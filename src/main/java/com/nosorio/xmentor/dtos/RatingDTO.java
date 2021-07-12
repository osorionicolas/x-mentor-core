package com.nosorio.xmentor.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RatingDTO {
    private String courseId;
    private Integer stars;
}
