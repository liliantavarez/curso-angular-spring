package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
