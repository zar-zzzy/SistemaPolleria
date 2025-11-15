package com.polleria.polleria.service;

import com.polleria.polleria.entity.MovimientoInsumo;
import com.polleria.polleria.repository.MovimientoInsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio para gestión de movimientos de insumos.
 */
@Service
public class MovimientoInsumoService {

    @Autowired
    private MovimientoInsumoRepository movimientoRepository;

    public List<MovimientoInsumo> listarTodos() {
        return movimientoRepository.findAll();
    }

    public List<MovimientoInsumo> listarUltimos10() {
        return movimientoRepository.findTop10ByOrderByFechaDesc();
    }

    public List<MovimientoInsumo> listarPorInsumo(Long insumoId) {
        return movimientoRepository.findByInsumoIdOrderByFechaDesc(insumoId);
    }

    public MovimientoInsumo guardarMovimiento(MovimientoInsumo movimiento) {
        return movimientoRepository.save(movimiento);
    }
}
