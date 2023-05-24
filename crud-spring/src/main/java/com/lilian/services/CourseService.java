package com.lilian.services;

import com.lilian.model.Course;
import com.lilian.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


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

    public Optional<Course> getById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id);
    }

    public Course post(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id).map(courseFound -> {
            courseFound.setName(course.getName());
            courseFound.setCategory(course.getCategory());
            return courseRepository.save(courseFound);
        });
    }

    public boolean delete(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseFound -> {
                    courseRepository.deleteById(id);
                    return true;
                })
                // Retorna que o registro não foi encontrado
                .orElse(false);
    }
}
