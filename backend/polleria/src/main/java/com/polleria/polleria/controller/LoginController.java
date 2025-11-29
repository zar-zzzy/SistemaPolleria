package com.polleria.polleria.controller;

import com.polleria.polleria.Usuario.Usuario;
import com.polleria.polleria.Usuario.UsuarioService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controlador para autenticación y gestión de sesiones.
 */
@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Muestra la página de login.
     */
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    /**
     * Procesa el login y crea la sesión.
     */
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.autenticar(username, password);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            session.setAttribute("usuario", usuario);
            session.setAttribute("rol", usuario.getRol().name());

            // Redirigir según el rol
            if ("ADMIN".equals(usuario.getRol().name())) {
                return "redirect:/admin/usuarios";  // ✅ CORREGIDO: ADMIN va a gestión de usuarios
            } else {
                return "redirect:/";  // ✅ TRABAJADOR va al inicio
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    /**
     * Cierra la sesión.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}