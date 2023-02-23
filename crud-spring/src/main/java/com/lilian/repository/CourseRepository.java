package com.lilian.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lilian.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
