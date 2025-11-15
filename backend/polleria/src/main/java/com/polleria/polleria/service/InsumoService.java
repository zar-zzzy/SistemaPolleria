package com.polleria.polleria.service;

import com.polleria.polleria.entity.Insumo;
import com.polleria.polleria.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la lógica de negocio de insumos.
 * Actúa como intermediario entre el Controller y el Repository.
 */
@Service
public class InsumoService {

    @Autowired
    private InsumoRepository insumoRepository;

    // Listar todos los insumos
    public List<Insumo> listarInsumos() {
        return insumoRepository.findAll();
    }

    // Buscar un insumo por ID
    public Optional<Insumo> obtenerInsumoPorId(Long id) {
        return insumoRepository.findById(id);
    }

    // Guardar un nuevo insumo
    public Insumo guardarInsumo(Insumo insumo) {
        return insumoRepository.save(insumo);
    }

    // Actualizar un insumo existente
    public Insumo actualizarInsumo(Long id, Insumo insumoActualizado) {
        return insumoRepository.findById(id)
                .map(insumoExistente -> {
                    insumoExistente.setNombre(insumoActualizado.getNombre());
                    insumoExistente.setStock(insumoActualizado.getStock());
                    insumoExistente.setUnidadMedida(insumoActualizado.getUnidadMedida());
                    return insumoRepository.save(insumoExistente);
                }).orElse(null);
    }

    // Eliminar un insumo por su ID
    public void eliminarInsumo(Long id) {
        insumoRepository.deleteById(id);
    }
}
