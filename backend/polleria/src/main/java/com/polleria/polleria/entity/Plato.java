package com.polleria.polleria.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Platos")
public class Plato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plato")
    private Integer idPlato;
    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;
    
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL)
    private List<PlatoInsumo> platoInsumos;
    
    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVentas;
    
    // Constructores
    public Plato() {}
    
    public Plato(String nombre, Categoria categoria, BigDecimal precio, String descripcion) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
    }
    
    // Getters y Setters
    public Integer getIdPlato() {
        return idPlato;
    }
    
    public void setIdPlato(Integer idPlato) {
        this.idPlato = idPlato;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public BigDecimal getPrecio() {
        return precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public List<PlatoInsumo> getPlatoInsumos() {
        return platoInsumos;
    }
    
    public void setPlatoInsumos(List<PlatoInsumo> platoInsumos) {
        this.platoInsumos = platoInsumos;
    }
    
    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }
    
    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }
}
