package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.models.Course;
import com.nosorio.xmentor.services.UserService;
import com.nosorio.xmentor.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/courses")
    public List<Course> getCoursesByUser(@AuthenticationPrincipal Jwt jwt, @RequestParam int page){
        String username = AuthUtils.getUsernameFromJwt(jwt);
        return userService.getCourses(username, page);
    }

    @GetMapping("/interests")
    public List<String> getInterestsByUser(@AuthenticationPrincipal Jwt jwt){
        String username = AuthUtils.getUsernameFromJwt(jwt);
        return userService.getInterests(username);
    }

    @PostMapping("/interests")
    public ResponseEntity<Object> registerInterest(@AuthenticationPrincipal Jwt jwt){
        return new ResponseEntity(HttpStatus.OK);
    }
}
