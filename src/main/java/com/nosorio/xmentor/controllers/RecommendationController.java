package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.dtos.RecommendationDTO;
import com.nosorio.xmentor.services.RecommendationService;
import com.nosorio.xmentor.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping({"", "/"})
    public RecommendationDTO getRecommendationForUser(@AuthenticationPrincipal Jwt principal){
        return this.recommendationService.getRecommendation(AuthUtils.getUsernameFromJwt(principal));
    }

    @GetMapping("/visitors")
    public RecommendationDTO getRecommendationForVisitor(){
        return this.recommendationService.getVisitorRecommendation();
    }

}
