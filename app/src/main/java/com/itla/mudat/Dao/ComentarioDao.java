package com.itla.mudat.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.Entity.Comentario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ComentarioDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private Comentario comentario;

    public ComentarioDao(Context context) {
        connection = new DBConnection(context);
    }

    @Override
    public Boolean Crear(Object item) {
        try {
            comentario = (Comentario) item;
            db = connection.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Comentario.nomid, comentario.getId());
            cv.put(Comentario.nomusuario, comentario.getUsuario());
            cv.put(Comentario.nomanuncio, comentario.getAnuncio());
            cv.put(Comentario.nomcomentario, comentario.getComentario());

            db.beginTransaction();
            db.insert(Comentario.nomtableComentario, null, cv);
            db.endTransaction();
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        } finally {
            if (db.isOpen()) {
                db.close();
            }
        }
        return true;
    }


    @Override
    public Boolean Eliminar(Object item) {

        try {
            comentario = (Comentario) item;
            db = connection.getWritableDatabase();

            db.delete(Comentario.nomtableComentario, Comentario.nomid + "=?", new String[]{"" + comentario.getId() + ""});
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<?> Listar() throws SQLException {
        List<Comentario> comentarioslistar = new ArrayList<>();

        db = connection.getReadableDatabase();
        String columnas[] = new String[]{Comentario.nomid, Comentario.nomusuario, Comentario.nomanuncio, Comentario.nomcomentario};
        Cursor cursor = db.query(Comentario.nomtableComentario, columnas, null, null, null, null, null);
        comentarioslistar = null;
        try {
            if (cursor.moveToFirst()) {
                comentarioslistar = new ArrayList<>();
                while (!cursor.isAfterLast()) {

                    comentario.setId(cursor.getInt(cursor.getColumnIndex(Comentario.nomid)));
                    comentario.setUsuario(cursor.getInt(cursor.getColumnIndex(Comentario.nomusuario)));
                    comentario.setAnuncio(cursor.getInt(cursor.getColumnIndex(Comentario.nomanuncio)));
                    comentario.setComentario(cursor.getString(cursor.getColumnIndex(Comentario.nomcomentario)));
                    comentarioslistar.add(comentario);
                    cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
            db.close();
        }
        return comentarioslistar;
    }

    @Override
    public Object Buscar(int item) {
        db = connection.getReadableDatabase();
        String where = Comentario.nomid + " = ?";
        String[] whereArgs = {"" + item + ""};
        String columnas[] = new String[]{Comentario.nomid, Comentario.nomusuario, Comentario.nomanuncio, Comentario.nomcomentario};
        Cursor cursor = db.query(Comentario.nomtableComentario, columnas, where, whereArgs, null, null, null);

        comentario = null;
        try {
            if (cursor.moveToFirst()) {
                comentario = new Comentario();
                comentario.setId(cursor.getInt(cursor.getColumnIndex(Comentario.nomid)));
                comentario.setUsuario(cursor.getInt(cursor.getColumnIndex(Comentario.nomusuario)));
                comentario.setAnuncio(cursor.getInt(cursor.getColumnIndex(Comentario.nomanuncio)));
                comentario.setComentario(cursor.getString(cursor.getColumnIndex(Comentario.nomcomentario)));

            }
        } finally {
            cursor.close();
            db.close();
        }
        return comentario;
    }
}