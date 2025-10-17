package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Venta;
import com.polleria.polleria.repository.VentaRepository;
import com.polleria.polleria.repository.DetalleVentaRepository;
import com.polleria.polleria.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private InsumoRepository insumoRepository;

    @GetMapping("/resumen-ventas")
    public Map<String, Object> getResumenVentas() {
        Map<String, Object> resumen = new HashMap<>();

        // ✅ Ventas de hoy — corregido con parámetros inicio y fin
        LocalDateTime inicio = LocalDate.now().atStartOfDay();
        LocalDateTime fin = inicio.plusDays(1);
        List<Venta> ventasHoy = ventaRepository.findVentasHoy(inicio, fin);
        resumen.put("ventasHoy", ventasHoy.size());

        // Ventas de la semana
        LocalDateTime inicioSemana = LocalDate.now().minusDays(7).atStartOfDay();
        LocalDateTime finSemana = LocalDateTime.now();
        List<Venta> ventasSemana = ventaRepository.findVentasPorRango(inicioSemana, finSemana);
        resumen.put("ventasSemana", ventasSemana.size());

        // Plato más vendido
        List<Object[]> platoMasVendido = detalleVentaRepository.findPlatoMasVendido();
        if (!platoMasVendido.isEmpty()) {
            resumen.put("platoMasVendido", platoMasVendido.get(0)[0]);
        } else {
            resumen.put("platoMasVendido", "No hay ventas");
        }

        return resumen;
    }

    @GetMapping("/stock-insumos")
    public List<Object[]> getStockInsumos() {
        return insumoRepository.findAll().stream()
                .map(insumo -> new Object[]{
                        insumo.getIdInsumo(),
                        insumo.getNombre(),
                        insumo.getUnidadMedida(),
                        insumo.getCantidad()
                })
                .toList();
    }

    @GetMapping("/ventas-por-fecha")
    public List<Venta> getVentasPorFecha(@RequestParam String fecha) {
        LocalDate fechaConsulta = LocalDate.parse(fecha);
        LocalDateTime inicioDia = fechaConsulta.atStartOfDay();
        LocalDateTime finDia = fechaConsulta.plusDays(1).atStartOfDay();
        return ventaRepository.findVentasPorRango(inicioDia, finDia);
    }
}
