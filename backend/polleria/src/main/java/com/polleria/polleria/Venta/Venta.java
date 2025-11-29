package com.polleria.polleria.Venta;

import java.time.LocalDateTime;
import java.util.List;

import com.polleria.polleria.DetalleVenta.DetalleVenta;

public class Venta {

    private Long id;
    private LocalDateTime fecha;
    private double total;
    private String cliente;
    private String metodoPago;
    private List<DetalleVenta> detalles;

    public Venta() {
        this.fecha = LocalDateTime.now();
    }

    public Venta(Long id, LocalDateTime fecha, double total, String cliente, String metodoPago) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}