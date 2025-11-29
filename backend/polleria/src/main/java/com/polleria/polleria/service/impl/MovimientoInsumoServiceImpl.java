package com.polleria.polleria.service.impl;

import com.polleria.polleria.entity.MovimientoInsumo;
import com.polleria.polleria.repository.Dao.MovimientoInsumoDAO;
import com.polleria.polleria.service.MovimientoInsumoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoInsumoServiceImpl implements MovimientoInsumoService {

    private final MovimientoInsumoDAO movimientoInsumoDAO;

    public MovimientoInsumoServiceImpl(MovimientoInsumoDAO movimientoInsumoDAO) {
        this.movimientoInsumoDAO = movimientoInsumoDAO;
    }

    @Override
    public List<MovimientoInsumo> listarTodos() {
        return movimientoInsumoDAO.findAll();
    }

    @Override
    public List<MovimientoInsumo> listarUltimos10() {
        return movimientoInsumoDAO.findTop10ByOrderByFechaDesc();
    }

    @Override
    public List<MovimientoInsumo> listarPorInsumo(Long insumoId) {
        return movimientoInsumoDAO.findByInsumoId(insumoId);
    }

    @Override
    public MovimientoInsumo guardarMovimiento(MovimientoInsumo movimiento) {
        return movimientoInsumoDAO.save(movimiento);
    }
}