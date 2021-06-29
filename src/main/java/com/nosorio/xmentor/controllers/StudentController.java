package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.models.Topic;
import com.nosorio.xmentor.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final TopicService topicService;

    @GetMapping("/interests")
    public List<Topic> getByStudent(){
        return topicService.getAll();
    }

    @PostMapping("/interests")
    public ResponseEntity<Object> registerInterest(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
