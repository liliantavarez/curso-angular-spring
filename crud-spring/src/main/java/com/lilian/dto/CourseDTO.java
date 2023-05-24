package com.lilian.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank @Nonnull @Length(min = 4, max = 150) String name,
        @Nonnull @Length(max = 10) @Pattern(regexp = "back-end|front-end") String category
) {
}

