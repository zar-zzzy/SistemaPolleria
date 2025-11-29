package com.polleria.polleria.service;

import com.polleria.polleria.entity.Insumo;
import java.util.List;
import java.util.Optional;

public interface InsumoService {
    List<Insumo> listarInsumos();
    Optional<Insumo> obtenerInsumoPorId(Long id);
    Insumo guardarInsumo(Insumo insumo);
    void eliminarInsumo(Long id);
    int contarInsumos();
}