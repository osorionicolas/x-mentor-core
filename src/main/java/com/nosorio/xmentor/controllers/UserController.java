package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.models.Topic;
import com.nosorio.xmentor.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/courses")
    public List<Course> getCoursesByUser(@RequestParam int page){
        return userService.getCourses("mamie.toy@gmail.com", page);
    }

    @GetMapping("/interests")
    public List<String> getInterestsByUser(){
        return userService.getInterests("mamie.toy@gmail.com");
    }

    @PostMapping("/interests")
    public ResponseEntity<Object> registerInterest(){
        return new ResponseEntity(HttpStatus.OK);
    }
}