package com.nosorio.xmentor.utils;

import com.nosorio.xmentor.Constants;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthUtils {

    public static String getUsernameFromJwt(Jwt principal){
        return principal.getClaimAsString(Constants.USERNAME_CLAIM);
    }
}
