package com.nosorio.xmentor.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.nosorio.xmentor.dtos.CoursePagination;
import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.services.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DgsComponent
@RequiredArgsConstructor
public class CourseDsgComponent {

    private final CourseService courseService;

    @DgsMutation
    public Course createCourse(@InputArgument Course course){
        return this.courseService.createCourse(course);
    }


    @DgsQuery
    public CoursePagination getCourses(@InputArgument String query, @InputArgument int page){
        return this.courseService.getCoursesByQuery(query, page);
    }

    @DgsQuery
    public Course getCourse(@InputArgument String courseId){
        return this.courseService.getCourseById(courseId);
    }


}
