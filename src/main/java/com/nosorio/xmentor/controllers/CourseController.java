package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.dtos.CoursePagination;
import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.services.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping({"", "/"})
    public CoursePagination retrieve(@RequestParam (value = "q") String query, @RequestParam int page) {
        return courseService.getCoursesByQuery(query, page);
    }

    @GetMapping("/{id}")
    public Course retrieveById(@PathVariable (value = "id") String courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return new ResponseEntity<>( courseService.createCourse(course), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/enroll")
    public ResponseEntity<Void> enroll(@PathVariable (value = "id") String courseId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/rate")
    public ResponseEntity<Void> rate() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
