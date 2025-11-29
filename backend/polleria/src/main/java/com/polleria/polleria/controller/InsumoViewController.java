package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Insumo;
import com.polleria.polleria.entity.MovimientoInsumo;
import com.polleria.polleria.entity.TipoMovimiento;
import com.polleria.polleria.service.InsumoService;
import com.polleria.polleria.service.MovimientoInsumoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/insumos")
public class InsumoViewController {

    private final InsumoService insumoService;
    private final MovimientoInsumoService movimientoService;

    public InsumoViewController(InsumoService insumoService, MovimientoInsumoService movimientoService) {
        this.insumoService = insumoService;
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public String listarInsumos(Model model) {
        model.addAttribute("insumos", insumoService.listarInsumos());
        model.addAttribute("insumo", new Insumo());
        model.addAttribute("movimientos", movimientoService.listarUltimos10());
        return "insumos";
    }

    @PostMapping("/guardar")
    public String guardarInsumo(@ModelAttribute Insumo insumo) {
        boolean esNuevo = (insumo.getId() == null);
        double stockAnterior = 0.0;
    
        if (!esNuevo) {
            Insumo insumoAnterior = insumoService.obtenerInsumoPorId(insumo.getId()).orElse(null);
        if (insumoAnterior != null) {
            stockAnterior = insumoAnterior.getStock();
        }
    }
    
    Insumo insumoGuardado = insumoService.guardarInsumo(insumo);
    
    // Registrar movimiento
    if (esNuevo) {
        MovimientoInsumo movimiento = new MovimientoInsumo();
        movimiento.setInsumo(insumoGuardado);
        movimiento.setTipo(TipoMovimiento.ENTRADA);
        movimiento.setCantidad(insumo.getStock());
        movimiento.setMotivo("Stock inicial");
        movimientoService.guardarMovimiento(movimiento);
    } else {
        double diferencia = insumo.getStock() - stockAnterior;
        if (diferencia != 0) {
            MovimientoInsumo movimiento = new MovimientoInsumo();
            movimiento.setInsumo(insumoGuardado);
            movimiento.setTipo(diferencia > 0 ? TipoMovimiento.ENTRADA : TipoMovimiento.SALIDA);
            movimiento.setCantidad(Math.abs(diferencia));
            movimiento.setMotivo(diferencia > 0 ? "Ajuste de inventario (entrada)" : "Ajuste de inventario (salida)");
            movimientoService.guardarMovimiento(movimiento);
        }
    }
    
    return "redirect:/insumos";
}

    @GetMapping("/editar/{id}")
    public String editarInsumo(@PathVariable Long id, Model model) {
        insumoService.obtenerInsumoPorId(id).ifPresent(insumo -> {
            model.addAttribute("insumo", insumo);
        });
        model.addAttribute("insumos", insumoService.listarInsumos());
        model.addAttribute("movimientos", movimientoService.listarUltimos10());
        return "insumos";
    }
}