package com.polleria.polleria.entity;

import java.time.LocalDate;

public class Anuncio {

    private Long id;
    private String titulo;
    private String mensaje;
    private boolean activo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Anuncio() {
        this.activo = true;
    }

    public Anuncio(Long id, String titulo, String mensaje, boolean activo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.activo = activo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}