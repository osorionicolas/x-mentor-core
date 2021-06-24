package com.nosorio.xmentor.services;

import com.nosorio.xmentor.graphrepositories.TopicGraphRepository;
import com.nosorio.xmentor.models.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicGraphRepository topicGraphRepository;

    public List<Topic> getAll(){
        return topicGraphRepository.findAll();
    }
}
