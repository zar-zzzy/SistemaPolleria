package com.polleria.polleria.entity;

/**
 * Entidad que representa la relación entre Plato e Insumo.
 * POJO simple para uso con JdbcTemplate.
 */
public class PlatoInsumo {

    private Long idPlatoInsumo;
    private Plato plato;
    private Insumo insumo;
    private Double cantidad;

    // ====== Constructores ======
    public PlatoInsumo() {
    }

    public PlatoInsumo(Long idPlatoInsumo, Plato plato, Insumo insumo, Double cantidad) {
        this.idPlatoInsumo = idPlatoInsumo;
        this.plato = plato;
        this.insumo = insumo;
        this.cantidad = cantidad;
    }

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

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}
