package controllers

import javax.inject.Inject
import play.api.libs.json._
import play.api.mvc._
import sangria.execution._
import sangria.parser.{QueryParser, SyntaxError}
import sangria.marshalling.playJson._
import models.SchemaDefinition
import sangria.renderer.SchemaRenderer
import sangria.slowlog.SlowLog
import services.CourseService

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class GraphQLController @Inject()()(implicit ec: ExecutionContext)
  extends BaseController{

  def graphqlBody = Action.async(parse.json) { request =>
    val query = (request.body \ "query").as[String]
    val operation = (request.body \ "operationName").asOpt[String]

    val variables = (request.body \ "variables").toOption.flatMap {
      case JsString(vars) => Some(parseVariables(vars))
      case obj: JsObject => Some(obj)
      case _ => None
    }

    executeQuery(query, variables, operation, isTracingEnabled(request))
  }

  private def parseVariables(variables: String) =
    if (variables.trim == "" || variables.trim == "null") Json.obj() else Json.parse(variables).as[JsObject]

  private def executeQuery(query: String, variables: Option[JsObject], operation: Option[String], tracing: Boolean) =
    QueryParser.parse(query) match {

      // query parsed successfully, time to execute it!
      case Success(queryAst) =>
        Executor.execute(SchemaDefinition.schema, queryAst, new CourseService,
          operationName = operation,
          variables = variables getOrElse Json.obj(),
          exceptionHandler = exceptionHandler,
          queryReducers = List(
            QueryReducer.rejectMaxDepth[CourseService](15),
            QueryReducer.rejectComplexQueries[CourseService](4000, (_, _) => TooComplexQueryError)),
          middleware = if (tracing) SlowLog.apolloTracing :: Nil else Nil)
          .map(Ok(_))
          .recover {
            case error: QueryAnalysisError => BadRequest(error.resolveError)
            case error: ErrorWithResolver => InternalServerError(error.resolveError)
          }

      // can't parse GraphQL query, return error
      case Failure(error: SyntaxError) =>
        Future.successful(BadRequest(Json.obj(
          "syntaxError" -> error.getMessage,
          "locations" -> Json.arr(Json.obj(
            "line" -> error.originalError.position.line,
            "column" -> error.originalError.position.column)))))

      case Failure(error) =>
        throw error
    }

  def isTracingEnabled(request: Request[_]) = request.headers.get("X-Apollo-Tracing").isDefined

  def renderSchema = Action {
    Ok(SchemaRenderer.renderSchema(SchemaDefinition.schema))
  }

  lazy val exceptionHandler = ExceptionHandler {
    case (_, error @ TooComplexQueryError) => HandledException(error.getMessage)
    case (_, error @ MaxQueryDepthReachedError(_)) => HandledException(error.getMessage)
  }

  case object TooComplexQueryError extends Exception("Query is too expensive.")
}
