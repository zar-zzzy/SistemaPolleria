package com.polleria.polleria.service;

import com.polleria.polleria.entity.DetalleVenta;
import com.polleria.polleria.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.findAll();
    }

    public Optional<DetalleVenta> obtenerDetallePorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public DetalleVenta guardarDetalle(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }

    public DetalleVenta actualizarDetalle(Long id, DetalleVenta detalleActualizado) {
        return detalleVentaRepository.findById(id).map(detalle -> {
            detalle.setCantidad(detalleActualizado.getCantidad());
            detalle.setPrecio(detalleActualizado.getPrecio());
            detalle.setPlato(detalleActualizado.getPlato());
            detalle.setVenta(detalleActualizado.getVenta());
            return detalleVentaRepository.save(detalle);
        }).orElse(null);
    }

    public void eliminarDetalle(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}
