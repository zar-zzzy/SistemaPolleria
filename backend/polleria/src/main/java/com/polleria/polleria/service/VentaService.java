package com.polleria.polleria.service;

import com.polleria.polleria.entity.Venta;
import com.polleria.polleria.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta actualizarVenta(Long id, Venta ventaActualizada) {
        return ventaRepository.findById(id).map(venta -> {
            venta.setFecha(ventaActualizada.getFecha());
            venta.setCliente(ventaActualizada.getCliente());
            venta.setTotal(ventaActualizada.getTotal());
            return ventaRepository.save(venta);
        }).orElse(null);
    }

    public void eliminarVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}
