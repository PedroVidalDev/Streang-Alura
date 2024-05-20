package com.pedro.screenmatch.controller;

import com.pedro.screenmatch.dto.EpisodioDTO;
import com.pedro.screenmatch.dto.SerieDTO;
import com.pedro.screenmatch.model.Serie;
import com.pedro.screenmatch.repository.SerieRepository;
import com.pedro.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/top5")
    public List<SerieDTO> obterTopCinco(){
        return service.obterTopCinco();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id){
        return service.obterPorId(id);
    }

    @GetMapping("{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasAsTemporadas(@PathVariable Long id){
        return service.obterTodasAsTemporadas(id);
    }

    @GetMapping("{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadaPorNumero(@PathVariable Long id, @PathVariable Long numero){
        return service.obterTemporadaPorNumero(id, numero);
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos(){
        return service.obterLancamentos();
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obterPorCategoria(@PathVariable String nomeGenero){
        return service.obterPorGenero(nomeGenero);
    }
}
