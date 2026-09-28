package com.lyalencar.demo_spring_rev_jpa.controller;

import com.lyalencar.demo_spring_rev_jpa.model.InfoAutor;
import com.lyalencar.demo_spring_rev_jpa.service.InfoAutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("info-autores")
@RequiredArgsConstructor
public class InfoAutorController {

    private final InfoAutorService infoAutorService;

    @PostMapping("{id}/info")
    public ResponseEntity<InfoAutor> criarInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor) {
        InfoAutor novaInfo = infoAutorService.saveInfoAutor(id, infoAutor);
        return ResponseEntity.status(HttpStatus.OK).body(novaInfo);
    }

}
