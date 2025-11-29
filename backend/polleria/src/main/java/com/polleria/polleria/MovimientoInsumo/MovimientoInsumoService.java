package com.polleria.polleria.MovimientoInsumo;

import java.util.List;

public interface MovimientoInsumoService {
    List<MovimientoInsumo> listarTodos();
    List<MovimientoInsumo> listarUltimos10();
    List<MovimientoInsumo> listarPorInsumo(Long insumoId);
    MovimientoInsumo guardarMovimiento(MovimientoInsumo movimiento);
}