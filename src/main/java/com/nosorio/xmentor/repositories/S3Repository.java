package com.nosorio.xmentor.repositories;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Repository
public class S3Repository {

    private final AmazonS3Client s3Client;

    public S3Repository(AmazonS3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(MultipartFile mFile) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(mFile.getSize());
            s3Client.putObject(new PutObjectRequest("xmentor", "file", mFile.getInputStream(), metadata));
        }
        catch(Exception e){
            log.info(e.getMessage());
        }
    }

}