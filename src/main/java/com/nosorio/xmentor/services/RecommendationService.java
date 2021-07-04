package com.nosorio.xmentor.services;

import com.nosorio.xmentor.dtos.RecommendationDTO;
import com.nosorio.xmentor.models.recommendations.BaseRecommendation;
import com.nosorio.xmentor.models.recommendations.DiscoverRecommendation;
import com.nosorio.xmentor.models.recommendations.EnrollRecommendation;
import com.nosorio.xmentor.models.recommendations.InterestRecommendation;
import com.nosorio.xmentor.services.recommendations.DiscoverRecommendationStrategy;
import com.nosorio.xmentor.services.recommendations.EnrollRecommendationStrategy;
import com.nosorio.xmentor.services.recommendations.InterestRecommendationStrategy;
import com.nosorio.xmentor.services.recommendations.RecommendationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    public RecommendationDTO getRecommendation(String username){
        EnrollRecommendation enrolledCourses = (EnrollRecommendation) getByStrategy(new EnrollRecommendationStrategy(), username);
        InterestRecommendation interestedCourses = (InterestRecommendation) getByStrategy(new InterestRecommendationStrategy(), username);
        DiscoverRecommendation discoveredCourses = (DiscoverRecommendation) getByStrategy(new DiscoverRecommendationStrategy(), username);
        return new RecommendationDTO(enrolledCourses, interestedCourses, discoveredCourses);
    }

    public RecommendationDTO getVisitorRecommendation(){
        return new RecommendationDTO(null, null, null);
    }

    private BaseRecommendation getByStrategy(RecommendationStrategy strategy, String username){
        return strategy.recommend(username);
    }
}
