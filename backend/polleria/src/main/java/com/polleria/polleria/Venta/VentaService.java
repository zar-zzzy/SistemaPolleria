package com.polleria.polleria.Venta;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VentaService {
    List<Venta> listarVentas();
    Optional<Venta> obtenerVentaPorId(Long id);
    Venta guardarVenta(Venta venta);
    void eliminarVenta(Long id);
    
    // MÉTODOS PARA REPORTES
    List<Venta> obtenerVentasHoy();
    List<Venta> obtenerVentasSemana();
    List<Venta> obtenerVentasMes();
    double calcularTotalVentas(List<Venta> ventas);
    int contarVentas(List<Venta> ventas);
    double calcularTicketPromedio(List<Venta> ventas);
    String obtenerPlatoMasVendido(List<Venta> ventas);
    Map<String, Map<String, Object>> obtenerDesglosePorPlato(List<Venta> ventas);
}