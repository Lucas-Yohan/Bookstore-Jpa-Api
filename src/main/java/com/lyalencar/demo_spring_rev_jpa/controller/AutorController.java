package com.lyalencar.demo_spring_rev_jpa.controller;

import com.lyalencar.demo_spring_rev_jpa.model.Autor;
import com.lyalencar.demo_spring_rev_jpa.projection.AutorInfoProjection;
import com.lyalencar.demo_spring_rev_jpa.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autores")
public class AutorController {
    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<Autor> salvarAutor(@RequestBody Autor autor) {
        Autor criarAutor = autorService.criarAutor(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarAutor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obterPorId(@PathVariable Long id) {
        Autor obterAutor = autorService.encontrarAutorPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(obterAutor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        Autor obterAutor = autorService.encontrarAutorPorId(id);
        autorService.atualizar(obterAutor);
        return ResponseEntity.status(HttpStatus.OK).body(obterAutor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
        Autor obterAutor = autorService.encontrarAutorPorId(id);
        autorService.deletarAutor(obterAutor);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/nomeOrSobrenome")
    public ResponseEntity<List<Autor>> obterPorNomeOuSobrenome(@RequestParam String nome) {
        List<Autor> obterAutor = autorService.encontrarPorNomeOuSobrenome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(obterAutor);
    }

    @GetMapping("/cargo")
    public ResponseEntity<List<Autor>> obterPorCargo(@RequestParam String cargo) {
        List<Autor> obterAutor = autorService.encontrarPorCargo(cargo);
        return ResponseEntity.status(HttpStatus.OK).body(obterAutor);
    }

    @GetMapping("/autor-info")
    public ResponseEntity<AutorInfoProjection> encontrarAutorProjectionPorId(@RequestParam Long id){
        AutorInfoProjection autorInfo = autorService.encontrarAutorProjectionPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(autorInfo);
    }

    @GetMapping("/autores-list")
    public ResponseEntity<List<Autor>> todosOsAutores(){
        List<Autor> todosAutores = autorService.todosOsAutores();
        return ResponseEntity.status(HttpStatus.OK).body(todosAutores);
    }

    @GetMapping("/total-autores")
    public ResponseEntity<Long> totalAutores(){
        Long total = autorService.totalAutores();
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }
}
