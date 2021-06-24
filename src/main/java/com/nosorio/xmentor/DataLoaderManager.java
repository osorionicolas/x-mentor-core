package com.nosorio.xmentor;

import com.nosorio.xmentor.loaders.CourseLoader;
import com.nosorio.xmentor.loaders.TopicLoader;
import com.nosorio.xmentor.loaders.UserLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoaderManager implements CommandLineRunner {

    private final CourseLoader courseLoader;
    private final TopicLoader topicLoader;
    private final UserLoader userLoader;

    @Override
    public void run(String... args) {
        log.info("Starting loader");
        try {
            courseLoader.loadCourses();
            topicLoader.loadTopics();
            userLoader.loadUsers();

        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }
}
