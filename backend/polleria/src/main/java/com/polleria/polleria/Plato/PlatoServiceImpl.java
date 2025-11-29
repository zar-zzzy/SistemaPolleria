package com.polleria.polleria.Plato;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoServiceImpl implements PlatoService {

    private final PlatoDAO platoDAO;

    public PlatoServiceImpl(PlatoDAO platoDAO) {
        this.platoDAO = platoDAO;
    }

    @Override
    public List<Plato> listar() {
        return platoDAO.findAll();
    }

    @Override
    public Optional<Plato> buscarPorId(Long id) {
        return platoDAO.findById(id);
    }

    @Override
    public Plato guardar(Plato plato) {
        if (plato.getIdPlato() == null) {
            return platoDAO.save(plato);
        } else {
            return platoDAO.update(plato);
        }
    }

    @Override
    public void eliminar(Long id) {
        platoDAO.deleteById(id);
    }
}