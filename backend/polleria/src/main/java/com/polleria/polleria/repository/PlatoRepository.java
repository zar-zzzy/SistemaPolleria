package com.polleria.polleria.repository;

import com.polleria.polleria.entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {
    List<Plato> findByCategoriaIdCategoria(Integer idCategoria);
}
