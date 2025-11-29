package com.polleria.polleria.DetalleVenta;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaDAO detalleVentaDAO;

    public DetalleVentaServiceImpl(DetalleVentaDAO detalleVentaDAO) {
        this.detalleVentaDAO = detalleVentaDAO;
    }

    @Override
    public List<DetalleVenta> obtenerDetallesPorVenta(Long ventaId) {
        return detalleVentaDAO.findByVentaId(ventaId);
    }

    @Override
    public DetalleVenta guardarDetalle(DetalleVenta detalle) {
        // Calcular el subtotal automáticamente
        detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecio());
        return detalleVentaDAO.save(detalle);
    }

    @Override
    public void eliminarDetallesPorVenta(Long ventaId) {
        detalleVentaDAO.deleteByVentaId(ventaId);
    }
}