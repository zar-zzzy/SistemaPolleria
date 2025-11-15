package com.polleria.polleria.repository;

import com.polleria.polleria.entity.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para el acceso a datos de insumos.
 * JpaRepository proporciona métodos automáticos: save(), findAll(), findById(),
 * deleteById(), etc.
 */
@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    // Métodos personalizados pueden agregarse aquí si es necesario
}
