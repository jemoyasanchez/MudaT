package com.itla.mudat.Dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.Entity.TipoUsuario;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoUsuarioDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private TipoUsuario tipousuario;

    public TipoUsuarioDao(Context context) {
        connection = new DBConnection(context);
    }

    @Override
    public Boolean Crear(Object item) {
        try {
            tipousuario = (TipoUsuario) item;
            db = connection.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(TipoUsuario.nomnombre, tipousuario.getNombre());

            db.beginTransaction();
            db.insert(TipoUsuario.nomtableTipoUsuario, null, cv);
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
            tipousuario = (TipoUsuario) item;
            db = connection.getWritableDatabase();

            db.delete(TipoUsuario.nomtableTipoUsuario, TipoUsuario.nomid + "=?", new String[]{"" + tipousuario.getId() + ""});
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<?> Listar() throws SQLException {
        List<TipoUsuario> tipousuariolistar = new ArrayList<>();

        db = connection.getReadableDatabase();
        String columnas[] = new String[]{TipoUsuario.nomid, TipoUsuario.nomnombre};
        Cursor cursor = db.query(TipoUsuario.nomtableTipoUsuario, columnas, null, null, null, null, null);
        tipousuariolistar = null;
        try {
            if (cursor.moveToFirst()) {
                tipousuariolistar = new ArrayList<>();
                while (!cursor.isAfterLast()) {
                    // tipousuario = new TipoUsuario();
                    tipousuario.setId(cursor.getInt(cursor.getColumnIndex(TipoUsuario.nomid)));
                    tipousuario.setNombre(cursor.getString(cursor.getColumnIndex(TipoUsuario.nomnombre)));

                    tipousuariolistar.add(tipousuario);
                    cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
            db.close();
        }
        return tipousuariolistar;
    }

    @Override
    public Object Buscar(int item) {
        db = connection.getReadableDatabase();
        String where = TipoUsuario.nomid + " = ?";
        String[] whereArgs = {"" + item + ""};
        String columnas[] = new String[]{TipoUsuario.nomid, TipoUsuario.nomnombre};
        Cursor cursor = db.query(TipoUsuario.nomtableTipoUsuario, columnas, where, whereArgs, null, null, null);

        tipousuario = null;
        try {
            if (cursor.moveToFirst()) {

                //tipousuario = new TipoUsuario();
                tipousuario.setId(cursor.getInt(cursor.getColumnIndex(TipoUsuario.nomid)));
                tipousuario.setNombre(cursor.getString(cursor.getColumnIndex(TipoUsuario.nomnombre)));

            }
        } finally {
            cursor.close();
            db.close();
        }
        return tipousuario;
    }
}
