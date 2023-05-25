package com.lilian.dto.mapper;

import com.lilian.dto.CourseDTO;
import com.lilian.enums.Category;
import com.lilian.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDto(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toCourse(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "front-end" -> Category.FRONT_END;
            case "back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria invalida: " + value);
        };
    }

}
