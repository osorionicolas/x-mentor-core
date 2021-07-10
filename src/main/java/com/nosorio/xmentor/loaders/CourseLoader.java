package com.nosorio.xmentor.loaders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosorio.xmentor.graphrepositories.CourseGraphRepository;
import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseLoader {

    private final CourseRepository courseRepository;
    private final CourseGraphRepository courseGraphRepository;

    public void loadCourses() throws IOException {
        log.info("Loading courses ...");
        ClassPathResource coursesFile = new ClassPathResource("data/courses.json");

        if (coursesFile.exists()) {
            List<Course> elements = new ObjectMapper().readValue(coursesFile.getInputStream(), new TypeReference<>() {});
            courseRepository.saveAll(elements);
            courseGraphRepository.saveAll(elements);
        }
    }
}
