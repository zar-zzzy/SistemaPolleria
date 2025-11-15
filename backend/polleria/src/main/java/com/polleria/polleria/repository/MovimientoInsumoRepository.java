package com.polleria.polleria.repository;

import com.polleria.polleria.entity.MovimientoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository para movimientos de insumos.
 */
@Repository
public interface MovimientoInsumoRepository extends JpaRepository<MovimientoInsumo, Long> {
    List<MovimientoInsumo> findByInsumoIdOrderByFechaDesc(Long insumoId);

    List<MovimientoInsumo> findTop10ByOrderByFechaDesc();
}
