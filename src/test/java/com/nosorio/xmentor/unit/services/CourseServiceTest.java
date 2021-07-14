package com.nosorio.xmentor.unit.services;

import com.nosorio.xmentor.dtos.CoursePagination;
import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.repositories.CourseRepository;
import com.nosorio.xmentor.services.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock CourseRepository courseRepositoryMock;

    @Test
    void whenQueryAndPageAreProvided_thenRetrieveCourses(){
        // Given
        Long totalCourses = 100L;
        int courseListSize = 5;
        int page = 1;
        String query = "";

        EasyRandom generator = new EasyRandom();
        List<Course> courseList = generator.objects(Course.class, courseListSize).collect(Collectors.toList());
        Pageable pageable = Mockito.mock(Pageable.class);
        Page<Course> coursesPage = new PageImpl(courseList, pageable, totalCourses);

        // When
        when(
            courseRepositoryMock.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(
                    any(String.class), any(String.class), any(Pageable.class)
            )
        ).thenReturn(coursesPage);

        CoursePagination courses = courseService.getCoursesByQuery(query, page);

        // Then
        Assertions.assertEquals(courses.getTotal(), totalCourses);
        Assertions.assertEquals(courses.getCourses().size(), courseListSize);
    }
}
