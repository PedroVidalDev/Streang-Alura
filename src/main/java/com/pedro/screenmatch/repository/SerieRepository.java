package com.pedro.screenmatch.repository;

import com.pedro.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}