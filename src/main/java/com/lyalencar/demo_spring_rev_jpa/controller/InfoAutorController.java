package com.lyalencar.demo_spring_rev_jpa.controller;

import com.lyalencar.demo_spring_rev_jpa.model.InfoAutor;
import com.lyalencar.demo_spring_rev_jpa.service.InfoAutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("info-autores")
@RequiredArgsConstructor
public class InfoAutorController {

    private final InfoAutorService infoAutorService;

    @PostMapping("{id}/info")
    public ResponseEntity<InfoAutor> criarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
        InfoAutor novaInfo = infoAutorService.saveInfoAutor(id, infoAutor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaInfo);
    }

    @GetMapping("{id}")
    public ResponseEntity<InfoAutor> encontrarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(infoAutorService.findById(id));
    }

    @GetMapping("/cargo")
    public ResponseEntity<List<InfoAutor>> encontrarTodosPorCargo(@RequestParam String cargo) {
        return ResponseEntity.ok(infoAutorService.findAllContainsCargo(cargo));
    }

    @GetMapping("/cargo/{cargo}/empresa/{empresa}")
    public ResponseEntity<List<InfoAutor>> encontrarPorCargoEEmpresa(@PathVariable String cargo, @PathVariable String empresa) {
        return ResponseEntity.ok(infoAutorService.findAllCargoAndEmpresa(cargo, empresa));
    }

    @GetMapping("/bio/{bio}")
        public ResponseEntity<InfoAutor> buscarPorBio(@PathVariable String bio) {
            return ResponseEntity.ok(infoAutorService.findFromBio(bio));
    }

}
