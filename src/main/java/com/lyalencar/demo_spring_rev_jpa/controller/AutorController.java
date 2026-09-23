package com.lyalencar.demo_spring_rev_jpa.controller;

import com.lyalencar.demo_spring_rev_jpa.model.Autor;
import com.lyalencar.demo_spring_rev_jpa.model.InfoAutor;
import com.lyalencar.demo_spring_rev_jpa.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("{id}")
    public ResponseEntity<Autor> obterPorId(@PathVariable Long id) {
        Autor obterAutor = autorService.encontrarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(obterAutor);
    }

    @PutMapping("{id}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long id, @RequestBody Autor autor) {
        Autor obterAutor = autorService.encontrarPorId(id);
        autorService.atualizar(obterAutor);
        return ResponseEntity.status(HttpStatus.OK).body(obterAutor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Long id) {
        Autor obterAutor = autorService.encontrarPorId(id);
        autorService.deletarAutor(obterAutor);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("nomeOrSobrenome")
    public ResponseEntity<List<Autor>> obterPorNomeOuSobrenome(@RequestParam String nome) {
        List<Autor> obterAutor = autorService.encontrarPorNomeOuSobrenome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(obterAutor);
    }

    @PostMapping("{id}/info")
    public ResponseEntity<Autor> criarInfoAutor(@PathVariable Long id, InfoAutor infoAutor) {
        Autor autor = autorService.saveInfoAutor(id, infoAutor);
        return ResponseEntity.status(HttpStatus.OK).body(autor);
    }

    @GetMapping("")
    public ResponseEntity<List<Autor>> encontrarPorCargo(@RequestParam String cargo){
        List<Autor> cargoAutor = autorService.findByCargo(cargo);
        return ResponseEntity.status(HttpStatus.OK).body(cargoAutor);
    }
}
