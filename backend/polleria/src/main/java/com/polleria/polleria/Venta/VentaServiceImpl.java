package com.polleria.polleria.Venta;

import com.polleria.polleria.DetalleVenta.DetalleVenta;
import com.polleria.polleria.DetalleVenta.DetalleVentaDAO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// Servicio que contiene la lógica de negocio para Ventas
@Service
public class VentaServiceImpl implements VentaService {

    private final VentaDAO ventaDAO; // Para acceder a tabla ventas
    private final DetalleVentaDAO detalleVentaDAO; // Para acceder a tabla detalle_venta

    public VentaServiceImpl(VentaDAO ventaDAO, DetalleVentaDAO detalleVentaDAO) {
        this.ventaDAO = ventaDAO;
        this.detalleVentaDAO = detalleVentaDAO;
    }

    // Obtiene todas las ventas con sus detalles
    @Override
    public List<Venta> listarVentas() {
        List<Venta> ventas = ventaDAO.findAll(); // SELECT * FROM ventas
        for (Venta venta : ventas) {
            List<DetalleVenta> detalles = detalleVentaDAO.findByVentaId(venta.getId()); // Busca detalles de cada venta
            venta.setDetalles(detalles);
        }
        return ventas;
    }

    // Busca una venta por ID
    @Override
    public Optional<Venta> obtenerVentaPorId(Long id) {
        Optional<Venta> ventaOpt = ventaDAO.findById(id);
        if (ventaOpt.isPresent()) {
            Venta venta = ventaOpt.get();
            List<DetalleVenta> detalles = detalleVentaDAO.findByVentaId(venta.getId());
            venta.setDetalles(detalles);
        }
        return ventaOpt;
    }

    // Guarda venta + sus detalles en BD
    @Override
    public Venta guardarVenta(Venta venta) {
        Venta ventaGuardada = ventaDAO.save(venta); // INSERT INTO ventas

        if (venta.getDetalles() != null) {
            for (DetalleVenta detalle : venta.getDetalles()) {
                detalle.setVenta(ventaGuardada);
                detalleVentaDAO.save(detalle); // INSERT INTO detalle_venta
            }
        }

        return ventaGuardada;
    }

    // Elimina venta y sus detalles
    @Override
    public void eliminarVenta(Long id) {
        detalleVentaDAO.deleteByVentaId(id); // Primero elimina detalles
        ventaDAO.deleteById(id); // Luego elimina la venta
    }

    @Override
    public List<Venta> obtenerVentasHoy() {
        List<Venta> ventas = ventaDAO.findVentasHoy();
        for (Venta venta : ventas) {
            List<DetalleVenta> detalles = detalleVentaDAO.findByVentaId(venta.getId());
            venta.setDetalles(detalles);
        }
        return ventas;
    }

    @Override
    public List<Venta> obtenerVentasSemana() {
        List<Venta> ventas = ventaDAO.findVentasSemana();
        for (Venta venta : ventas) {
            List<DetalleVenta> detalles = detalleVentaDAO.findByVentaId(venta.getId());
            venta.setDetalles(detalles);
        }
        return ventas;
    }

    @Override
    public List<Venta> obtenerVentasMes() {
        List<Venta> ventas = ventaDAO.findVentasMes();
        for (Venta venta : ventas) {
            List<DetalleVenta> detalles = detalleVentaDAO.findByVentaId(venta.getId());
            venta.setDetalles(detalles);
        }
        return ventas;
    }

    @Override
    public double calcularTotalVentas(List<Venta> ventas) {
        return ventas.stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }

    @Override
    public int contarVentas(List<Venta> ventas) {
        return ventas.size();
    }

    @Override
    public double calcularTicketPromedio(List<Venta> ventas) {
        if (ventas.isEmpty()) {
            return 0.0;
        }
        return calcularTotalVentas(ventas) / ventas.size();
    }

    @Override
    public String obtenerPlatoMasVendido(List<Venta> ventas) {
        Map<String, Integer> conteoPlatos = new java.util.HashMap<>();

        for (Venta venta : ventas) {
            if (venta.getDetalles() != null) {
                for (DetalleVenta detalle : venta.getDetalles()) {
                    String nombrePlato = detalle.getPlato().getNombre();
                    conteoPlatos.put(nombrePlato,
                            conteoPlatos.getOrDefault(nombrePlato, 0) + detalle.getCantidad());
                }
            }
        }

        return conteoPlatos.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Sin datos");
    }

    @Override
    public Map<String, Map<String, Object>> obtenerDesglosePorPlato(List<Venta> ventas) {
        Map<String, Map<String, Object>> desglose = new java.util.HashMap<>();

        for (Venta venta : ventas) {
            if (venta.getDetalles() != null) {
                for (DetalleVenta detalle : venta.getDetalles()) {
                    String nombrePlato = detalle.getPlato().getNombre();

                    if (!desglose.containsKey(nombrePlato)) {
                        Map<String, Object> info = new java.util.HashMap<>();
                        info.put("cantidad", 0);
                        info.put("total", 0.0);
                        desglose.put(nombrePlato, info);
                    }

                    Map<String, Object> info = desglose.get(nombrePlato);
                    info.put("cantidad", (Integer) info.get("cantidad") + detalle.getCantidad());
                    info.put("total", (Double) info.get("total") + detalle.getSubtotal());
                }
            }
        }

        return desglose;
    }
}