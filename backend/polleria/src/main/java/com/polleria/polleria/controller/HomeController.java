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

        // ADMIN va directo a gestión de anuncios, TRABAJADOR al inicio operativo
        if ("ADMIN".equals(rol)) {
            return "redirect:/admin/anuncios";
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
     * Página de publicidad - Solo ADMIN. Si es TRABAJADOR, lo mandamos a anuncios
     * de solo lectura.
     */
    @GetMapping("/publicidad")
    public String publicidad(HttpSession session, Model model) {
        String rol = (String) session.getAttribute("rol");
        if ("ADMIN".equals(rol)) {
            model.addAttribute("anuncios", anuncioService.listarActivosHoy());
            return "publicidad";
        }
        if ("TRABAJADOR".equals(rol)) {
            return "redirect:/anuncios";
        }
        return "redirect:/login";
    }

    /** Página de reportes (métricas) - Solo ADMIN */
    @GetMapping("/reportes")
    public String reportes(HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (!"ADMIN".equals(rol)) {
            return "redirect:/login";
        }
        return "reportes";
    }

    /** Página de reportes SOLO del día - Solo TRABAJADOR */
    @GetMapping("/reportes-dia")
    public String reportesDia(HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }
        return "reportes-dia";
    }

    /** Página de ventas - Solo TRABAJADOR */
    @GetMapping("/ventas")
    public String ventas(HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }
        return "ventas";
    }

    /** Página de anuncios para TRABAJADOR (solo lectura). */
    @GetMapping("/anuncios")
    public String anuncios(HttpSession session, Model model) {
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }
        model.addAttribute("anuncios", anuncioService.listarActivosHoy());
        // reutilizamos la misma plantilla de publicidad, pero es vista del trabajador
        return "publicidad";
    }
}
