package com.polleria.polleria.DetalleVenta;

import java.util.List;

public interface DetalleVentaDAO {
    List<DetalleVenta> findByVentaId(Long ventaId);
    DetalleVenta save(DetalleVenta detalle);
    void deleteByVentaId(Long ventaId);
}