package com.polleria.polleria.Plato;

import com.polleria.polleria.Categoria.Categoria;

public class Plato {

    private Long idPlato;
    private String nombre;
    private String descripcion;
    private double precio;
    private Categoria categoria;

    public Plato() {
    }

    public Plato(Long idPlato, String nombre, String descripcion, double precio, Categoria categoria) {
        this.idPlato = idPlato;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Long getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}