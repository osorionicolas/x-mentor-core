package com.nosorio.xmentor.loaders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosorio.xmentor.graphrepositories.UserGraphRepository;
import com.nosorio.xmentor.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserLoader {

    private final UserGraphRepository userGraphRepository;

    public void loadUsers() throws IOException {
        log.info("Loading users ...");
        ClassPathResource usersFile = new ClassPathResource("data/users.json");

        if (usersFile.exists()) {
            List<User> users = new ObjectMapper().readValue(usersFile.getInputStream(), new TypeReference<List<User>>(){});
            userGraphRepository.saveAll(users);
        }
    }
}
