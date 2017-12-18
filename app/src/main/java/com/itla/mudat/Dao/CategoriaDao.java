package com.itla.mudat.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.Entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private Categoria categoria;
    Boolean vali = true;

    public CategoriaDao(Context context) {
        connection = new DBConnection(context);
    }


    @Override
    public Boolean Crear(Object item) {

        try {
            vali = true;
            categoria = (Categoria) item;
            db = connection.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Categoria.nomnombre, categoria.getNombre());

            if (categoria.getId() == null)
                db.insert(Categoria.nomtableCategoria, null, cv);

            else
                db.update(Categoria.nomtableCategoria, cv, Categoria.nomid + "=?", new String[]{"" + categoria.getId() + ""});

        } catch (Exception e) {
            e.printStackTrace();
            vali = false;
        } finally {
            // db.close();
        }
        return vali;
    }

    @Override
    public Boolean Eliminar(Object item) {
        try {
            categoria = (Categoria) item;
            db = connection.getWritableDatabase();
            db.delete(Categoria.nomtableCategoria, Categoria.nomid + "=?", new String[]{"" + categoria.getId() + ""});
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            //db.close();
        }
        return true;
    }

    @Override
    public List<Categoria> Listar() {
        List<Categoria> categoriaslista = new ArrayList<>();
        db = connection.getReadableDatabase();
        String columnas[] = new String[]{Categoria.nomid, Categoria.nomnombre};
        Cursor cursor = db.query(Categoria.nomtableCategoria, columnas, null, null, null, null, null);
        categoriaslista = null;
        try {
            if (cursor.moveToFirst()) {
                categoriaslista = new ArrayList<>();
                while (!cursor.isAfterLast()) {
                    categoria = new Categoria();
                    categoria.setId(cursor.getInt(cursor.getColumnIndex(Categoria.nomid)));
                    categoria.setNombre(cursor.getString(cursor.getColumnIndex(Categoria.nomnombre)));

                    categoriaslista.add(categoria);
                    cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
            // db.close();
        }
        return categoriaslista;
    }

    @Override
    public Object Buscar(int item) {
        db = connection.getReadableDatabase();
        String where = Categoria.nomid + " = ?";
        String[] whereArgs = {"" + item + ""};
        String columnas[] = new String[]{Categoria.nomid, Categoria.nomnombre};
        Cursor cursor = db.query(Categoria.nomtableCategoria, columnas, where, whereArgs, null, null, null);

        categoria = null;
        try {
            if (cursor.moveToFirst()) {

                categoria = new Categoria();
                categoria.setId(cursor.getInt(cursor.getColumnIndex(Categoria.nomid)));
                categoria.setNombre(cursor.getString(cursor.getColumnIndex(Categoria.nomnombre)));

            }
        } finally {
            cursor.close();
            db.close();
        }
        return categoria;
    }
}
