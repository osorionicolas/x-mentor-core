package com.nosorio.xmentor.repositories;

import com.nosorio.xmentor.models.S3File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface S3FileRepository extends JpaRepository<S3File, S3File>{
    Optional<S3File> findByFilename(String filename);
    Optional<S3File> findByUuid(String uuid);

}