package com.polleria.polleria.MovimientoInsumo;

import java.util.List;

public interface MovimientoInsumoDAO {
    List<MovimientoInsumo> findAll();
    List<MovimientoInsumo> findTop10ByOrderByFechaDesc();
    List<MovimientoInsumo> findByInsumoId(Long insumoId);
    MovimientoInsumo save(MovimientoInsumo movimiento);
}