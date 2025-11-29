package com.polleria.polleria.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaDAO {
    List<Venta> findAll();
    Optional<Venta> findById(Long id);
    Venta save(Venta venta);
    void deleteById(Long id);
    
    // MÉTODOS PARA REPORTES
    List<Venta> findVentasHoy();
    List<Venta> findVentasSemana();
    List<Venta> findVentasMes();
}