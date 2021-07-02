package com.nosorio.xmentor.messaging;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.nosorio.xmentor.models.events.CourseCreated;
import com.nosorio.xmentor.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${rabbitmq.queue}", durable = "false"),
        exchange = @Exchange(value = "${rabbitmq.exchange}", ignoreDeclarationExceptions = "true"),
        key = "course.creation"
))
public class Receiver {

    private final NotificationService notificationService;
    private final CountDownLatch latch = new CountDownLatch(1);

    @RabbitHandler
    public void receiveMessage(CourseCreated course) {
        log.info("Course '" + course.getCourse().getTitle() + "' created on " + course.getCreatedAt());
        this.notificationService.getEmitters().forEach(emitter -> {
            try {
                emitter.send(course);
            } catch (IOException e) {
                log.error(e.getMessage());
                emitter.completeWithError(e);
            }
        });
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}