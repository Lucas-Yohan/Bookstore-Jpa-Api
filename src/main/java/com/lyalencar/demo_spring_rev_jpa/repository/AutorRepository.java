package com.lyalencar.demo_spring_rev_jpa.repository;

import com.lyalencar.demo_spring_rev_jpa.model.Autor;
import com.lyalencar.demo_spring_rev_jpa.projection.AutorInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%')) OR LOWER(a.sobrenome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Autor> findAllByNomeOrSobrenome(@Param("nome") String nome);

    @Query("SELECT a FROM Autor a WHERE LOWER(a.infoAutor.cargo) LIKE LOWER(CONCAT('%', :cargo, '%'))")
    List<Autor> findByCargo(@Param("cargo") String cargo);

    @Query("SELECT a.nome as nome, a.sobrenome as sobrenome, a.infoAutor.cargo as cargo, a.infoAutor.bio as bio " +
            "FROM Autor a WHERE a.id = :id")
    AutorInfoProjection findAutorProjectionById(@Param("id") Long id);
}
