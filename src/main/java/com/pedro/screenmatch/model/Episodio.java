package com.pedro.screenmatch.model;

import jakarta.persistence.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodios")
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Serie serie;

    private Integer temporada;
    private String titulo;
    private Integer numero;
    private double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer numeroTemporada, DadosEpisodio dados){
        this.temporada = numeroTemporada;
        this.titulo = dados.titulo();
        this.numero = dados.numero();

        try{
            this.avaliacao = Double.parseDouble(dados.avaliacao());
        } catch(NumberFormatException ex){
            this.avaliacao = 0;
        }

        try{
            this.dataLancamento = LocalDate.parse(dados.dataLancamento());
        } catch(DateTimeParseException ex){
            this.dataLancamento = null;
        }
    }

    public Episodio() {

    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Long getId() {
        return id;
    }

    public Serie getSerie() {
        return serie;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    @Override
    public String toString(){
        return STR."Episodio{temporada=\{temporada}, titulo='\{titulo}\{'\''}, numeroEpisodio=\{numero}, avaliacao=\{avaliacao}, dataLancamento=\{dataLancamento}\{'}'}";
    }
}
