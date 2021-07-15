package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.dtos.CoursePagination;
import com.nosorio.xmentor.dtos.RatingDTO;
import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.services.CourseService;
import com.nosorio.xmentor.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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
    public Course retrieveById(@AuthenticationPrincipal Jwt jwt, @PathVariable (value = "id") String courseId) {
        String username = AuthUtils.getUsernameFromJwt(jwt);
        return courseService.getCourseById(username, courseId);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Course> create(@AuthenticationPrincipal Jwt jwt, @RequestBody Course course) {
        return new ResponseEntity<>( courseService.createCourse(course), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/enroll")
    public ResponseEntity<Void> enroll(@PathVariable (value = "id") String courseId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/rate")
    public ResponseEntity<Void> rate(@PathVariable RatingDTO rating) {
        courseService.rateCourse(rating);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
