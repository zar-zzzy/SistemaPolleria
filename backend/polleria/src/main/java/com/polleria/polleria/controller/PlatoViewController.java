package com.polleria.polleria.controller;

import com.polleria.polleria.Categoria.CategoriaService;
import com.polleria.polleria.Plato.Plato;
import com.polleria.polleria.Plato.PlatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/platos")
public class PlatoViewController {

    @Autowired
    private PlatoService platoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarPlatos(Model model) {
        model.addAttribute("platos", platoService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("plato", new Plato());
        return "platos";
    }

    @PostMapping("/guardar")
    public String guardarPlato(@ModelAttribute Plato plato) {
        platoService.guardar(plato);
        return "redirect:/platos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPlato(@PathVariable Long id) {
        try {
            platoService.eliminar(id);
        } catch (Exception e) {
            // Manejar error
        }
        return "redirect:/platos";
    }

    @GetMapping("/editar/{id}")
    public String editarPlato(@PathVariable Long id, Model model) {
        platoService.buscarPorId(id).ifPresent(plato -> {
            model.addAttribute("plato", plato);
        });
        model.addAttribute("platos", platoService.listar());
        model.addAttribute("categorias", categoriaService.listar());
        return "platos";
    }
}