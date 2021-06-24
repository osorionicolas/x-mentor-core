package com.nosorio.xmentor.graphrepositories;

import com.nosorio.xmentor.models.Topic;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicGraphRepository extends Neo4jRepository<Topic, String> {

}