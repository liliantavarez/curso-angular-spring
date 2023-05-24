package com.lilian.services;

import com.lilian.exceptions.RecordNotFoundException;
import com.lilian.model.Course;
import com.lilian.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


//Classe service é uma especialização na anotação de component;
@Service
@Validated
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course post(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Course update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id).map(courseFound -> {
            courseFound.setName(course.getName());
            courseFound.setCategory(course.getCategory());
            return courseRepository.save(courseFound);
        }).orElseThrow(() -> new RecordNotFoundException(id));

    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));


    }
}
