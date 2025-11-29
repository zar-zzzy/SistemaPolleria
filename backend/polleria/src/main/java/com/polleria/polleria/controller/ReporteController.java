package com.polleria.polleria.controller;

import com.polleria.polleria.Venta.Venta;
import com.polleria.polleria.Venta.VentaService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class ReporteController {

    @Autowired
    private VentaService ventaService;

    /**
     * Página de reportes con selector de período
     */
    @GetMapping("/reportes")
    public String mostrarReportes(@RequestParam(defaultValue = "hoy") String periodo,
                                   HttpSession session,
                                   Model model) {
        String rol = (String) session.getAttribute("rol");
        if (!"ADMIN".equals(rol)) {
            return "redirect:/login";
        }

        List<Venta> ventas;
        
        // Obtener ventas según el período seleccionado
        switch (periodo) {
            case "semana":
                ventas = ventaService.obtenerVentasSemana();
                break;
            case "mes":
                ventas = ventaService.obtenerVentasMes();
                break;
            default:
                ventas = ventaService.obtenerVentasHoy();
        }

        // Calcular métricas
        double totalVentas = ventaService.calcularTotalVentas(ventas);
        int cantidadVentas = ventaService.contarVentas(ventas);
        double ticketPromedio = ventaService.calcularTicketPromedio(ventas);
        String platoMasVendido = ventaService.obtenerPlatoMasVendido(ventas);
        Map<String, Map<String, Object>> desglose = ventaService.obtenerDesglosePorPlato(ventas);

        // Agregar atributos al modelo
        model.addAttribute("periodo", periodo);
        model.addAttribute("totalVentas", totalVentas);
        model.addAttribute("cantidadVentas", cantidadVentas);
        model.addAttribute("ticketPromedio", ticketPromedio);
        model.addAttribute("platoMasVendido", platoMasVendido);
        model.addAttribute("desglose", desglose);

        return "reportes";
    }
}