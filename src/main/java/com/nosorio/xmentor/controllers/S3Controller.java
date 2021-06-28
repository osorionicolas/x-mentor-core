package com.nosorio.xmentor.controllers;

import com.nosorio.xmentor.repositories.S3Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Repository s3repository;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam MultipartFile file) {
        s3repository.uploadFile(file);
    }

}
