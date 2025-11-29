package com.polleria.polleria.Anuncio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnuncioDAO {
    List<Anuncio> findAll();
    Optional<Anuncio> findById(Long id);
    List<Anuncio> findByActivoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate inicio, LocalDate fin);
    Anuncio save(Anuncio anuncio);
    Anuncio update(Anuncio anuncio);
    void deleteById(Long id);
}