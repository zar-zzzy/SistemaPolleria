package com.polleria.polleria.repository;

import com.polleria.polleria.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    // Buscar ventas de hoy comparando por rango de fecha (00:00 a 23:59)
    @Query("SELECT v FROM Venta v WHERE v.fecha >= :inicio AND v.fecha < :fin")
    List<Venta> findVentasHoy(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // Buscar ventas entre dos fechas específicas
    @Query("SELECT v FROM Venta v WHERE v.fecha >= :fechaInicio AND v.fecha <= :fechaFin")
    List<Venta> findVentasPorRango(@Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
}
