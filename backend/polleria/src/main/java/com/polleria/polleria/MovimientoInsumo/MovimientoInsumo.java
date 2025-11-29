package com.polleria.polleria.MovimientoInsumo;

import java.time.LocalDateTime;

import com.polleria.polleria.Insumo.Insumo;

public class MovimientoInsumo {

    private Long id;
    private Insumo insumo;
    private TipoMovimiento tipo;
    private double cantidad;
    private String motivo;
    private LocalDateTime fecha;

    public MovimientoInsumo() {
        this.fecha = LocalDateTime.now();
    }

    public MovimientoInsumo(Long id, Insumo insumo, TipoMovimiento tipo, double cantidad, String motivo, LocalDateTime fecha) {
        this.id = id;
        this.insumo = insumo;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}