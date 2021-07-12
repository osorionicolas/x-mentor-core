package com.nosorio.xmentor.repositories;

import com.nosorio.xmentor.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    Page<Course> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String title, String description, Pageable pageable);
    Optional<Course> findByUuid(UUID uuid);
}