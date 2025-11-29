package com.polleria.polleria.controller;

import com.polleria.polleria.DetalleVenta.DetalleVenta;
import com.polleria.polleria.Plato.Plato;
import com.polleria.polleria.Plato.PlatoService;
import com.polleria.polleria.Venta.Venta;
import com.polleria.polleria.Venta.VentaService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final PlatoService platoService;

    public VentaController(VentaService ventaService, PlatoService platoService) {
        this.ventaService = ventaService;
        this.platoService = platoService;
    }

    @GetMapping
    public String mostrarVentas(HttpSession session, Model model) {
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }

        model.addAttribute("platos", platoService.listar());
        model.addAttribute("ventas", ventaService.listarVentas());
        return "ventas";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@RequestParam(required = false) String cliente,
                                @RequestParam Long idPlato,
                                @RequestParam Integer cantidad,
                                @RequestParam String metodoPago,
                                @RequestParam(required = false) String observaciones,
                                HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }

        Plato plato = platoService.buscarPorId(idPlato).orElse(null);
        if (plato == null) {
            return "redirect:/ventas?error=plato_no_encontrado";
        }

        Venta venta = new Venta();
        venta.setCliente(cliente != null && !cliente.isEmpty() ? cliente : "Cliente Anónimo");
        venta.setFecha(LocalDateTime.now());
        venta.setMetodoPago(metodoPago);

        DetalleVenta detalle = new DetalleVenta();
        detalle.setPlato(plato);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(plato.getPrecio());
        detalle.setSubtotal(plato.getPrecio() * cantidad);
        detalle.setVenta(venta);

        List<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(detalle);
        venta.setDetalles(detalles);

        venta.setTotal(detalle.getSubtotal());

        ventaService.guardarVenta(venta);

        return "redirect:/ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id, HttpSession session) {
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }

        ventaService.eliminarVenta(id);
        return "redirect:/ventas";
    }
}