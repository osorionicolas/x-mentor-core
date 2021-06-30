package com.nosorio.xmentor.services;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.nosorio.xmentor.models.S3File;
import com.nosorio.xmentor.repositories.S3FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client s3Client;
    private final S3FileRepository s3FileRepository;

    @Value("${aws.bucket}")
    private String s3BucketName;

    private String generateUrl(String fileName, HttpMethod httpMethod) {
        try {
            Calendar expirationDate = Calendar.getInstance();
            expirationDate.setTime(new Date());
            expirationDate.add(Calendar.MINUTE, 15);
            return s3Client.generatePresignedUrl(s3BucketName, fileName, expirationDate.getTime(), httpMethod).toString();
        }
        catch(SdkClientException e){
            log.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to generate pre-signed url");
        }
    }

    public String findByName(String filename) {
        Optional<S3File> oFile = s3FileRepository.findByFilename(filename);
        String uuid = oFile.map(S3File::getUuid).orElse("");
        if (!s3Client.doesObjectExist(s3BucketName, uuid))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find file " + filename);
        log.info("Generating GET signed URL for file name {}", uuid);
        return generateUrl(uuid, HttpMethod.GET);
    }

    public String save(String filename, String extension) {
        String uniqueFilename = UUID.randomUUID() + "." + extension;
        log.info("Generating PUT signed URL for file name {}", uniqueFilename);
        String signedUrl = generateUrl(uniqueFilename, HttpMethod.PUT);
        s3FileRepository.save(new S3File(filename, uniqueFilename));
        return signedUrl;
    }

}