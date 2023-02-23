package com.lilian.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

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
    @Length(max = 10)
    @Pattern(regexp = "back-end|front-end")
    @Column(length = 20, nullable = false)
    private String category;

    @Nonnull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo";

}
