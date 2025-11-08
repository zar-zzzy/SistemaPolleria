package com.polleria.polleria.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "DETALLE_VENTA")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private double subtotal;
    private double precio; // ✅ Campo agregado

    @ManyToOne
    @JoinColumn(name = "idVenta") // 🔹 Ahora coincide con la tabla "VENTAS"
    @JsonIgnoreProperties("detalles")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "idPlato") // 🔹 Ahora coincide con la tabla "PLATOS"
    private Plato plato;

    // ====== Getters y Setters ======

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }
}
