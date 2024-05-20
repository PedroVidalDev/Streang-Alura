package com.pedro.screenmatch.service;

import com.pedro.screenmatch.dto.SerieDTO;
import com.pedro.screenmatch.model.Serie;
import com.pedro.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obterTodasAsSeries(){
        return converteDados(repository.findAll());
    }

    public List<SerieDTO> obterTopCinco(){
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteDados(repository.findTop5ByOrderByEpisodiosDataLancamentoDesc());
    }

    public List<SerieDTO> converteDados(List<Serie> dados){
        return dados
                .stream()
                .map(s -> new SerieDTO(s))
                .collect(Collectors.toList());
    }

}
