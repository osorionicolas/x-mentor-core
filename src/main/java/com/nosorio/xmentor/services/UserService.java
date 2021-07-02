package com.nosorio.xmentor.services;

import com.nosorio.xmentor.Constants;
import com.nosorio.xmentor.graphrepositories.CourseGraphRepository;
import com.nosorio.xmentor.graphrepositories.TopicGraphRepository;
import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.models.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final CourseGraphRepository courseGraphRepository;
    private final TopicGraphRepository topicGraphRepository;

    public List<String> getInterests(String username){
        return topicGraphRepository.findByUsername(username).stream().map(Topic::getName).collect(Collectors.toList());
    }

    public List<Course> getCourses(String username, int page){
        int itemsPerPage = Constants.ITEMS_PER_PAGE;
        int offset = (page - 1) * itemsPerPage;
        return courseGraphRepository.findByUsername(username, offset, itemsPerPage);
    }

}
