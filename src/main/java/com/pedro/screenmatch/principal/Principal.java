package com.pedro.screenmatch.principal;

import com.pedro.screenmatch.model.DadosEpisodio;
import com.pedro.screenmatch.model.DadosSerie;
import com.pedro.screenmatch.model.DadosTemporada;
import com.pedro.screenmatch.model.Episodio;
import com.pedro.screenmatch.service.ConsumoApi;
import com.pedro.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner sc = new Scanner(System.in);

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){
        String nome;
        System.out.print("Digite o nome da serie: ");
        nome = sc.nextLine();

        String enderecoCompleto = ENDERECO + nome.replaceAll(" ", "+") + API_KEY;
        var json = consumoApi.obterDados(enderecoCompleto);
        DadosSerie dadosSeries = conversor.obterDados(json, DadosSerie.class);

        System.out.println(dadosSeries);
        List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i = 1; i<= dadosSeries.totalTemporadas(); i++){
			json = consumoApi.obterDados(ENDERECO + nome.replaceAll(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);

//        for(int i = 0; i < dadosSeries.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for(int j = 0; j < episodiosTemporada.size(); j++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                    .map(d -> new Episodio(t.numero(), d))
                )
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);
    }
}
