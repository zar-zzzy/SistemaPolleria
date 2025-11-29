package com.polleria.polleria.Plato;

import java.util.List;
import java.util.Optional;

public interface PlatoService {
    List<Plato> listar();
    Optional<Plato> buscarPorId(Long id);
    Plato guardar(Plato plato);
    void eliminar(Long id);
}