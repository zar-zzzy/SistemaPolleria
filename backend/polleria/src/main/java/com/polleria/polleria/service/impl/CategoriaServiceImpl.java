package com.polleria.polleria.service.impl;

import com.polleria.polleria.entity.Categoria;
import com.polleria.polleria.repository.dao.CategoriaDAO;
import com.polleria.polleria.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImpl(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaDAO.findAll();
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaDAO.findById(id);
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        if (categoria.getIdCategoria() == null) {
            return categoriaDAO.save(categoria);
        } else {
            return categoriaDAO.update(categoria);
        }
    }

    @Override
    public void eliminar(Long id) {
        categoriaDAO.deleteById(id);
    }
}