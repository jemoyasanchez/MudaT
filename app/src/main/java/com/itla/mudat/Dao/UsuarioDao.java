package com.itla.mudat.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.Entity.TipoUsuario;
import com.itla.mudat.Entity.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private Usuario usuario;
    Boolean vali=true;
    public UsuarioDao(Context context)
    {
        connection = new DBConnection(context);
    }


    @Override
    public Boolean Crear(Object item) {

        try {
            vali=true;
            usuario = (Usuario) item;
            db = connection.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Usuario.nomnombre, usuario.getNombre());
            cv.put(Usuario.nomtipousuario, String.valueOf(usuario.getTipousuario()));
            cv.put(Usuario.nomidentificacion, usuario.getIdentificacion());
            cv.put(Usuario.nomemail, usuario.getEmail());
            cv.put(Usuario.nomtelefono, usuario.getTelefono());
            cv.put(Usuario.nomclave, usuario.getClave());
            cv.put(Usuario.nomstatus, usuario.getStatus());
   if (usuario.getId()==null )
            db.insert(Usuario.nomtableUsuario, null, cv);

      else
       db.update(Usuario.nomtableUsuario,  cv,Usuario.nomid+"=?",new String[] {""+usuario.getId()+""} );

        }

        catch (Exception e)
        {
            e.printStackTrace();
            vali= false;
        }
        finally {
           // db.close();
        }
        return vali;
    }

    @Override
    public Boolean Eliminar(Object item) {
        try
        {
            usuario = (Usuario) item;
            db = connection.getWritableDatabase();
            db.delete(Usuario.nomtableUsuario, Usuario.nomid+"=?",new String[] {""+usuario.getId()+""});
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
            //db.close();
        }
        return true;
    }

    @Override
    public List<Usuario> Listar()   {
        List<Usuario> usuarioslista= new ArrayList<>();
        db = connection.getReadableDatabase();
        String columnas[] = new String[]{Usuario.nomid, Usuario.nomnombre,Usuario.nomtipousuario,Usuario.nomidentificacion, Usuario.nomemail, Usuario.nomtelefono};
        Cursor cursor = db.query(Usuario.nomtableUsuario, columnas, null, null, null, null, Usuario.nomid+" DESC");
        usuarioslista=null;
            try
            {
                if (cursor.moveToFirst()) {
                    usuarioslista=new ArrayList<>();
                    while (!cursor.isAfterLast()) {
                        usuario = new Usuario();
                        usuario.setId(cursor.getInt(cursor.getColumnIndex(Usuario.nomid)));
                        usuario.setNombre(cursor.getString(cursor.getColumnIndex(Usuario.nomnombre)));
                        usuario.setIdentificacion(cursor.getString(cursor.getColumnIndex(Usuario.nomidentificacion)));
                       usuario.setTipousuario( cursor.getInt(cursor.getColumnIndex(Usuario.nomtipousuario)));
                        usuario.setEmail(cursor.getString(cursor.getColumnIndex(Usuario.nomemail)));
                        usuario.setTelefono(cursor.getString(cursor.getColumnIndex(Usuario.nomtelefono)));
                        usuarioslista.add(usuario);
                        cursor.moveToNext();
                    }
                }

                } finally {
                    cursor.close();
                   // db.close();
                }
                return usuarioslista;
    }

    @Override
    public Object Buscar(int item) {
        db =  connection.getReadableDatabase();
        String where = Usuario.nomid + " = ?";
        String[] whereArgs = {""+item+""};
        String columnas[] = new String[]{Usuario.nomid, Usuario.nomnombre,Usuario.nomtipousuario,Usuario.nomidentificacion, Usuario.nomemail, Usuario.nomtelefono,Usuario.nomclave};
        Cursor cursor = db.query(Usuario.nomtableUsuario, columnas, where, whereArgs, null, null, null);

        usuario = null;
        try {
            if (cursor.moveToFirst())
            {

                usuario = new Usuario();
                usuario.setId(cursor.getInt(cursor.getColumnIndex(Usuario.nomid)));
                usuario.setNombre(cursor.getString(cursor.getColumnIndex(Usuario.nomnombre)));
              usuario.setTipousuario( cursor.getInt(cursor.getColumnIndex(Usuario.nomtipousuario)));
              usuario.setIdentificacion(cursor.getString(cursor.getColumnIndex(Usuario.nomidentificacion)));
                usuario.setEmail(cursor.getString(cursor.getColumnIndex(Usuario.nomemail)));
                usuario.setTelefono(cursor.getString(cursor.getColumnIndex(Usuario.nomtelefono)));
            }
        } finally {
            cursor.close();
            db.close();
        }
        return usuario;
    }

    public Object BuscarUsuarioSesion(String Nombre,String Contrasena) {
        db =  connection.getReadableDatabase();

Cursor cursor=db.rawQuery("select *from "+Usuario.nomtableUsuario+" where "+Usuario.nomnombre+"='"+Nombre+"' and "+Usuario.nomclave+"='"+Contrasena+"'",null);
        usuario = null;
        try {
            if (cursor.moveToFirst())
            {

                usuario = new Usuario();
                usuario.setId(cursor.getInt(cursor.getColumnIndex(Usuario.nomid)));
                usuario.setNombre(cursor.getString(cursor.getColumnIndex(Usuario.nomnombre)));
                usuario.setTipousuario( cursor.getInt(cursor.getColumnIndex(Usuario.nomtipousuario)));
                usuario.setIdentificacion(cursor.getString(cursor.getColumnIndex(Usuario.nomidentificacion)));
                usuario.setEmail(cursor.getString(cursor.getColumnIndex(Usuario.nomemail)));
                usuario.setTelefono(cursor.getString(cursor.getColumnIndex(Usuario.nomtelefono)));
            }
        } finally {
            cursor.close();
            db.close();
        }
        return usuario;
    }


    public List<Map<String, String>> ListadeUsuarios() {
        db = connection.getReadableDatabase();
        List<Map<String, String>> lista = new ArrayList<Map<String, String>>();
        try {
            db =  connection.getReadableDatabase();

            String columnas[] = new String[]{Usuario.nomid, Usuario.nomnombre,Usuario.nomtipousuario,Usuario.nomidentificacion, Usuario.nomemail, Usuario.nomtelefono,Usuario.nomclave,Usuario.nomstatus};
            Cursor cursor = db.query(Usuario.nomtableUsuario, columnas, null, null, null, null, null);


            if (cursor.moveToFirst())
            {
                lista = new ArrayList<Map<String, String>>();
                while (!cursor.isAfterLast())
                {

                    Map temp = new HashMap();
                    temp.put(Usuario.nomid, cursor.getInt(cursor.getColumnIndex(Usuario.nomid)));
                    temp.put(Usuario.nomnombre, cursor.getString(cursor.getColumnIndex(Usuario.nomnombre)));
                    temp.put(Usuario.nomtipousuario, cursor.getInt(cursor.getColumnIndex(Usuario.nomtipousuario)));
                    temp.put(Usuario.nomidentificacion, cursor.getInt(cursor.getColumnIndex(Usuario.nomidentificacion)));
                    temp.put(Usuario.nomemail, cursor.getInt(cursor.getColumnIndex(Usuario.nomemail)));
                    temp.put(Usuario.nomtelefono, cursor.getInt(cursor.getColumnIndex(Usuario.nomtelefono)));
                    temp.put(Usuario.nomclave, cursor.getInt(cursor.getColumnIndex(Usuario.nomclave)));
                    temp.put(Usuario.nomstatus, cursor.getInt(cursor.getColumnIndex(Usuario.nomstatus)));
                    lista.add(temp);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return lista;
    }
}
