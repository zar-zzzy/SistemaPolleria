package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Insumo;
import com.polleria.polleria.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/insumos")
public class InsumoViewController {

    @Autowired
    private InsumoService insumoService;

    // Mostrar lista de insumos
    @GetMapping
    public String listarInsumos(Model model) {
        model.addAttribute("insumos", insumoService.listarInsumos());
        model.addAttribute("insumo", new Insumo());
        return "insumos";
    }

    // Crear nuevo insumo
    @PostMapping("/guardar")
    public String guardarInsumo(@ModelAttribute Insumo insumo) {
        insumoService.guardarInsumo(insumo);
        return "redirect:/insumos";
    }

    // Eliminar insumo
    @GetMapping("/eliminar/{id}")
    public String eliminarInsumo(@PathVariable Long id) {
        try {
            insumoService.eliminarInsumo(id);
        } catch (Exception e) {
            // Manejar error si está en uso
        }
        return "redirect:/insumos";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editarInsumo(@PathVariable Long id, Model model) {
        insumoService.obtenerInsumoPorId(id).ifPresent(insumo -> {
            model.addAttribute("insumo", insumo);
        });
        model.addAttribute("insumos", insumoService.listarInsumos());
        return "insumos";
    }
}
