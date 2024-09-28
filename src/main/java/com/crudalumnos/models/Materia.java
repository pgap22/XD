package com.crudalumnos.models;

import java.sql.Date;

public class Materia {
    private String codigoMateria;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;

    public Materia(String codigoMateria, String nombre, String descripcion, Date fechaCreacion) {
        this.codigoMateria = codigoMateria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
