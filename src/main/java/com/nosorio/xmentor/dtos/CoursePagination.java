package com.nosorio.xmentor.dtos;

import com.nosorio.xmentor.models.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CoursePagination {
    private List<Course> courses;
    private Long total;
}
