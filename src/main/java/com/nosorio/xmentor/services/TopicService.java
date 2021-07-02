package com.nosorio.xmentor.services;

import com.nosorio.xmentor.graphrepositories.TopicGraphRepository;
import com.nosorio.xmentor.models.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicGraphRepository topicGraphRepository;

    public List<String> getAll(){
        return topicGraphRepository.findAll().stream().map(Topic::getName).collect(Collectors.toList());
    }
}
