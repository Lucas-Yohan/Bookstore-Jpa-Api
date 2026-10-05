package com.lyalencar.demo_spring_rev_jpa.service;

import com.lyalencar.demo_spring_rev_jpa.model.Autor;
import com.lyalencar.demo_spring_rev_jpa.model.InfoAutor;
import com.lyalencar.demo_spring_rev_jpa.repository.InfoAutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoAutorService {
    private final InfoAutorRepository infoAutorRepository;
    private final AutorService autorService;

    @Transactional
    public InfoAutor saveInfoAutor(Long id, InfoAutor infoAutor) {
        Autor autor = autorService.encontrarAutorPorId(id);
        autor.setInfoAutor(infoAutor);
        infoAutorRepository.save(infoAutor);
        return infoAutor;
    }

    @Transactional(readOnly = true)
    public InfoAutor findById(Long id){
        InfoAutor info = new InfoAutor();
        info.setId(id);
        return infoAutorRepository.findOne(Example.of(info)).orElseGet(() -> {
            throw new RuntimeException("InfoAutor não encontrado com id: " + id);
        });
    }

    @Transactional(readOnly = true)
    public List<InfoAutor> findAllContainsCargo(String cargo) {
        InfoAutor info = new InfoAutor();
        info.setCargo(cargo);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("cargo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return infoAutorRepository.findAll(Example.of(info, matcher));
    }

    @Transactional(readOnly = true)
    public List<InfoAutor> findAllCargoAndEmpresa(String cargo, String empresa) {
        InfoAutor info = new InfoAutor();
        info.setCargo(cargo);
        info.setBio(empresa);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("cargo", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("bio", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return infoAutorRepository.findAll(Example.of(info, matcher));
    }

    @Transactional(readOnly = true)
    public InfoAutor findFromBio(String bio) {
        InfoAutor info = new InfoAutor();
        info.setBio(bio);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("bio", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        return infoAutorRepository.findBy(
            Example.of(info, matcher),
                query -> query.sortBy(Sort.by("bio").descending()).first()).orElseGet(() -> {
                    throw new RuntimeException("InfoAutor não encontrado com bio: " + bio);
                });
    }

}