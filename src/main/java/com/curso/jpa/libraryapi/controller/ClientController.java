package com.curso.jpa.libraryapi.controller;

import com.curso.jpa.libraryapi.controller.dto.ClientDTO;
import com.curso.jpa.libraryapi.controller.mappers.ClientMapper;
import com.curso.jpa.libraryapi.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService service;
    private final ClientMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('GERENTE')")
    public void salvar(@RequestBody @Valid ClientDTO dto){
        log.info("Cadastrando cliente {} com escopo: {}", dto.clientId(), dto.scope());

        var client = mapper.toEntity(dto);
        service.salvar(client);
    }
}
