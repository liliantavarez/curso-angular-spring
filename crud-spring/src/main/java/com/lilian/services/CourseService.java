package com.lilian.services;

import com.lilian.dto.CourseDTO;
import com.lilian.dto.mapper.CourseMapper;
import com.lilian.exceptions.RecordNotFoundException;
import com.lilian.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;


//Classe service é uma especialização na anotação de component;
@Service
@Validated
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> getAll() {
        return courseRepository.findAll().stream().map(courseMapper::toDto).collect(Collectors.toList());
    }

    public CourseDTO getById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDto).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO post(@Valid @NotNull CourseDTO courseDTO) {
        return courseMapper.toDto(courseRepository.save(courseMapper.toCourse(courseDTO)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id).map(courseFound -> {
            courseFound.setName(course.name());
            courseFound.setCategory(course.category());
            return courseRepository.save(courseFound);
        }).map(courseMapper::toDto).orElseThrow(() -> new RecordNotFoundException(id));

    }

    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));


    }
}
