package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping(path = "/notifications", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribeToNotifications() {
        SseEmitter emitter = new SseEmitter();
        this.notificationService.addEmitter(emitter);
        return emitter;
    }
}
