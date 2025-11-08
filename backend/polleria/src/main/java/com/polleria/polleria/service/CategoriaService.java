package com.polleria.polleria.service;

import com.polleria.polleria.entity.Categoria;
import com.polleria.polleria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar todas las categorías
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    // Buscar por ID
    public Optional<Categoria> buscarPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    // Guardar (crear o actualizar)
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Eliminar
    public void eliminar(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
