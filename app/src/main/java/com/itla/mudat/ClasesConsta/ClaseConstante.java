package com.itla.mudat.ClasesConsta;

import android.app.Activity;

import com.itla.mudat.Dao.UsuarioDao;
import com.itla.mudat.Entity.Constante;
import com.itla.mudat.Entity.Usuario;


public class ClaseConstante {

    private static Constante constante=null;
    private static Usuario usuario=null;

    public static Constante getConstante() {
        return constante;
    }

    public static void setConstante(Constante constante) {
        ClaseConstante.constante = constante;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        ClaseConstante.usuario = usuario;
    }

    public static void SetConfiguracionConstate(Constante constante, Activity activity)
    {
        Usuario usuario=new Usuario();
        UsuarioDao usuarioDao=new UsuarioDao(activity);
        setConstante(constante);
        usuario= (Usuario) usuarioDao.Buscar(constante.getId());

        setUsuario(usuario);

    }
}
