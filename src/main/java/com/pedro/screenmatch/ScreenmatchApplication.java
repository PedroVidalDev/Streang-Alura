package com.pedro.screenmatch;

import com.pedro.screenmatch.model.DadosEpisodio;
import com.pedro.screenmatch.model.DadosSerie;
import com.pedro.screenmatch.service.ConsumoApi;
import com.pedro.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		ConsumoApi consumoApi = new ConsumoApi();
		var json = "";

		ConverteDados conversor = new ConverteDados();

		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		DadosSerie dadosSeries = conversor.obterDados(json, DadosSerie.class);

		json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);

		System.out.println(dadosSeries);
		System.out.println(dadosEpisodio);
	}
}
