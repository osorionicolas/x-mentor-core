package com.nosorio.xmentor.services;

import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.token.Tokens;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponseParser;
import com.nosorio.xmentor.configurations.AuthConfiguration;
import com.nosorio.xmentor.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final AuthConfiguration authConfiguration;

    public Tokens login(User user) {
        log.info("Trying to login with user: {}", user.getUsername());
        TokenRequest request =
                new TokenRequest(
                    this.authConfiguration.getTokenUrl(),
                    new ClientSecretBasic(this.authConfiguration.getClientId(), this.authConfiguration.getClientSecret()),
                    new ResourceOwnerPasswordCredentialsGrant(user.getUsername(), new Secret(user.getPassword())),
                    new Scope("openid")
                );
        try {
            TokenResponse tokenResponse = OIDCTokenResponseParser.parse(request.toHTTPRequest().send());
            if (!tokenResponse.indicatesSuccess()) {
                log.info("Login fail for user: {}", user.getUsername());
                TokenErrorResponse errorResponse = tokenResponse.toErrorResponse();
                log.info(errorResponse.toHTTPResponse().toString());
                throw new ResponseStatusException(HttpStatus.valueOf(errorResponse.toHTTPResponse().getStatusCode()), errorResponse.toHTTPResponse().toString());
            }
            else {
                log.info("Login successful for user: {}", user.getUsername());
                return tokenResponse.toSuccessResponse().getTokens();
            }
        } catch(IOException | ParseException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "IDK");
        }
    }

    public void logout() {

    }

    public void signup() {

    }

}
