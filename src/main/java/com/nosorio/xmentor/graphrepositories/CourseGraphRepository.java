package com.nosorio.xmentor.graphrepositories;

import com.nosorio.xmentor.dtos.RatingDTO;
import com.nosorio.xmentor.models.Course;
import lombok.val;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseGraphRepository extends Neo4jRepository<Course, Long> {

    @Query("MATCH (u:User)-[r:STUDYING]->(c:Course) WHERE u.username = $username RETURN c SKIP $skip LIMIT $limit")
    List<Course> findByUsername(String username, int skip, int limit);

    @Query("MATCH (u:User)-[r:STUDYING]->(c:Course), (t:Topic)-[:has]->(c:Course) WHERE u.username = $username RETURN c")
    List<Course> findByUsernameAndTopic(String username, String topic);

    @Query("MATCH (t:Topic)-[r:HAS]->(c:Course) WHERE t.name = $topic RETURN c")
    List<Course> findByTopic(String topic);

    /*@Query("MATCH (u:User)-[r:RATED]->(c:Course) WHERE u.username = $username RETURN c")
    List<Course> findByRated(String username, int skip, int limit);*/

    @Query("MATCH (u:User), (t:Topic) WHERE u.username = $username AND t.name = $topic CREATE (u)-[r:INTERESTED]->(t)")
    void createInterestRelationQuery(String username, String topic);

    @Query("MATCH (u:User), (c:Course) WHERE u.username = $username AND c.title = $course.title CREATE (u)-[:RATES {rating:${rating.stars}}]->(c)")
    void createRatesQuery(String username, Course course, RatingDTO rating);

    @Query("MATCH (u:User), (c:Course) WHERE u.username = $username AND c.title = ${course.title} CREATE (u)-[:STUDYING]->(c)")
    void createStudyingRelationQuery(String username, Course course);

    @Query("MATCH (u:User)-[interest:INTERESTED]->(t:Topic) WHERE u.username = $username and t.name = $topic DELETE interest")
    void deleteInterestRelationQuery(String username, String topic);

}