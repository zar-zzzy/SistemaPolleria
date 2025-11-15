package com.polleria.polleria.repository;

import com.polleria.polleria.entity.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

    List<Anuncio> findByActivoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate inicio,
            LocalDate fin);
}
