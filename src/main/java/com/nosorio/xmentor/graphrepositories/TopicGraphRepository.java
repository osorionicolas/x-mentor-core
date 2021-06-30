package com.nosorio.xmentor.graphrepositories;

import com.nosorio.xmentor.models.Topic;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicGraphRepository extends Neo4jRepository<Topic, String> {
    @Query("MATCH (u:User)-[r:INTERESTED]->(t:Topic) WHERE u.username = $username RETURN t")
    List<Topic> findByUsername(String username);
}