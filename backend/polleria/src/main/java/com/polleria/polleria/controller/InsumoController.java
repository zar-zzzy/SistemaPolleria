package com.polleria.polleria.controller;

import com.polleria.polleria.entity.Insumo;
import com.polleria.polleria.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    @GetMapping
    public List<Insumo> listarInsumos() {
        return insumoService.listarInsumos();
    }

    @GetMapping("/{id}")
    public Optional<Insumo> obtenerInsumo(@PathVariable Long id) {
        return insumoService.obtenerInsumoPorId(id);
    }

    @PostMapping
    public Insumo crearInsumo(@RequestBody Insumo insumo) {
        return insumoService.guardarInsumo(insumo);
    }

    @PutMapping("/{id}")
    public Insumo actualizarInsumo(@PathVariable Long id, @RequestBody Insumo insumo) {
        return insumoService.actualizarInsumo(id, insumo);
    }

    @DeleteMapping("/{id}")
    public void eliminarInsumo(@PathVariable Long id) {
        insumoService.eliminarInsumo(id);
    }
}
