package com.curso.jpa.libraryapi.repository;

import com.curso.jpa.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacaoTeste {

    @Autowired
    TransacaoService transacaoService;

    @Test
    void transacaoEstadoManaged(){
        transacaoService.atualizarSemAtualizar();
    }
}
