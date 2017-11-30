package com.itla.mudat.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.Entity.Anuncio;
import com.itla.mudat.Entity.Categoria;
import com.itla.mudat.Entity.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnuncioDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private Anuncio anuncio;

    public AnuncioDao(Context context)
    {
        connection = new DBConnection(context);
    }

    @Override
    public Boolean Crear(Object item) {
        try {
            anuncio = (Anuncio) item;
            db = connection.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Anuncio.nomcategoria, String.valueOf(anuncio.getCategoria()));
            cv.put(Anuncio.nomusuario, String.valueOf(anuncio.getUsuario()));
            cv.put(Anuncio.nomfecha, String.valueOf(anuncio.getFecha()));
            cv.put(Anuncio.nomcondicion, anuncio.getCondicion());
            cv.put(Anuncio.nomprecio, anuncio.getPrecio());
            cv.put(Anuncio.nomtitulo, anuncio.getTitulo());
            cv.put(Anuncio.nomubicacion, anuncio.getUbicacion());
            cv.put(Anuncio.nomdetalle,anuncio.getDetalle());
            db.beginTransaction();
            db.insert(Anuncio.nomtableanuncio, null, cv);
            db.endTransaction();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
            db.close();
        }
        return true;
    }

    @Override
    public Boolean Actualizar(Object item) {
        try {
            anuncio = (Anuncio) item;
            db = connection.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Anuncio.nomcategoria, String.valueOf(anuncio.getCategoria()));
            cv.put(Anuncio.nomusuario, String.valueOf(anuncio.getUsuario()));
            cv.put(Anuncio.nomfecha, String.valueOf(anuncio.getFecha()));
            cv.put(Anuncio.nomcondicion, anuncio.getCondicion());
            cv.put(Anuncio.nomprecio, anuncio.getPrecio());
            cv.put(Anuncio.nomtitulo, anuncio.getTitulo());
            cv.put(Anuncio.nomubicacion, anuncio.getUbicacion());
            cv.put(Anuncio.nomdetalle,anuncio.getDetalle());
            db.beginTransaction();
            db.update(Anuncio.nomtableanuncio,  cv,Anuncio.nomid+"=?",new String[] {""+anuncio.getId()+""} );
            db.endTransaction();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
            db.close();
        }
        return true;
    }

    @Override
    public Boolean Eliminar(Object item) {
        try {
            anuncio = (Anuncio) item;
            db = connection.getWritableDatabase();
            db.delete(Anuncio.nomtableanuncio, Anuncio.nomid+"=?",new String[] {""+anuncio.getId()+""});

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally {
            db.close();
        }
        return true;
    }



    @Override
    public List<?> Listar()   {
        List<Anuncio> usuarioslista= new ArrayList<>();

        db = connection.getReadableDatabase();
        String columnas[] = new String[]{Anuncio.nomid, Anuncio.nomusuario, Anuncio.nomfecha, Anuncio.nomcondicion,Anuncio.nomprecio,Anuncio.nomtitulo,Anuncio.nomubicacion,Anuncio.nomdetalle};
        Cursor cursor = db.query(Anuncio.nomtableanuncio, columnas, null, null, null, null, null);
        usuarioslista=null;
        try
        {
            if (cursor.moveToFirst()) {

                while (!cursor.isAfterLast()) {
                    anuncio = new Anuncio();
                    anuncio.setId(cursor.getInt(cursor.getColumnIndex(Anuncio.nomid)));
                    anuncio.setCategoria(Categoria.valueOf( cursor.getString(cursor.getColumnIndex(Anuncio.nomcategoria))));
                    //anuncio.setUsuario(Usuario.va(  cursor.getString(cursor.getColumnIndex(Anuncio.nomusuario)));
                    // u.setTipousuario(TipoUsuario.valueOf( cursor.getString(cursor.getColumnIndex("tipousuario"))));

                   // anuncio.setFecha(cursor.getString(cursor.getColumnIndex(Anuncio.nomfecha)));
                    anuncio.setCondicion(cursor.getString(cursor.getColumnIndex(Anuncio.nomcondicion)));
                    anuncio.setPrecio(cursor.getDouble(cursor.getColumnIndex(Anuncio.nomprecio)));
                    anuncio.setTitulo(cursor.getString(cursor.getColumnIndex(Anuncio.nomtitulo)));
                    anuncio.setUbicacion(cursor.getString(cursor.getColumnIndex(Anuncio.nomubicacion)));
                    anuncio.setDetalle(cursor.getString(cursor.getColumnIndex(Anuncio.nomdetalle)));
                    usuarioslista.add(anuncio);
                    cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
            db.close();
        }
        return usuarioslista;
    }

    @Override
    public Object Buscar(int item) {

        db =  connection.getReadableDatabase();
        String where = Anuncio.nomid + " = ?";
        String[] whereArgs = {""+item+""};
        String columnas[] = new String[]{Anuncio.nomid, Anuncio.nomusuario, Anuncio.nomfecha, Anuncio.nomcondicion,Anuncio.nomprecio,Anuncio.nomtitulo,Anuncio.nomubicacion,Anuncio.nomdetalle};
        Cursor cursor = db.query(Anuncio.nomtableanuncio, columnas, where, whereArgs, null, null, null);
        anuncio = null;

        try {
            if (cursor.moveToFirst()) {

                anuncio = new Anuncio();
                anuncio.setId(cursor.getInt(cursor.getColumnIndex(Anuncio.nomid)));
                anuncio.setCategoria(Categoria.valueOf( cursor.getString(cursor.getColumnIndex(Anuncio.nomcategoria))));
                //anuncio.setUsuario(Usuario.va(  cursor.getString(cursor.getColumnIndex(Anuncio.nomusuario)));
                // u.setTipousuario(TipoUsuario.valueOf( cursor.getString(cursor.getColumnIndex("tipousuario"))));

              //  anuncio.setFecha(cursor.getString(cursor.getColumnIndex(Anuncio.nomfecha)));
                anuncio.setCondicion(cursor.getString(cursor.getColumnIndex(Anuncio.nomcondicion)));
                anuncio.setPrecio(cursor.getDouble(cursor.getColumnIndex(Anuncio.nomprecio)));
                anuncio.setTitulo(cursor.getString(cursor.getColumnIndex(Anuncio.nomtitulo)));
                anuncio.setUbicacion(cursor.getString(cursor.getColumnIndex(Anuncio.nomubicacion)));
                anuncio.setDetalle(cursor.getString(cursor.getColumnIndex(Anuncio.nomdetalle)));
            }
        } finally {
            cursor.close();
            db.close();
        }
        return anuncio;
    }
}
