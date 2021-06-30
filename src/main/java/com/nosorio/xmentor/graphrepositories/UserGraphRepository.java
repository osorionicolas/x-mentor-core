package com.nosorio.xmentor.graphrepositories;

import com.nosorio.xmentor.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGraphRepository extends Neo4jRepository<User, String> {
}