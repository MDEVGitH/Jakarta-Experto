package org.manuel.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Producto {
    private long id;
    private String nombre;
    private BigDecimal precio;
    private Date date;
    private Categoria categoria;
    private String sku;

    public Producto(long id, String nombre, BigDecimal precio, Date date) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.date = date;
    }

    public Producto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + precio + " | " + date + " | " + categoria.getNombre() + " | " + sku;
    }
}
