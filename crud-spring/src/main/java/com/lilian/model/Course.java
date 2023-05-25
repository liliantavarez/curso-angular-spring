package com.lilian.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilian.enums.Category;
import com.lilian.enums.Status;
import com.lilian.enums.converters.CategoryConverter;
import com.lilian.enums.converters.StatusConverter;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
// Recebe uma string com o sql que sera executado pelo deleted via  hibernate
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
//Inserindo filtro no where padrão das requisições via hibernate
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @Nonnull
    @Length(min = 4, max = 150)

    @Column(length = 150, nullable = false)
    private String name;

    @Nonnull
    @Column(length = 20, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @Nonnull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

}
