package com.pedro.screenmatch.principal;

import com.pedro.screenmatch.model.DadosSerie;
import com.pedro.screenmatch.model.DadosTemporada;
import com.pedro.screenmatch.service.ConsumoApi;
import com.pedro.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    }
}
