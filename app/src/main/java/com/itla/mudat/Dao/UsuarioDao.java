package com.itla.mudat.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.Entity.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private Usuario usuario;

    public UsuarioDao(Context context)
    {
        connection = new DBConnection(context);
    }


    @Override
    public Boolean Crear(Object item) {
        try {
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

            db.insert(Usuario.nomtableUsuario, null, cv);

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
           // db.close();
        }
        return true;
    }

    @Override
    public Boolean Actualizar(Object item) {
        try {
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
         //   db.beginTransaction();
            db.update(Usuario.nomtableUsuario,  cv,Usuario.nomid+"=?",new String[] {""+usuario.getId()+""} );
        //    db.endTransaction();
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
    public List<?> Listar()   {
        List<Usuario> usuarioslista= new ArrayList<>();
        db = connection.getReadableDatabase();
        String columnas[] = new String[]{Usuario.nomid, Usuario.nomnombre, Usuario.nomemail, Usuario.nomtelefono};
        Cursor cursor = db.query(Usuario.nomtableUsuario, columnas, null, null, null, null, null);
        usuarioslista=null;
            try
            {
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        usuario = new Usuario();
                        usuario.setId(cursor.getInt(cursor.getColumnIndex(Usuario.nomid)));
                        usuario.setNombre(cursor.getString(cursor.getColumnIndex(Usuario.nomnombre)));
                        // u.setTipousuario(TipoUsuario.valueOf( cursor.getString(cursor.getColumnIndex("tipousuario"))));
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
        String columnas[] = new String[]{Usuario.nomid, Usuario.nomnombre, Usuario.nomemail, Usuario.nomtelefono};
        Cursor cursor = db.query(Usuario.nomtableUsuario, columnas, where, whereArgs, null, null, null);

        usuario = null;
        try {
            if (cursor.moveToFirst())
            {

                usuario = new Usuario();
                usuario.setId(cursor.getInt(cursor.getColumnIndex(Usuario.nomid)));
                usuario.setNombre(cursor.getString(cursor.getColumnIndex(Usuario.nomnombre)));
                // u.setTipousuario(TipoUsuario.valueOf( cursor.getString(cursor.getColumnIndex("tipousuario"))));
                usuario.setEmail(cursor.getString(cursor.getColumnIndex(Usuario.nomemail)));
                usuario.setTelefono(cursor.getString(cursor.getColumnIndex(Usuario.nomtelefono)));
            }
        } finally {
            cursor.close();
            db.close();
        }
        return usuario;
    }
}
