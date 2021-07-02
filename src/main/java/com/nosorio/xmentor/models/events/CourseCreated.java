package com.nosorio.xmentor.models.events;

import com.nosorio.xmentor.models.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CourseCreated extends DomainEvent implements Serializable {
    private Course course;
    private Long createdAt;
}