package com.itla.mudat.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.Entity.MeGusta;

import java.security.spec.ECField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MeGustaDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private MeGusta megusta;
    Boolean vali = true;

    public MeGustaDao(Context context) {
        connection = new DBConnection(context);
    }

    @Override
    public Boolean Crear(Object item) {
        try {
            vali = true;
            megusta = (MeGusta) item;
            db = connection.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(MeGusta.nomid,megusta.getId());
            cv.put(MeGusta.nomusuario, megusta.getUsuario());
            cv.put(MeGusta.nomanuncio, megusta.getAnuncio());
            cv.put(MeGusta.nomgusta, megusta.getGusta());
            if (megusta.getId() ==null) {
                db.insert(MeGusta.nomtableMeGusta, null, cv);
            } else {
                db.delete(MeGusta.nomtableMeGusta, MeGusta.nomid + "=?", new String[]{"" + megusta.getId() + ""});
            }
        } catch (Exception e)
        {
            vali = false;
            e.printStackTrace();

        } finally {
            if (db.isOpen()) {
                db.close();
            }
        }
        return vali;
    }


    @Override
    public Boolean Eliminar(Object item) {

        try {
            megusta = (MeGusta) item;
            db = connection.getWritableDatabase();

            db.delete(MeGusta.nomtableMeGusta, MeGusta.nomid + "=?", new String[]{"" + megusta.getId() + ""});
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<?> Listar() throws SQLException {
        List<MeGusta> comentarioslistar = new ArrayList<>();

        db = connection.getReadableDatabase();
        String columnas[] = new String[]{MeGusta.nomid, MeGusta.nomusuario, MeGusta.nomanuncio, MeGusta.nomgusta};
        Cursor cursor = db.query(MeGusta.nomtableMeGusta, columnas, null, null, null, null, null);
        comentarioslistar = null;
        try {
            if (cursor.moveToFirst()) {
                comentarioslistar = new ArrayList<>();
                while (!cursor.isAfterLast()) {

                    megusta.setId(cursor.getInt(cursor.getColumnIndex(MeGusta.nomid)));
                    megusta.setUsuario(cursor.getInt(cursor.getColumnIndex(MeGusta.nomusuario)));
                    megusta.setAnuncio(cursor.getInt(cursor.getColumnIndex(MeGusta.nomanuncio)));
                    megusta.setGusta(cursor.getInt(cursor.getColumnIndex(MeGusta.nomgusta)));
                    comentarioslistar.add(megusta);
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
        String where = MeGusta.nomid + " = ?";
        String[] whereArgs = {"" + item + ""};
        String columnas[] = new String[]{MeGusta.nomid, MeGusta.nomusuario, MeGusta.nomanuncio, MeGusta.nomgusta};
        Cursor cursor = db.query(MeGusta.nomtableMeGusta, columnas, where, whereArgs, null, null, null);

        megusta = null;
        try {
            if (cursor.moveToFirst()) {
                megusta = new MeGusta();
                megusta.setId(cursor.getInt(cursor.getColumnIndex(MeGusta.nomid)));
                megusta.setUsuario(cursor.getInt(cursor.getColumnIndex(MeGusta.nomusuario)));
                megusta.setAnuncio(cursor.getInt(cursor.getColumnIndex(MeGusta.nomanuncio)));
                megusta.setGusta(cursor.getInt(cursor.getColumnIndex(MeGusta.nomgusta)));

            }
        } finally {
            cursor.close();
            db.close();
        }
        return megusta;
    }

    public Object Buscarid(Integer anuncio, Integer cliente) {
        db = connection.getReadableDatabase();
        String where = MeGusta.nomanuncio + "=? and  " + MeGusta.nomusuario + "=? ";
        String[] whereArgs = {"" + anuncio + "," + cliente + ""};
        String columnas[] = new String[]{MeGusta.nomid, MeGusta.nomusuario, MeGusta.nomanuncio, MeGusta.nomgusta};
        Cursor cursor = db.query(MeGusta.nomtableMeGusta, columnas, where, whereArgs, null, null, null);

        megusta = null;
        try {
            if (cursor.moveToFirst()) {
                megusta = new MeGusta();
                megusta.setId(cursor.getInt(cursor.getColumnIndex(MeGusta.nomid)));
                megusta.setUsuario(cursor.getInt(cursor.getColumnIndex(MeGusta.nomusuario)));
                megusta.setAnuncio(cursor.getInt(cursor.getColumnIndex(MeGusta.nomanuncio)));
                megusta.setGusta(cursor.getInt(cursor.getColumnIndex(MeGusta.nomgusta)));

            }
        }catch (Exception e){
            e.printStackTrace();}
        finally {
            cursor.close();
            db.close();
        }
        return megusta;
    }
}