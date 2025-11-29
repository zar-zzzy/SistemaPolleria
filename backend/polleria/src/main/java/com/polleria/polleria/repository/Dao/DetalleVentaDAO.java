package com.polleria.polleria.repository.dao;

import com.polleria.polleria.entity.DetalleVenta;
import java.util.List;

public interface DetalleVentaDAO {
    List<DetalleVenta> findByVentaId(Long ventaId);

    DetalleVenta save(DetalleVenta detalle);

    void deleteByVentaId(Long ventaId);
}