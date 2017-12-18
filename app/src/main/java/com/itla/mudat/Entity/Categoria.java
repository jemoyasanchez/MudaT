package com.itla.mudat.Entity;


public class Categoria {


    public static final String nomtableCategoria = "Categoria";
    public static final String nomid = "id";
    public static final String nomnombre = "nombre";

    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
