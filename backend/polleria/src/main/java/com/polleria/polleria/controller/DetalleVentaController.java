package com.polleria.polleria.controller;

import com.polleria.polleria.entity.DetalleVenta;
import com.polleria.polleria.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalle-ventas")
@CrossOrigin(origins = "*")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVenta> listarDetalles() {
        return detalleVentaService.listarDetalles();
    }

    @GetMapping("/{id}")
    public Optional<DetalleVenta> obtenerDetalle(@PathVariable Long id) {
        return detalleVentaService.obtenerDetallePorId(id);
    }

    @PostMapping
    public DetalleVenta crearDetalle(@RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.guardarDetalle(detalleVenta);
    }

    @PutMapping("/{id}")
    public DetalleVenta actualizarDetalle(@PathVariable Long id, @RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.actualizarDetalle(id, detalleVenta);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetalle(@PathVariable Long id) {
        detalleVentaService.eliminarDetalle(id);
    }
}
