package com.itla.mudat.Entity;



public class Comentario {
    public static final String nomtableComentario="Comentario";
    public static final String nomid="id";
    public static final String nomusuario="usuario";
    public static final String nomanuncio="anuncio";
    public static final String nomcomentario="comentario";
    private Integer id;
    private Integer usuario;
    private Integer anuncio;
    private String comentario;

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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
