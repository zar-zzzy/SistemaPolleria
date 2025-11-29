package com.polleria.polleria.repository.dao;

import com.polleria.polleria.entity.Insumo;
import java.util.List;
import java.util.Optional;

public interface InsumoDAO {
    List<Insumo> findAll();

    Optional<Insumo> findById(Long id);

    Insumo save(Insumo insumo);

    Insumo update(Insumo insumo);

    void deleteById(Long id);

    int count();
}