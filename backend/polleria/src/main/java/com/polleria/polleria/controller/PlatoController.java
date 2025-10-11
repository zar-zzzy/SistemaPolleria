package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Plato;
import com.polleria.polleria.repository.PlatoRepository;
import com.polleria.polleria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "*")
public class PlatoController {
    
    @Autowired
    private PlatoRepository platoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @GetMapping
    public List<Plato> getAllPlatos() {
        return platoRepository.findAll();
    }
    
    @GetMapping("/categoria/{categoriaId}")
    public List<Plato> getPlatosByCategoria(@PathVariable Integer categoriaId) {
        return platoRepository.findByCategoriaId(categoriaId);
    }
    
    @PostMapping
    public Plato createPlato(@RequestBody Plato plato) {
        return platoRepository.save(plato);
    }
    
    @GetMapping("/{id}")
    public Plato getPlatoById(@PathVariable Integer id) {
        return platoRepository.findById(id).orElse(null);
    }
    
    @PutMapping("/{id}")
    public Plato updatePlato(@PathVariable Integer id, @RequestBody Plato plato) {
        plato.setIdPlato(id);
        return platoRepository.save(plato);
    }
    
    @DeleteMapping("/{id}")
    public void deletePlato(@PathVariable Integer id) {
        platoRepository.deleteById(id);
    }
}
