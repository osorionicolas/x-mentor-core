package com.nosorio.xmentor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class NotificationController {

    private ExecutorService nonBlockingService = Executors.newCachedThreadPool();

    @GetMapping("/notifications")
    public SseEmitter subscribeToNotifications() {
        SseEmitter emitter = new SseEmitter();
        nonBlockingService.execute(() -> {
            try {
                emitter.send("/sse" + " @ " + new Date());
                // we could send more events
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }
}
