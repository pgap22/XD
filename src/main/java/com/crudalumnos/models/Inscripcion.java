package com.crudalumnos.models;

public class Inscripcion {
    private String carnetAlumno;
    private String codigoMateria;

    public Inscripcion(String carnetAlumno, String codigoMateria) {
        this.carnetAlumno = carnetAlumno;
        this.codigoMateria = codigoMateria;
    }

    // Getters y Setters
    public String getCarnetAlumno() {
        return carnetAlumno;
    }

    public void setCarnetAlumno(String carnetAlumno) {
        this.carnetAlumno = carnetAlumno;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }
}
