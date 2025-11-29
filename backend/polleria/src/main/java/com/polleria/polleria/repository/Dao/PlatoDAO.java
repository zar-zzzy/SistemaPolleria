package com.polleria.polleria.repository.Dao;

import com.polleria.polleria.entity.Plato;
import java.util.List;
import java.util.Optional;

public interface PlatoDAO {
    List<Plato> findAll();
    Optional<Plato> findById(Long id);
    Plato save(Plato plato);
    Plato update(Plato plato);
    void deleteById(Long id);
}