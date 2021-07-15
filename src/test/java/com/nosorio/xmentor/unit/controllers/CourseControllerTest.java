package com.nosorio.xmentor.unit.controllers;

import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.services.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CourseService courseService;

    @Test
    void ifNoJwtIsProvided_shouldReturn403() throws Exception {
        // Given
        Course course = new Course();

        // When
        when(courseService.getCourseById(any(String.class), any(String.class))).thenReturn(course);

        // Then
        MvcResult result = mockMvc.perform(get("/courses/1"))
                .andExpect(status().isForbidden())
                .andReturn();
        Assertions.assertEquals(result.getResponse().getErrorMessage(), "User is not allowed to access the resource");
    }

    /*@Test
    void ifJwtIsProvided_shouldReturnCourse() throws Exception {
        // Given
        Course course = new Course();
        String accessToken = ""

        // When
        when(courseService.getCourseById(any(String.class), any(String.class))).thenReturn(course);

        // Then
        mockMvc.perform(get("/courses/292f76ed-737b-4392-86d7-e5df727c22da")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)).andExpect(status().isOk());
    }*/
}
