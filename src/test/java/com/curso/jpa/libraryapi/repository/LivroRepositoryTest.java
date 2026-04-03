package com.curso.jpa.libraryapi.repository;

import com.curso.jpa.libraryapi.model.Autor;
import com.curso.jpa.libraryapi.model.GeneroLivro;
import com.curso.jpa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvartest(){
        Livro livro = new Livro();
        livro.setIsbn("9875-4321");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Ciencias");
        livro.setDataPublicacao(LocalDate.of(1880, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("b643b27c-b917-43bc-8441-63a260501a0d"))
                .orElse(null);
        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test //mais comum
    void salvarAutorELivrotest(){
        Livro livro = new Livro();
        livro.setIsbn("9875-4321");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Quatro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Josefino");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadetest(){
        Livro livro = new Livro();
        livro.setIsbn("9875-4321");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("João");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivroTest(){
         UUID id = UUID.fromString("22dd2b2c-27a9-4030-848f-31ab8fe136ea");
         var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("6c940a0b-589f-47b9-b56e-2af268937824");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletarTest(){
        UUID id = UUID.fromString("8f2c3ae7-ed2f-4aea-bca0-ebdfa564b0d0");
        repository.deleteById(id);
    }

    @Test
    void buscarLivroTest(){
        UUID id = UUID.fromString("3b1ccdf3-6662-4017-a1e1-acb96b686bd8");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro:");
        System.out.println(livro.getTitulo());
        System.out.println("Autor:");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void buscaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("Senhor dos Colares");
        lista.forEach(System.out::println);
    }

    @Test
    void buscaPorIsbnTest(){
        Optional<Livro> livro = repository.findByIsbn("9875-4321");
        livro.ifPresent(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodos();
        resultado.forEach(System.out::println);
    }

    @Test
    @Transactional
    void listarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroTest(){
        var resultado = repository.findByGenero(GeneroLivro.FICCAO);
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest(){
        repository.deleteByGenero(GeneroLivro.FICCAO);
    }

    @Test
    void atualizarDataPubTest(){
        repository.updateDataPublicacao(LocalDate.of(2000, 12, 24));
    }
}