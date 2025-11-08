package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Plato;
import com.polleria.polleria.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "*") // Permite conexión con tu frontend
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    // Listar todos los platos
    @GetMapping
    public List<Plato> listar() {
        return platoService.listar();
    }

    // Buscar un plato por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Plato> buscarPorId(@PathVariable Integer id) {
        return platoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear o actualizar un plato
    @PostMapping
    public Plato guardar(@RequestBody Plato plato) {
        return platoService.guardar(plato);
    }

    // Eliminar un plato
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            platoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(409)
                    .body("No se puede eliminar el plato porque está siendo usado en ventas");
        }
    }
}
