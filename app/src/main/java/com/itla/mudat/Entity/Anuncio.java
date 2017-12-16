package com.itla.mudat.Entity;


import java.io.Serializable;
import java.util.Date;

public class Anuncio implements Serializable {

    public static final String nomtableanuncio="Anuncio";
    public static final String nomid="id";
    public static final String nomcategoria="categoria";
    public static final String nomusuario="usuario";
    public static final String nomfecha="fecha";
    public static final String nomcondicion="condicion";
    public static final String nomprecio="precio";
    public static final String nomtitulo="titulo";
    public static final String nomubicacion="ubicacion";
    public static final String nomdetalle="detalle";


    private Integer id;
    private Integer categoria;
    private Integer usuario;
    private Date fecha;
    private String condicion;
    private Double precio;
    private String titulo;
    private String ubicacion;
    private String detalle;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", fecha='" + fecha + '\'' +
                ", condicion='" + condicion + '\'' +
                ", precio=" + precio +
                ", titulo='" + titulo + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", detalle='" + detalle + '\'' +
                '}';
    }
}
