package com.polleria.polleria.DetalleVenta;

import com.polleria.polleria.Plato.Plato;
import com.polleria.polleria.Venta.Venta;

public class DetalleVenta {

    private Long id;
    private int cantidad;
    private double subtotal;
    private double precio;
    private Venta venta;
    private Plato plato;

    public DetalleVenta() {
    }

    public DetalleVenta(Long id, int cantidad, double subtotal, double precio, Venta venta, Plato plato) {
        this.id = id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.precio = precio;
        this.venta = venta;
        this.plato = plato;
    }

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