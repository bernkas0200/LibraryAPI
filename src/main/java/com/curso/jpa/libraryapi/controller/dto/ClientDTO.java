package com.curso.jpa.libraryapi.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(
        @NotBlank(message = "campo obrigatório")
        String clientId,
        @NotBlank(message = "campo obrigatório")
        String clientSecret,
        @NotBlank(message = "campo obrigatório")
        String redirectURI,
        String scope) {

}
