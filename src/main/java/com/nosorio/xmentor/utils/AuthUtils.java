package com.nosorio.xmentor.utils;

import com.nosorio.xmentor.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class AuthUtils {

    public static String getUsernameFromJwt(Jwt jwt){
        return Optional.ofNullable(jwt)
                       .map(token -> token.getClaimAsString(Constants.USERNAME_CLAIM))
                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not allowed to access the resource"));
    }
}
