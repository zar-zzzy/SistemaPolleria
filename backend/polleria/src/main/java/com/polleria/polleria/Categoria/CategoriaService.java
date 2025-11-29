package com.polleria.polleria.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();
    Optional<Categoria> buscarPorId(Long id);
    Categoria guardar(Categoria categoria);
    void eliminar(Long id);
}