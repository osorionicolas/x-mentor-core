package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.models.Topic;
import com.nosorio.xmentor.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/topics")
    public List<Topic> getAll(){
        return topicService.getAll();
    }

}
