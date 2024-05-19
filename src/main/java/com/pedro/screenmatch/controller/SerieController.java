package com.pedro.screenmatch.controller;

import com.pedro.screenmatch.dto.SerieDTO;
import com.pedro.screenmatch.model.Serie;
import com.pedro.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieRepository repository;

    @GetMapping
    public List<SerieDTO> obterSeries(){
        return repository.findAll()
                .stream()
                .map(s -> new SerieDTO(s))
                .collect(Collectors.toList());
    }
}
