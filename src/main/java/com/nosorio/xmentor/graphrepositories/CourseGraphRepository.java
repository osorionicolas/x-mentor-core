package com.nosorio.xmentor.graphrepositories;

import com.nosorio.xmentor.models.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseGraphRepository extends Neo4jRepository<Course, Long> {

    @Query("MATCH (u:User)-[r:STUDYING]->(c:Course) WHERE u.username = $username RETURN c SKIP $skip LIMIT $limit")
    List<Course> findByUsername(String username, int skip, int limit);

    @Query("MATCH (u:User)-[r:STUDYING]->(c:Course), (t:Topic)-[:has]->(c:Course) WHERE u.username = $username RETURN c")
    List<Course> findByUsernameAndTopic(String username, String topic, int skip, int limit);

    @Query("MATCH (t:Topic)-[r:HAS]->(c:Course) WHERE t.name = $topic RETURN c")
    List<Course> findByTopic(String topic, int skip, int limit);

    /*@Query("MATCH (u:User)-[r:RATED]->(c:Course) WHERE u.username = $username RETURN c")
    List<Course> findByRated(String username, int skip, int limit);*/

}