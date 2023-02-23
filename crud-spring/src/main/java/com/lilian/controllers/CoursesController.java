package com.lilian.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lilian.model.Course;
import com.lilian.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor // Cria o construtor

@Component
public class CoursesController {

    private final CourseRepository courseRepository;

    @GetMapping
    public @ResponseBody List<Course> getAll() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    // ResponseEntity<Course>: Definindo tipo do retorno
    // @PathVariable Long id: parâmetro de nome id tipo long sendo enviado no
    // caminho da requisição
    public ResponseEntity<Course> getById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseFound -> ResponseEntity.ok().body(courseFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseFound -> {
            courseRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        })
                // Retorna que o registro não foi encontrado
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
        return courseRepository.findById(id).map(courseFound -> {
            courseFound.setName(course.getName());
            courseFound.setCategory(course.getCategory());
            Course updated = courseRepository.save(courseFound);
            return ResponseEntity.ok().body(updated);
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course post(@RequestBody @Valid Course course) {
        return courseRepository.save(course);
    }
}
