package com.curso.jpa.libraryapi.repository;

import com.curso.jpa.libraryapi.model.Autor;
import com.curso.jpa.libraryapi.model.GeneroLivro;
import com.curso.jpa.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {

    //Query method
    //select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);;

    List<Livro> findByTitulo(String titulo);

    Optional<Livro> findByIsbn(String isbn);

    //JPQL-->usar os nomes das propriedades da entidade, não o que está no banco, se for dif
    @Query(" select l from Livro as l order by l.titulo ")
    List<Livro> listarTodos();

    @Query(" select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    @Query(" select l from Livro l where l.genero = :genero")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generolivro);

    @Modifying
    @Transactional
    @Query(" delete from Livro where genero = ?1 ")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query(" update Livro set dataPublicacao = ?1 ")
    void updateDataPublicacao(LocalDate novadata);

    boolean existsByAutor(Autor autor);
}
