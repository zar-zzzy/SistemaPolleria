package com.polleria.polleria.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad para registrar movimientos de stock de insumos.
 * Tipos: ENTRADA (compra/ingreso) y SALIDA (uso/consumo)
 */
@Entity
@Table(name = "movimiento_insumo")
public class MovimientoInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "insumo_id")
    private Insumo insumo;

    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    private double cantidad;
    private String motivo;

    private LocalDateTime fecha;

    // Constructor por defecto
    public MovimientoInsumo() {
        this.fecha = LocalDateTime.now();
    }

    // Getters y Setters
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
