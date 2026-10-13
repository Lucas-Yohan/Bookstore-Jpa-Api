package com.lyalencar.demo_spring_rev_jpa.service;

import com.lyalencar.demo_spring_rev_jpa.model.Autor;
import com.lyalencar.demo_spring_rev_jpa.model.Categoria;
import com.lyalencar.demo_spring_rev_jpa.model.Post;
import com.lyalencar.demo_spring_rev_jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final AutorService autorService;
    private final CategoriaService categoriaService;

    @Transactional
    public Post save(Post post) {
        Autor autor = this.autorService.encontrarAutorPorId(post.getAutor().getId());
        post.setAutor(autor);

        List<String> titulos = post.getCategorias().stream()
                .map(Categoria::getTitulo).collect(Collectors.toList());

        List<Categoria> categorias = this.categoriaService.findByTitulos(titulos);
        post.setCategorias(categorias);
        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByCategoriaAndAutorId(String categoria, Long autorId) {
        return this.postRepository.findByCategoriasTituloAndAutorId(categoria, autorId);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByAutor(String autorNome, String autorSobrenome) {
        return this.postRepository.findByAutorNomeOrAutorSobrenome(autorNome, autorSobrenome);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByTitulo(String titulo) {
        return this.postRepository.findByTituloContainsOrderByAutorNomeAsc(titulo);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByDataPublicacaoMaiorOuIgual(LocalDate dataPublicacao) {
        return this.postRepository.findByDataPublicacaoIsGreaterThanEqual(dataPublicacao);
    }

    @Transactional(readOnly = true)
    public List<Post> findAllBySemDataPublicacao() {
        return this.postRepository.findByDataPublicacaoIsNull();
    }
}
