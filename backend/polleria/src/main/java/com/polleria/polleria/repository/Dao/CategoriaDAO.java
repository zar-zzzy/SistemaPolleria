package com.polleria.polleria.repository.dao;

import com.polleria.polleria.entity.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaDAO {
    List<Categoria> findAll();

    Optional<Categoria> findById(Long id);

    Categoria save(Categoria categoria);

    Categoria update(Categoria categoria);

    void deleteById(Long id);
}