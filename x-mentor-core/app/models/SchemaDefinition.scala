package models

import sangria.macros.derive._
import sangria.schema._
import services.CourseService

import scala.concurrent.{ExecutionContext, Future}

/**
  * Defines a GraphQL schema for the current project
  */
object SchemaDefinition {

  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  val CourseType =
    deriveObjectType[Unit, Course](
      ObjectTypeDescription("A course"),
      DocumentField("id", "The id of the course"),
      DocumentField("title", "The title of the course"),
      DocumentField("description", "The description of the course"))

  val ID = Argument("id", LongType, description = "id of the course")
  val query = Argument("query", StringType, description = "id of the course")
  val page = Argument("page", IntType, description = "id of the course")

  val Query = ObjectType(
    "Query",
    fields[CourseService, Unit](
      Field(
        "course",
        CourseType,
        arguments = ID :: Nil,
        resolve = ctx =>
          ctx.ctx
            .retrieveById(ctx.arg(ID))
            .flatMap(_.fold(error => Future.failed(new IllegalArgumentException), course => Future(course)))
      ),
      Field(
        "courses",
        ListType(CourseType),
        arguments = query :: page :: Nil,
        resolve = ctx =>
          ctx.ctx
            .retrieve(ctx.arg(query), ctx.arg(page))
            .flatMap(_.fold(error => Future.failed(new IllegalArgumentException), course => Future(course.courses)))
      ),
    )
  )

  val schema = Schema(Query)
}
