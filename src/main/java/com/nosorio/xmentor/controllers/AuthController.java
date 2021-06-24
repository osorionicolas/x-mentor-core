package com.nosorio.xmentor.controllers;

import com.nimbusds.oauth2.sdk.token.Tokens;
import com.nosorio.xmentor.models.User;
import com.nosorio.xmentor.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Tokens login(@RequestBody User user) {
        return authService.login(user);
    }

    @PostMapping("/logout")
    public void logout() {

    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user) {

    }
}
