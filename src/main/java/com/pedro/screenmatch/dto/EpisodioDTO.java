package com.pedro.screenmatch.dto;

import com.pedro.screenmatch.model.Episodio;
import com.pedro.screenmatch.model.Serie;

public record EpisodioDTO(
        int temporada,
        int numeroEpisodio,
        String titulo
) {
    public EpisodioDTO(Episodio dados){
        this(dados.getTemporada(), dados.getNumero(), dados.getTitulo());
    }
}
