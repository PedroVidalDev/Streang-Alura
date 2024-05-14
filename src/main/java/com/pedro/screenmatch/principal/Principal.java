package com.pedro.screenmatch.principal;

import com.pedro.screenmatch.model.DadosEpisodio;
import com.pedro.screenmatch.model.DadosSerie;
import com.pedro.screenmatch.model.DadosTemporada;
import com.pedro.screenmatch.model.Episodio;
import com.pedro.screenmatch.service.ConsumoApi;
import com.pedro.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

        System.out.println("A partir de qual ano voce deseja ver os episodios: ");
        var ano = sc.nextInt();
        sc.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        episodios.stream()
                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                " Episodio: " + e.getNumero() +
                                " Data lancamento: " + e.getDataLancamento().format(formatter)
                ));

        System.out.print("Digite o nome de um episodio: ");
        var trechoTitulo = sc.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();

        if(episodioBuscado.isPresent()){
            System.out.println("Episodio encontrado.");
            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
        } else{
            System.out.println("Episodio nao encontrado.");
        }

        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0)
                .collect(Collectors.groupingBy(
                        Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacoesPorTemporada);
    }
}
