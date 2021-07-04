package com.nosorio.xmentor.dtos;

import com.nosorio.xmentor.models.recommendations.DiscoverRecommendation;
import com.nosorio.xmentor.models.recommendations.EnrollRecommendation;
import com.nosorio.xmentor.models.recommendations.InterestRecommendation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendationDTO {
    private EnrollRecommendation basedOnEnroll;
    private InterestRecommendation basedOnInterest;
    private DiscoverRecommendation basedOnDiscover;
}
