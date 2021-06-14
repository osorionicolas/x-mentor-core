package models

import sangria.macros.derive._
import sangria.schema._
import services.CourseService

/**
  * Defines a GraphQL schema for the current project
  */
object SchemaDefinition {

  val CourseType =
    deriveObjectType[Unit, Course](
      ObjectTypeDescription("A course"),
      DocumentField("id", "The id of the course"),
      DocumentField("title", "The title of the course"),
      DocumentField("description", "The description of the course"))

  val ID = Argument("id", LongType, description = "id of the course")

  val Query = ObjectType(
    "Query", fields[CourseService, Unit](
      Field("course", CourseType,
        arguments = ID :: Nil,
        resolve = (ctx) => ctx.ctx.retrieveById(ctx.arg(ID))),
    ))

  val schema = Schema(Query)
}
