package com.polleria.polleria.service;

import com.polleria.polleria.entity.Plato;
import com.polleria.polleria.repository.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    public List<Plato> listar() {
        return platoRepository.findAll();
    }

    public Optional<Plato> buscarPorId(Integer id) {
        return platoRepository.findById(id);
    }

    public Plato guardar(Plato plato) {
        return platoRepository.save(plato);
    }

    public void eliminar(Integer id) {
        platoRepository.deleteById(id);
    }
}
