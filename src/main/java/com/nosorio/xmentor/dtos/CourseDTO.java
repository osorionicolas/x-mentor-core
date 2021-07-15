package com.nosorio.xmentor.dtos;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class CourseDTO {
    private UUID uuid;
    private String title;
    private String description;
    private Map<String, String> content;
    private String preview;
    private String topic;
    private int rating;
}
