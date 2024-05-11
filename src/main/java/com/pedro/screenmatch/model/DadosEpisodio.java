package com.pedro.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DadosEpisodio(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") int numero,
        @JsonAlias("imdbRating") float avaliacao,
        @JsonAlias("Released") String dataLancamento
) {
}
