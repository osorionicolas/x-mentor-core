package com.nosorio.xmentor.messaging;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${rabbitmq.queue}", durable = "false"),
        exchange = @Exchange(value = "${rabbitmq.exchange}", ignoreDeclarationExceptions = "true"),
        key = "foo.bar.#"
))
public class Receiver {

    private final CountDownLatch latch = new CountDownLatch(1);

    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}