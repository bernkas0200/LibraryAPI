package com.curso.jpa.libraryapi.controller.mappers;

import com.curso.jpa.libraryapi.controller.dto.ClientDTO;
import com.curso.jpa.libraryapi.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity (ClientDTO dto);
}
