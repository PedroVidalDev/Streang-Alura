package com.pedro.screenmatch.dto;

import com.pedro.screenmatch.model.Categoria;
import com.pedro.screenmatch.model.Serie;

public record SerieDTO (
    Long id,
    String titulo,
    Integer totalTemporadas,
    Double avaliacao,
    Categoria genero,
    String atores,
    String poster,
    String sinopse
){
    public SerieDTO(Serie dados){
        this(dados.getId(), dados.getTitulo(), dados.getTotalTemporadas(), dados.getAvaliacao(), dados.getGenero(), dados.getAtores(), dados.getPoster(), dados.getSinopse());
    }
};
