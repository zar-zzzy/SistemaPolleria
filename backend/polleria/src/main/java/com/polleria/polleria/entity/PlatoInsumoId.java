package com.polleria.polleria.entity;

import java.io.Serializable;
import java.util.Objects;

public class PlatoInsumoId implements Serializable {
    
    private Integer plato;
    private Integer insumo;
    
    // Constructores
    public PlatoInsumoId() {}
    
    public PlatoInsumoId(Integer plato, Integer insumo) {
        this.plato = plato;
        this.insumo = insumo;
    }
    
    // Getters y Setters
    public Integer getPlato() {
        return plato;
    }
    
    public void setPlato(Integer plato) {
        this.plato = plato;
    }
    
    public Integer getInsumo() {
        return insumo;
    }
    
    public void setInsumo(Integer insumo) {
        this.insumo = insumo;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlatoInsumoId that = (PlatoInsumoId) o;
        return Objects.equals(plato, that.plato) && Objects.equals(insumo, that.insumo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(plato, insumo);
    }
}
