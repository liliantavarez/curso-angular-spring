package com.lilian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lilian.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
