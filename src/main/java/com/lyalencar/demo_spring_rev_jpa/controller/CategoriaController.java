package com.lyalencar.demo_spring_rev_jpa.controller;

import com.lyalencar.demo_spring_rev_jpa.model.Categoria;
import com.lyalencar.demo_spring_rev_jpa.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<List<Categoria>> salvarCategorias(@RequestBody List<Categoria> categorias){
        List<Categoria> listCategorias = categoriaService.save(categorias);
        return ResponseEntity.status(HttpStatus.CREATED).body(listCategorias);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Categoria> buscarCategoriaPorTitulo(@PathVariable String titulo){
        Categoria categoria = categoriaService.findByTitulo(titulo);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/titulo/inicio/{titulo}")
    public ResponseEntity<List<Categoria>> buscarPeloPrimeiroTitulo(@PathVariable String titulo){
        List<Categoria> categorias = categoriaService.findByFirstTitulo(titulo);
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/titulos")
    public ResponseEntity<List<Categoria>> buscarPorTitulos(@RequestParam(name = "t") List<String> titulos){
        return ResponseEntity.ok(categoriaService.findByTitulos(titulos));
    }

    @GetMapping()
    public ResponseEntity<List<Categoria>> buscarTodosOsTituloAscendentes(){
        return ResponseEntity.ok(categoriaService.findAllOrdenandoPorTituloAsc());
    }

}
