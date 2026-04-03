package com.curso.jpa.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

//DTO --> Data Transfer Object
@Schema(name = "Autor")
public record AutorDTO(
        UUID id,
        @NotBlank(message = "campo obrigatório")
        @Size(min = 2, max = 100, message = "campo fora do tamanho padrão")
        String nome,
        @NotNull(message = "campo obrigatório")
        @Past(message = "data de nascimento deve ser uma data passada")
        LocalDate dataNascimento,
        @NotBlank(message = "campo obrigatório")
        @Size(min = 2, max = 50, message = "campo fora do tamanho padrão")
        String nacionalidade) {
}
