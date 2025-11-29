package com.polleria.polleria.service.impl;

import com.polleria.polleria.entity.Insumo;
import com.polleria.polleria.repository.dao.InsumoDAO;
import com.polleria.polleria.service.InsumoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsumoServiceImpl implements InsumoService {

    private final InsumoDAO insumoDAO;

    public InsumoServiceImpl(InsumoDAO insumoDAO) {
        this.insumoDAO = insumoDAO;
    }

    @Override
    public List<Insumo> listarInsumos() {
        return insumoDAO.findAll();
    }

    @Override
    public Optional<Insumo> obtenerInsumoPorId(Long id) {
        return insumoDAO.findById(id);
    }

    @Override
    public Insumo guardarInsumo(Insumo insumo) {
        if (insumo.getId() == null) {
            return insumoDAO.save(insumo);
        } else {
            return insumoDAO.update(insumo);
        }
    }

    @Override
    public void eliminarInsumo(Long id) {
        insumoDAO.deleteById(id);
    }

    @Override
    public int contarInsumos() {
        return insumoDAO.count();
    }
}