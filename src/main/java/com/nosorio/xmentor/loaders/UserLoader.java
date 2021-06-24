package com.nosorio.xmentor.loaders;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.nosorio.xmentor.graphrepositories.UserGraphRepository;
import com.nosorio.xmentor.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserLoader {

    private final UserGraphRepository userGraphRepository;

    public void loadUsers() throws IOException {
        log.info("Loading users ...");
        ClassPathResource coursesFile = new ClassPathResource("data/students.csv");

        if (coursesFile.exists()) {
            MappingIterator<User> users = new CsvMapper().readerWithTypedSchemaFor(User.class).readValues(coursesFile.getInputStream());
            userGraphRepository.saveAll(users.readAll());
        }
    }
}
