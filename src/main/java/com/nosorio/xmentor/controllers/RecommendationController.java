package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.dtos.RecommendationDTO;
import com.nosorio.xmentor.services.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping({"", "/"})
    public RecommendationDTO recommend(){
        //this.recommendationService
        return new RecommendationDTO();
    }

    @GetMapping("/visitors")
    public RecommendationDTO visitorRecommend(){
        //this.recommendationService
        return new RecommendationDTO();
    }

}
