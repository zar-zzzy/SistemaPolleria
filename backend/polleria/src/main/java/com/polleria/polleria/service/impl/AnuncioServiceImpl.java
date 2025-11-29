package com.polleria.polleria.service.impl;

import com.polleria.polleria.entity.Anuncio;
import com.polleria.polleria.repository.dao.AnuncioDAO;
import com.polleria.polleria.service.AnuncioService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AnuncioServiceImpl implements AnuncioService {

    private final AnuncioDAO anuncioDAO;

    public AnuncioServiceImpl(AnuncioDAO anuncioDAO) {
        this.anuncioDAO = anuncioDAO;
    }

    @Override
    public List<Anuncio> listarTodos() {
        return anuncioDAO.findAll();
    }

    @Override
    public List<Anuncio> listarActivosHoy() {
        LocalDate hoy = LocalDate.now();
        return anuncioDAO.findByActivoTrueAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(hoy, hoy);
    }

    @Override
    public Optional<Anuncio> buscarPorId(Long id) {
        return anuncioDAO.findById(id);
    }

    @Override
    public Anuncio guardar(Anuncio anuncio) {
        if (anuncio.getId() == null) {
            return anuncioDAO.save(anuncio);
        } else {
            return anuncioDAO.update(anuncio);
        }
    }

    @Override
    public void eliminar(Long id) {
        anuncioDAO.deleteById(id);
    }
}