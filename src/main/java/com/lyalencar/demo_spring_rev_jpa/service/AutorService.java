package com.lyalencar.demo_spring_rev_jpa.service;

import com.lyalencar.demo_spring_rev_jpa.model.Autor;
import com.lyalencar.demo_spring_rev_jpa.projection.AutorInfoProjection;
import com.lyalencar.demo_spring_rev_jpa.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;

    @Transactional
    public Autor criarAutor(Autor autor) {
        autorRepository.save(autor);
        return autor;
    }

    @Transactional
    public Autor atualizar(Autor autor) {
        autorRepository.save(autor);
        return autor;
    }

    @Transactional(readOnly = true)
    public Autor encontrarAutorPorId(Long id) {
        return autorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Autor não encontrado")
        );
    }

    @Transactional
    public void deletarAutor(Autor autor) {
        autorRepository.delete(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> encontrarPorNomeOuSobrenome(String nome) {
        return autorRepository.findAllByNomeOrSobrenome(nome);
    }

    @Transactional(readOnly = true)
    public List<Autor> encontrarPorCargo(String cargo) {
        return autorRepository.findByCargo(cargo);
    }

    @Transactional(readOnly = true)
    public AutorInfoProjection encontrarAutorProjectionPorId(Long id){
        return autorRepository.findAutorProjectionById(id);
    }

    @Transactional(readOnly = true)
    public List<Autor> todosOsAutores() {
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Long totalAutores() {
        return autorRepository.count();
    }
}
