package com.polleria.polleria.service;

import com.polleria.polleria.entity.Anuncio;
import com.polleria.polleria.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    public List<Anuncio> listarTodos() {
        return anuncioRepository.findAll();
    }

    public List<Anuncio> listarActivosHoy() {
        LocalDate hoy = LocalDate.now();
        return anuncioRepository.findByActivoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(hoy, hoy);
    }

    public Anuncio guardar(Anuncio anuncio) {
        if (anuncio == null) {
            throw new IllegalArgumentException("El anuncio no puede ser nulo");
        }
        return anuncioRepository.save(anuncio);
    }

    public void eliminar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del anuncio no puede ser nulo");
        }
        anuncioRepository.deleteById(id);
    }
}
