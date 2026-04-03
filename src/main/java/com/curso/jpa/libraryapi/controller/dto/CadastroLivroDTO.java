package com.curso.jpa.libraryapi.controller.dto;

import com.curso.jpa.libraryapi.model.GeneroLivro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Livro")
public record CadastroLivroDTO(
        @ISBN
        @NotBlank(message = "campo obrigatorio")
        String isbn,
        @NotBlank(message = "campo obrigatorio")
        String titulo,
        @NotNull(message = "campo obrigatorio")
        @Past(message = "data de publicação deve ser uma data passada")
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        @NotNull(message = "campo obrigatorio")
        UUID idAutor) {
}
