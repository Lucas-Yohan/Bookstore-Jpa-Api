package com.lyalencar.demo_spring_rev_jpa.repository;

import com.lyalencar.demo_spring_rev_jpa.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoriasTituloAndAutorId(String categoria, Long autorId);

    List<Post> findByAutorNomeOrAutorSobrenome(String autorNome, String autorSobrenome);

    List<Post> findByTituloContainsOrderByAutorNomeAsc(String titulo);

    List<Post> findByDataPublicacaoIsGreaterThanEqual(LocalDate dataPublicacao);

    List<Post> findByDataPublicacaoIsNull();

}
