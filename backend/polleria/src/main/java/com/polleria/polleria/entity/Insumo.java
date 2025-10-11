package com.polleria.polleria.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Insumos")
public class Insumo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Integer idInsumo;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "unidad_medida", nullable = false)
    private String unidadMedida;
    
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    
    // Constructores
    public Insumo() {}
    
    public Insumo(String nombre, String unidadMedida, Double cantidad) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.cantidad = cantidad;
    }
    
    // Getters y Setters
    public Integer getIdInsumo() {
        return idInsumo;
    }
    
    public void setIdInsumo(Integer idInsumo) {
        this.idInsumo = idInsumo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getUnidadMedida() {
        return unidadMedida;
    }
    
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    public Double getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}
