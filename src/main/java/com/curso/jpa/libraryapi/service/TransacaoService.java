package com.curso.jpa.libraryapi.service;

import com.curso.jpa.libraryapi.repository.AutorRepository;
import com.curso.jpa.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizarSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("b38d77e7-f8d3-4951-a808-62b8a59759b9"))
                .orElse(null);
        livro.setDataPublicacao(LocalDate.of(2024, 6, 1));
    }
}
