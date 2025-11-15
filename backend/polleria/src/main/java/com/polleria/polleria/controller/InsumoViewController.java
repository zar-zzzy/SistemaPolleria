package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Insumo;
import com.polleria.polleria.service.InsumoService;
import com.polleria.polleria.service.MovimientoInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC para la gestión de insumos.
 * Maneja las peticiones HTTP y retorna vistas Thymeleaf.
 */
@Controller
@RequestMapping("/insumos")
public class InsumoViewController {

    @Autowired
    private InsumoService insumoService;

    @Autowired
    private MovimientoInsumoService movimientoService;

    /**
     * Muestra la página de insumos con la lista completa y últimos movimientos.
     * GET /insumos
     */
    @GetMapping
    public String listarInsumos(Model model) {
        model.addAttribute("insumos", insumoService.listarInsumos());
        model.addAttribute("insumo", new Insumo());
        model.addAttribute("movimientos", movimientoService.listarUltimos10());
        return "insumos";
    }

    /**
     * Guarda un nuevo insumo o actualiza uno existente.
     * POST /insumos/guardar
     */
    @PostMapping("/guardar")
    public String guardarInsumo(@ModelAttribute Insumo insumo) {
        insumoService.guardarInsumo(insumo);
        return "redirect:/insumos";
    }

    /**
     * Elimina un insumo por su ID.
     * GET /insumos/eliminar/{id}
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarInsumo(@PathVariable Long id) {
        try {
            insumoService.eliminarInsumo(id);
        } catch (Exception e) {
            // Manejar error si está en uso
        }
        return "redirect:/insumos";
    }

    /**
     * Carga un insumo en el formulario para editarlo.
     * GET /insumos/editar/{id}
     */
    @GetMapping("/editar/{id}")
    public String editarInsumo(@PathVariable Long id, Model model) {
        insumoService.obtenerInsumoPorId(id).ifPresent(insumo -> {
            model.addAttribute("insumo", insumo);
        });
        model.addAttribute("insumos", insumoService.listarInsumos());
        return "insumos";
    }
}
