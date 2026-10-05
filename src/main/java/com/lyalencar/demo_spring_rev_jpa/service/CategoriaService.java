package com.lyalencar.demo_spring_rev_jpa.service;

import com.lyalencar.demo_spring_rev_jpa.model.Categoria;
import com.lyalencar.demo_spring_rev_jpa.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Transactional
    public List<Categoria> save(List<Categoria> categorias) {
        return categoriaRepository.saveAll(categorias);
    }

    @Transactional(readOnly = true)
    public Categoria findByTitulo(String titulo) {
        return categoriaRepository.findByTitulo(titulo).orElseThrow(
                () -> new RuntimeException("Categoria não encontrada com o título: " + titulo)
        );
    }

    @Transactional(readOnly = true)
    public List<Categoria> findByFirstTitulo(String titulo) {
        List<Categoria> titulos = categoriaRepository.findByTituloStartsWith(titulo);
        if (titulos.isEmpty() || titulo.isBlank()) {
            throw new RuntimeException("Nenhuma categoria encontrada com o título: " + titulo);
        }
        return titulos;
    }

    @Transactional(readOnly = true)
    public List<Categoria> findByTitulos(List<String> titulos) {
        return categoriaRepository.findByTituloIn(titulos);
    }

    @Transactional(readOnly = true)
    public List<Categoria> findAllOrdenandoPorTituloAsc() {
        return categoriaRepository.findAllByOrderByTituloAsc();
    }


}
