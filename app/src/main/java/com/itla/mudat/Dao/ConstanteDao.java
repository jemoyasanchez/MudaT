package com.itla.mudat.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.Entity.Constante;

import java.util.ArrayList;
import java.util.List;

public class ConstanteDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private Constante constante;
    Boolean vali=true;
    public ConstanteDao(Context context)
    {
        connection = new DBConnection(context);
    }


    @Override
    public Boolean Crear(Object item) {

        try {
            vali=true;
            constante = (Constante) item;
            db = connection.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Constante.nomid, constante.getId());

            if (constante.getId()==null )
                db.insert(Constante.nomtableConstante, null, cv);

            else
                db.update(Constante.nomtableConstante,  cv,Constante.nomid+"=?",new String[] {""+constante.getId()+""} );

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
            constante = (Constante) item;
            db = connection.getWritableDatabase();
            db.delete(Constante.nomtableConstante, Constante.nomid+"=?",new String[] {""+constante.getId()+""});
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
    public List<Constante> Listar()   {
        List<Constante> constantelista= new ArrayList<>();
        db = connection.getReadableDatabase();
        String columnas[] = new String[]{Constante.nomid};
        Cursor cursor = db.query(Constante.nomtableConstante, columnas, null, null, null, null, null);
        constantelista=null;
        try
        {
            if (cursor.moveToFirst()) {
                constantelista=new ArrayList<>();
                while (!cursor.isAfterLast()) {
                    constante = new Constante();
                    constante.setId(cursor.getInt(cursor.getColumnIndex(Constante.nomid)));

                    constantelista.add(constante);
                    cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
            // db.close();
        }
        return constantelista;
    }

    @Override
    public Object Buscar(int item) {
        db =  connection.getReadableDatabase();
        String where = Constante.nomid + " = ?";
        String[] whereArgs = {""+item+""};
        String columnas[] = new String[]{Constante.nomid};
        Cursor cursor = db.query(Constante.nomtableConstante, columnas, where, whereArgs, null, null, null);

        constante = null;
        try {
            if (cursor.moveToFirst())
            {

                constante = new Constante();
                constante.setId(cursor.getInt(cursor.getColumnIndex(Constante.nomid)));

            }
        } finally {
            cursor.close();
            db.close();
        }
        return constante;
    }
}