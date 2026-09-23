package com.lyalencar.demo_spring_rev_jpa.service;

import com.lyalencar.demo_spring_rev_jpa.model.Autor;
import com.lyalencar.demo_spring_rev_jpa.model.InfoAutor;
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
    public Autor encontrarPorId(Long id) {
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

    @Transactional()
    public Autor saveInfoAutor(Long id, InfoAutor infoAutor) {
        return null;
    }

    @Transactional(readOnly = true)
    public List<Autor> findByCargo(String cargo){
        return autorRepository.findByCargo(cargo);
    }
}
