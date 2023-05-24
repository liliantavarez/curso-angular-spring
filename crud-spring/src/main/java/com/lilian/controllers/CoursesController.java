package com.lilian.controllers;

import com.lilian.dto.CourseDTO;
import com.lilian.services.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/courses")
@Component
public class CoursesController {
    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public @ResponseBody List<CourseDTO> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    // ResponseEntity<Course>: Definindo tipo do retorno
    // @PathVariable Long id: parâmetro de nome id tipo long sendo enviado no
    // caminho da requisição
    public CourseDTO getById(@PathVariable @NotNull @Positive Long id) {
        return courseService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseService.delete(id);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CourseDTO
            course) {
        return courseService.update(id, course);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO post(@RequestBody @Valid @NotNull CourseDTO course) {
        return courseService.post(course);
    }
}
