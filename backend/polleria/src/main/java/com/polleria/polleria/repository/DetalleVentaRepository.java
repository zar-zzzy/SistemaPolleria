package com.polleria.polleria.repository;

import com.polleria.polleria.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    
    @Query("SELECT dv.plato.nombre, SUM(dv.cantidad) FROM DetalleVenta dv GROUP BY dv.plato.idPlato ORDER BY SUM(dv.cantidad) DESC")
    List<Object[]> findPlatoMasVendido();
}
