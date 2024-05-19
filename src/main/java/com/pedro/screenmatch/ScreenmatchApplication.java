package com.pedro.screenmatch;

import com.pedro.screenmatch.model.DadosEpisodio;
import com.pedro.screenmatch.model.DadosSerie;
import com.pedro.screenmatch.model.DadosTemporada;
import com.pedro.screenmatch.principal.Principal;
import com.pedro.screenmatch.repository.SerieRepository;
import com.pedro.screenmatch.service.ConsumoApi;
import com.pedro.screenmatch.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

}
