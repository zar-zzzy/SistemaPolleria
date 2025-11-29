package com.polleria.polleria.service;

import com.polleria.polleria.entity.DetalleVenta;
import java.util.List;

public interface DetalleVentaService {
    
    List<DetalleVenta> obtenerDetallesPorVenta(Long ventaId);
    
    DetalleVenta guardarDetalle(DetalleVenta detalle);
    
    void eliminarDetallesPorVenta(Long ventaId);
}