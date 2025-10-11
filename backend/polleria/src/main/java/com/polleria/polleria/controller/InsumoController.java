package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Insumo;
import com.polleria.polleria.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*") // Para permitir conexión desde el frontend
public class InsumoController {
    
    @Autowired
    private InsumoRepository insumoRepository;
    
    @GetMapping
    public List<Insumo> getAllInsumos() {
        return insumoRepository.findAll();
    }
    
    @PostMapping
    public Insumo createInsumo(@RequestBody Insumo insumo) {
        return insumoRepository.save(insumo);
    }
    
    @GetMapping("/{id}")
    public Insumo getInsumoById(@PathVariable Integer id) {
        return insumoRepository.findById(id).orElse(null);
    }
    
    @PutMapping("/{id}")
    public Insumo updateInsumo(@PathVariable Integer id, @RequestBody Insumo insumo) {
        insumo.setIdInsumo(id);
        return insumoRepository.save(insumo);
    }
    
    @DeleteMapping("/{id}")
    public void deleteInsumo(@PathVariable Integer id) {
        insumoRepository.deleteById(id);
    }
    
    @GetMapping("/test")
    public String testConnection() {
        return "¡Conexión exitosa! El backend está funcionando correctamente.";
    }
}
