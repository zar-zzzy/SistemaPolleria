package com.polleria.polleria.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PLATO_INSUMO")
public class PlatoInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlatoInsumo;

    @ManyToOne
    @JoinColumn(name = "idPlato")
    private Plato plato;

    @ManyToOne
    @JoinColumn(name = "idInsumo")
    private Insumo insumo;

    // ====== Getters y Setters ======
    public Long getIdPlatoInsumo() {
        return idPlatoInsumo;
    }

    public void setIdPlatoInsumo(Long idPlatoInsumo) {
        this.idPlatoInsumo = idPlatoInsumo;
    }

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
}
