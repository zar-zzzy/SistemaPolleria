package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Venta;
import com.polleria.polleria.entity.DetalleVenta;
import com.polleria.polleria.repository.VentaRepository;
import com.polleria.polleria.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {
    
    @Autowired
    private VentaRepository ventaRepository;
    
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    
    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }
    
    @GetMapping("/hoy")
    public List<Venta> getVentasHoy() {
        return ventaRepository.findVentasHoy();
    }
    
    @GetMapping("/semana")
    public List<Venta> getVentasSemana() {
        LocalDateTime inicioSemana = LocalDate.now().minusDays(7).atStartOfDay();
        LocalDateTime finSemana = LocalDateTime.now();
        return ventaRepository.findVentasPorRango(inicioSemana, finSemana);
    }
    
    @PostMapping
    public Venta createVenta(@RequestBody Venta venta) {
        return ventaRepository.save(venta);
    }
    
    @GetMapping("/{id}")
    public Venta getVentaById(@PathVariable Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }
    
    @GetMapping("/reportes/plato-mas-vendido")
    public List<Object[]> getPlatoMasVendido() {
        return detalleVentaRepository.findPlatoMasVendido();
    }
}
