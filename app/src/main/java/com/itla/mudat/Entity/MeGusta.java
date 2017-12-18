package com.itla.mudat.Entity;

public class MeGusta {
    public static final String nomtableMeGusta = "MeGusta";
    public static final String nomid = "id";
    public static final String nomusuario = "usuario";
    public static final String nomanuncio = "anuncio";
    public static final String nomgusta = "gusta";
    private Integer id;
    private Integer usuario;
    private Integer anuncio;
    private Integer gusta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Integer anuncio) {
        this.anuncio = anuncio;
    }

    public Integer getGusta() {
        return gusta;
    }

    public void setGusta(Integer gusta) {
        this.gusta = gusta;
    }
}
