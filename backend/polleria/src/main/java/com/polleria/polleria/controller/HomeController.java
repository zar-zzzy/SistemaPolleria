package com.polleria.polleria.controller;

import com.polleria.polleria.service.AnuncioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para las páginas principales del sistema.
 * Maneja la navegación general de la aplicación.
 */
@Controller
public class HomeController {

    @Autowired
    private AnuncioService anuncioService;

    /**
     * Página de inicio.
     * GET /
     */
    @GetMapping("/")
    public String home(HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (rol == null) {
            return "redirect:/login";
        }

        // ADMIN va a gestión de usuarios, TRABAJADOR al inicio operativo
        if ("ADMIN".equals(rol)) {
            return "redirect:/admin/usuarios";
        }
        return "index";
    }

    /** Página de contacto - Solo TRABAJADOR */
    @GetMapping("/contacto")
    public String contacto(HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }
        return "contacto";
    }

    /**
     * Página de publicidad - Solo TRABAJADOR
     */
    @GetMapping("/publicidad")
    public String publicidad(HttpSession session, Model model) {
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }
        model.addAttribute("anuncios", anuncioService.listarActivosHoy());
        return "publicidad";
    }

    /** Página de anuncios para TRABAJADOR (solo lectura). */
    @GetMapping("/anuncios")
    public String anuncios(HttpSession session, Model model) {
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }
        model.addAttribute("anuncios", anuncioService.listarActivosHoy());
        return "publicidad";
    }
    
    // ❌ ELIMINADO: @GetMapping("/reportes") porque ahora lo maneja ReporteController
    // ❌ ELIMINADO: @GetMapping("/reportes-dia") ya no se usa
}