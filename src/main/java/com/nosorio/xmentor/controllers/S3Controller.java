package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.services.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assets")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/download")
    public ResponseEntity<Object> findByName(@RequestParam String filename) {
        return new ResponseEntity<>(s3Service.findByName(filename), HttpStatus.OK);
    }

    @GetMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam String filename, @RequestParam String extension) {
        return new ResponseEntity<>(s3Service.save(filename, extension), HttpStatus.OK);
    }

}
