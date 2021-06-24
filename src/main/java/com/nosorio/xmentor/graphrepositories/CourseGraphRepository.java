package com.nosorio.xmentor.graphrepositories;

import com.nosorio.xmentor.models.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseGraphRepository extends Neo4jRepository<Course, Long> {

}