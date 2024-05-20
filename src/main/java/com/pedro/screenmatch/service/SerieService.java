package com.pedro.screenmatch.service;

import com.pedro.screenmatch.dto.SerieDTO;
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
        return repository.findAll()
                .stream()
                .map(s -> new SerieDTO(s))
                .collect(Collectors.toList());
    }
}