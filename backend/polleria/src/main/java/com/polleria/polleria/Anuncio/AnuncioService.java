package com.polleria.polleria.Anuncio;

import java.util.List;
import java.util.Optional;

public interface AnuncioService {
    List<Anuncio> listarTodos();
    List<Anuncio> listarActivosHoy();
    Optional<Anuncio> buscarPorId(Long id);
    Anuncio guardar(Anuncio anuncio);
    void eliminar(Long id);
}