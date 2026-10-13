package com.lyalencar.demo_spring_rev_jpa.controller;

import com.lyalencar.demo_spring_rev_jpa.model.Post;
import com.lyalencar.demo_spring_rev_jpa.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<List<Post>> salvar(@RequestBody Post post) {
        Post postSalvo = postService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(List.of(postSalvo));
    }

    @GetMapping("categoria/{categoria}/autor/{id}")
    public ResponseEntity<List<Post>> encontrarPorCategoriaEAutor(@PathVariable String categoria, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findAllByCategoriaAndAutorId(categoria, id));
    }

    @GetMapping("autor/nome/{nome}/sobrenome/{sobrenome}")
    public List<Post> encontrarPorAutorNomeOuSobrenome(@PathVariable String nome, @PathVariable String sobrenome) {
        return this.postService.findAllByAutor(nome, sobrenome);
    }

    @GetMapping("titulo/{titulo}")
    public List<Post> encontrarPorTitulo(@PathVariable String titulo) {
        return this.postService.findAllByTitulo(titulo);
    }

    @GetMapping("data-publicacao/{data}")
    public List<Post> encontrarPorDataPublicacao(@PathVariable LocalDate data) {
        return this.postService.findAllByDataPublicacaoMaiorOuIgual(data);
    }

    @GetMapping("sem-data-publicacao")
    public List<Post> encontrarSemData() {
        return this.postService.findAllBySemDataPublicacao();
    }

}
