package com.lyalencar.demo_spring_rev_jpa.service;

import com.lyalencar.demo_spring_rev_jpa.model.Autor;
import com.lyalencar.demo_spring_rev_jpa.model.InfoAutor;
import com.lyalencar.demo_spring_rev_jpa.repository.InfoAutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InfoAutorService {
    private final InfoAutorRepository infoAutorRepository;
    private final AutorService autorService;

    @Transactional
    public InfoAutor saveInfoAutor(Long id, InfoAutor infoAutor) {
        Autor autor = autorService.encontrarPorId(id);
        InfoAutor novaInfo = infoAutorRepository.save(infoAutor);
        autor.setInfoAutor(novaInfo);
        infoAutorRepository.save(novaInfo);
        return novaInfo;
    }
}
