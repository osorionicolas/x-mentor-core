package com.nosorio.xmentor.services;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Service
public class NotificationService {

    private final List<SseEmitter> emitters = new ArrayList<>();

    public void addEmitter(SseEmitter emitter) {
        this.emitters.add(emitter);
    }

}
