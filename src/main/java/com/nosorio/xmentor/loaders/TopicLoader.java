package com.nosorio.xmentor.loaders;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.nosorio.xmentor.graphrepositories.TopicGraphRepository;
import com.nosorio.xmentor.models.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TopicLoader {

    private final TopicGraphRepository topicGraphRepository;

    public void loadTopics() throws IOException {
        log.info("Loading topics ...");
        ClassPathResource coursesFile = new ClassPathResource("data/topics.csv");

        if (coursesFile.exists()) {
            MappingIterator<Topic> topics = new CsvMapper().readerWithTypedSchemaFor(Topic.class).readValues(coursesFile.getInputStream());
            topicGraphRepository.saveAll(topics.readAll());
        }
    }
}
