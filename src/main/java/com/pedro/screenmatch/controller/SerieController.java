package com.pedro.screenmatch.controller;

import com.pedro.screenmatch.dto.SerieDTO;
import com.pedro.screenmatch.model.Serie;
import com.pedro.screenmatch.repository.SerieRepository;
import com.pedro.screenmatch.service.SerieService;
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

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> obterSeries(){
        return service.obterTodasAsSeries();
    }
}
