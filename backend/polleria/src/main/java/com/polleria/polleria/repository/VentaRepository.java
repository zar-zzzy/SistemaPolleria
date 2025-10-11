package com.polleria.polleria.repository;

import com.polleria.polleria.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    
    @Query("SELECT v FROM Venta v WHERE DATE(v.fecha) = CURRENT_DATE")
    List<Venta> findVentasHoy();
    
    @Query("SELECT v FROM Venta v WHERE v.fecha >= :fechaInicio AND v.fecha <= :fechaFin")
    List<Venta> findVentasPorRango(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
