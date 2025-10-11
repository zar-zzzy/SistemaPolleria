package com.polleria.polleria.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PlatoInsumo")
@IdClass(PlatoInsumoId.class)
public class PlatoInsumo {
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plato", nullable = false)
    private Plato plato;
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insumo", nullable = false)
    private Insumo insumo;
    
    @Column(name = "cantidad", nullable = false)
    private BigDecimal cantidad;
    
    // Constructores
    public PlatoInsumo() {}
    
    public PlatoInsumo(Plato plato, Insumo insumo, BigDecimal cantidad) {
        this.plato = plato;
        this.insumo = insumo;
        this.cantidad = cantidad;
    }
    
    // Getters y Setters
    public Plato getPlato() {
        return plato;
    }
    
    public void setPlato(Plato plato) {
        this.plato = plato;
    }
    
    public Insumo getInsumo() {
        return insumo;
    }
    
    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }
    
    public BigDecimal getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}
