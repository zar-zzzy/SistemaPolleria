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

/*
 * ============================================================
 * FLUJO: REGISTRAR UNA VENTA
 * ============================================================
 * 
 * 1. JSP (ventas.jsp)
 *    - Usuario llena formulario → submit a /ventas/guardar
 * 
 * 2. Controller (VentaController, método guardarVenta)
 *    - Valida rol TRABAJADOR
 *    - Busca el plato seleccionado
 *    - Crea objetos Venta + DetalleVenta
 *    - Llama ventaService.guardarVenta(venta)
 * 
 * 3. Service (VentaServiceImpl)
 *    - ventaDAO.save(venta) → guarda en tabla ventas
 *    - detalleVentaDAO.save(detalle) → guarda en tabla detalle_venta
 * 
 * 4. Repository (VentaRepository / DetalleVentaRepository)
 *    - Ejecuta INSERT con JdbcTemplate
 * 
 * 5. Redirect
 *    - return "redirect:/ventas" → recarga página con listarVentas()
 * ============================================================
 */
@Controller
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;
    private final PlatoService platoService;

    public VentaController(VentaService ventaService, PlatoService platoService) {
    public VentaController(VentaService ventaService, PlatoService platoService) {
        this.ventaService = ventaService;
        this.platoService = platoService;
    }

    /**
     * LISTAR VENTAS - GET /ventas
     * Muestra la página de ventas con el formulario y la tabla
     */
    @GetMapping
    public String mostrarVentas(HttpSession session, Model model) {
        // Verificar que el usuario sea TRABAJADOR
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }

        // Enviar datos a la vista JSP
        model.addAttribute("platos", platoService.listar()); // Para el select de platos
        model.addAttribute("ventas", ventaService.listarVentas()); // Para la tabla de ventas
        return "ventas"; // Retorna ventas.jsp
    }

    /**
     * GUARDAR VENTA - POST /ventas/guardar
     * Recibe los datos del formulario y guarda la venta
     */
    @PostMapping("/guardar")
    public String guardarVenta(@RequestParam(required = false) String cliente,
            @RequestParam Long idPlato,
            @RequestParam Integer cantidad,
            @RequestParam String metodoPago,
            @RequestParam(required = false) String observaciones,
            HttpSession session) {
        // Verificar rol
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }

        // Buscar el plato seleccionado
        Plato plato = platoService.buscarPorId(idPlato).orElse(null);
        if (plato == null) {
            return "redirect:/ventas?error=plato_no_encontrado";
        }

        // Crear objeto Venta
        Venta venta = new Venta();
        venta.setCliente(cliente != null && !cliente.isEmpty() ? cliente : "Cliente Anónimo");
        venta.setFecha(LocalDateTime.now());
        venta.setMetodoPago(metodoPago);

        // Crear objeto DetalleVenta (qué plato, cuántos, a qué precio)
        DetalleVenta detalle = new DetalleVenta();
        detalle.setPlato(plato);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(plato.getPrecio());
        detalle.setSubtotal(plato.getPrecio() * cantidad);
        detalle.setVenta(venta);

        // Agregar detalle a la venta
        List<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(detalle);
        venta.setDetalles(detalles);

        // Calcular total
        venta.setTotal(detalle.getSubtotal());

        // Guardar en BD (Service → Repository → INSERT SQL)
        ventaService.guardarVenta(venta);

        // Redirigir a la lista de ventas
        return "redirect:/ventas";
    }

    /**
     * ELIMINAR VENTA - GET /ventas/eliminar/{id}
     * Elimina una venta por su ID
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id, HttpSession session) {
        // Verificar rol
        String rol = (String) session.getAttribute("rol");
        if (!"TRABAJADOR".equals(rol)) {
            return "redirect:/login";
        }

        // Eliminar (Service → Repository → DELETE SQL)
        ventaService.eliminarVenta(id);
        return "redirect:/ventas";
    }
}