package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Usuario;
import com.polleria.polleria.entity.Anuncio;
import com.polleria.polleria.service.UsuarioService;
import com.polleria.polleria.service.AnuncioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controlador para el panel de administración.
 * Solo accesible por usuarios con rol ADMIN.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AnuncioService anuncioService;

    /**
     * Panel principal del admin.
     */
    @GetMapping
    public String panelAdmin(HttpSession session, Model model) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }
        return "admin/dashboard";
    }

    /**
     * Gestión de usuarios.
     */
    @GetMapping("/usuarios")
    public String gestionUsuarios(HttpSession session, Model model) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("usuario", new Usuario());
        return "admin/usuarios";
    }

    /**
     * Gestión de anuncios.
     */
    @GetMapping("/anuncios")
    public String gestionAnuncios(HttpSession session, Model model) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }
        model.addAttribute("anuncios", anuncioService.listarTodos());
        model.addAttribute("anuncio", new Anuncio());
        return "admin/anuncios";
    }

    /**
     * Guardar anuncio con manejo explícito del checkbox.
     */
    @PostMapping("/anuncios/guardar")
    public String guardarAnuncio(@RequestParam(required = false) Long id,
                                  @RequestParam String titulo,
                                  @RequestParam String mensaje,
                                  @RequestParam(required = false, defaultValue = "false") boolean activo,
                                  @RequestParam String fechaInicio,
                                  @RequestParam String fechaFin,
                                  HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }
        
        // DEBUG: Ver qué valor llega
        System.out.println("===== DEBUG ANUNCIO =====");
        System.out.println("ID: " + id);
        System.out.println("Titulo: " + titulo);
        System.out.println("Activo (checkbox): " + activo);
        System.out.println("Fecha Inicio: " + fechaInicio);
        System.out.println("Fecha Fin: " + fechaFin);
        System.out.println("=========================");
        
        Anuncio anuncio = new Anuncio();
        if (id != null) {
            anuncio.setId(id);
        }
        anuncio.setTitulo(titulo);
        anuncio.setMensaje(mensaje);
        anuncio.setActivo(activo);
        anuncio.setFechaInicio(LocalDate.parse(fechaInicio));
        anuncio.setFechaFin(LocalDate.parse(fechaFin));
        
        System.out.println("Antes de guardar - Activo: " + anuncio.isActivo());
        anuncioService.guardar(anuncio);
        System.out.println("Anuncio guardado exitosamente");
        
        return "redirect:/admin/anuncios";
    }

    /**
     * Cargar anuncio para editar.
     */
    @GetMapping("/anuncios/editar/{id}")
    public String editarAnuncio(@PathVariable Long id, HttpSession session, Model model) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }
        
        anuncioService.buscarPorId(id).ifPresent(anuncio -> {
            model.addAttribute("anuncio", anuncio);
        });
        
        model.addAttribute("anuncios", anuncioService.listarTodos());
        return "admin/anuncios";
    }

    /**
     * Eliminar anuncio.
     */
    @GetMapping("/anuncios/eliminar/{id}")
    public String eliminarAnuncio(@PathVariable Long id, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }
        anuncioService.eliminar(id);
        return "redirect:/admin/anuncios";
    }

    /**
     * Guardar nuevo usuario.
     */
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }
        usuarioService.guardarUsuario(usuario);
        return "redirect:/admin/usuarios";
    }

    /**
     * Eliminar usuario.
     */
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id, HttpSession session) {
        if (!esAdmin(session)) {
            return "redirect:/login";
        }
        usuarioService.eliminarUsuario(id);
        return "redirect:/admin/usuarios";
    }

    /**
     * Verifica si el usuario en sesión es admin.
     */
    private boolean esAdmin(HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        return "ADMIN".equals(rol);
    }
}