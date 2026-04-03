package com.curso.jpa.libraryapi.controller.mappers;

import com.curso.jpa.libraryapi.controller.dto.UsuarioDTO;
import com.curso.jpa.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
