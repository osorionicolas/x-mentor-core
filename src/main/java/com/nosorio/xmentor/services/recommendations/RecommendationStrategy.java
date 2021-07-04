package com.nosorio.xmentor.services.recommendations;

import com.nosorio.xmentor.models.recommendations.BaseRecommendation;

public interface RecommendationStrategy {
    BaseRecommendation recommend(String username);
}
