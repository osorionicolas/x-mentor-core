package com.nosorio.xmentor.unit.services;

import com.nosorio.xmentor.dtos.CoursePagination;
import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.repositories.CourseRepository;
import com.nosorio.xmentor.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepositoryMock;

    @Test
    public void whenQueryAndPageAreProvided_thenRetrieveCourses(){
        // Initialize variables
        Long totalCourses = 100L;
        int courseListSize = 5;
        int page = 1;
        String query = "";

        // Mock objects
        EasyRandom generator = new EasyRandom();
        List<Course> courseList = generator.objects(Course.class, courseListSize).collect(Collectors.toList());
        Pageable pageable = Mockito.mock(Pageable.class);
        Page<Course> coursesPage = new PageImpl(courseList, pageable, totalCourses);

        // Mock responses
        when(
            courseRepositoryMock.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(
                    any(String.class), any(String.class), any(Pageable.class)
            )
        ).thenReturn(coursesPage);

        // Call method
        CoursePagination courses = courseService.getCoursesByQuery(query, page);

        // Assert response
        assertEquals(courses.getTotal(), totalCourses);
        assertEquals(courses.getCourses().size(), courseListSize);
    }
}
