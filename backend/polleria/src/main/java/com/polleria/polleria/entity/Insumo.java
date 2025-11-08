package com.polleria.polleria.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "INSUMOS")
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int stock;

    private String unidadMedida; // ✅ Campo agregado

    // 🔹 Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUnidadMedida() { // ✅ Getter agregado
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) { // ✅ Setter agregado
        this.unidadMedida = unidadMedida;
    }
}
