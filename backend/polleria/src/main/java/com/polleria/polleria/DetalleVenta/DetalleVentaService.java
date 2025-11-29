package com.polleria.polleria.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {
    
    List<DetalleVenta> obtenerDetallesPorVenta(Long ventaId);
    
    DetalleVenta guardarDetalle(DetalleVenta detalle);
    
    void eliminarDetallesPorVenta(Long ventaId);
}