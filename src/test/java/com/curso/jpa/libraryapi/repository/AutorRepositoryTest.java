package com.curso.jpa.libraryapi.repository;

import com.curso.jpa.libraryapi.model.Autor;
import com.curso.jpa.libraryapi.model.GeneroLivro;
import com.curso.jpa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("75b60e37-eb0b-4fb4-ba07-24e07a06eaf7");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 2, 27));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("75b60e37-eb0b-4fb4-ba07-24e07a06eaf7");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("8e35061d-234b-499a-ad5a-d9d0f3d3c2fb");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Mexicana");
        autor.setDataNascimento(LocalDate.of(1970, 8, 2));

        Livro livro = new Livro();
        livro.setIsbn("10875-4321");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Senhor dos Colares");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("99999-4321");
        livro2.setPreco(BigDecimal.valueOf(204));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("Casa Assombrada");
        livro2.setDataPublicacao(LocalDate.of(2001, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutorTest(){
        var id = UUID.fromString("e0ede34e-a444-44d8-b8f3-b961e19f592d");
        var autor = repository.findById(id).get();

        List<Livro> livroslista = livroRepository.findByAutor(autor);
        autor.setLivros(livroslista);

        autor.getLivros().forEach(System.out::println);
    }

}
