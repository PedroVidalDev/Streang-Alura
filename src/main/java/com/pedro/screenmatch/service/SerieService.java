package com.pedro.screenmatch.service;

import com.pedro.screenmatch.dto.EpisodioDTO;
import com.pedro.screenmatch.dto.SerieDTO;
import com.pedro.screenmatch.model.Categoria;
import com.pedro.screenmatch.model.Episodio;
import com.pedro.screenmatch.model.Serie;
import com.pedro.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public SerieDTO obterPorId(Long id){
        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()){
            return new SerieDTO(serie.get());
        }

        return null;
    }

    public List<EpisodioDTO> obterTodasAsTemporadas(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()){
            Serie serieEncontrada = serie.get();

            return serieEncontrada.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e))
                    .collect(Collectors.toList());
        }

        return null;
    }

    public List<EpisodioDTO> obterTemporadaPorNumero(Long id, Long numero) {
        return repository.obterEpisodiosPorTemporada(id, numero).stream()
                .map(e -> new EpisodioDTO(e))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterPorGenero(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);

        return converteDados(repository.findByGenero(categoria));
    }

    public List<EpisodioDTO> obterTopEpisodios(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()){
            return repository.topEpisodiosPorSerie(serie.get())
                    .stream()
                    .map(e -> new EpisodioDTO(e))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<SerieDTO> converteDados(List<Serie> dados){
        return dados
                .stream()
                .map(s -> new SerieDTO(s))
                .collect(Collectors.toList());
    }


}
