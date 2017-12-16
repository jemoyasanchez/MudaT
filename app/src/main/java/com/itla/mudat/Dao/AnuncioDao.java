package com.itla.mudat.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleAdapter;

import com.itla.mudat.Entity.Anuncio;
import com.itla.mudat.Entity.Categoria;
import com.itla.mudat.Entity.Constante;
import com.itla.mudat.Entity.Usuario;
import com.itla.mudat.R;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnuncioDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private Anuncio anuncio;
    private Boolean vali = true;
    private static final SimpleDateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
    CategoriaDao categoriaDao;
    public AnuncioDao(Context context) {
        connection = new DBConnection(context);
        categoriaDao=new CategoriaDao(context);
    }

    @Override
    public Boolean Crear(Object item) {
        try {
            vali = true;
            anuncio = (Anuncio) item;
            db = connection.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Anuncio.nomcategoria, String.valueOf(anuncio.getCategoria()));
            cv.put(Anuncio.nomusuario, String.valueOf(anuncio.getUsuario()));
            cv.put(Anuncio.nomfecha, String.valueOf(anuncio.getFecha()));
            cv.put(Anuncio.nomcondicion, anuncio.getCondicion());
            cv.put(Anuncio.nomprecio, DF.format(anuncio.getPrecio()));
            cv.put(Anuncio.nomtitulo, anuncio.getTitulo());
            cv.put(Anuncio.nomubicacion, anuncio.getUbicacion());
            cv.put(Anuncio.nomdetalle, anuncio.getDetalle());
            db.beginTransaction();
            db.insert(Anuncio.nomtableanuncio, null, cv);
            db.endTransaction();
            if (anuncio.getId() == null)
                db.insert(Anuncio.nomtableanuncio, null, cv);

            else
                db.update(Anuncio.nomtableanuncio, cv, Anuncio.nomid + "=?", new String[]{"" + anuncio.getId() + ""});

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
            anuncio = (Anuncio) item;
            db = connection.getWritableDatabase();
            db.delete(Anuncio.nomtableanuncio, Anuncio.nomid + "=?", new String[]{"" + anuncio.getId() + ""});

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.close();
        }
        return true;
    }


    @Override
    public List<?> Listar() {
        List<Anuncio> anuncioslista = new ArrayList<>();

        db = connection.getReadableDatabase();
        String columnas[] = new String[]{Anuncio.nomid, Anuncio.nomusuario,Anuncio.nomcategoria, Anuncio.nomfecha, Anuncio.nomcondicion, Anuncio.nomprecio, Anuncio.nomtitulo, Anuncio.nomubicacion, Anuncio.nomdetalle};
        Cursor cursor = db.query(Anuncio.nomtableanuncio, columnas, null, null, null, null, Anuncio.nomid + " DESC");
        anuncioslista = null;
        try {
            if (cursor.moveToFirst()) {
                anuncioslista = new ArrayList<>();
                while (!cursor.isAfterLast()) {
                    anuncio = new Anuncio();
                    anuncio.setId(cursor.getInt(cursor.getColumnIndex(Anuncio.nomid)));
                    anuncio.setCategoria(cursor.getInt(cursor.getColumnIndex(Anuncio.nomcategoria)));
                    anuncio.setUsuario(  cursor.getInt(cursor.getColumnIndex(Anuncio.nomusuario)));
                   try {
                        anuncio.setFecha(DF.parse(cursor.getString(cursor.getColumnIndex(Anuncio.nomfecha))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    anuncio.setCondicion(cursor.getString(cursor.getColumnIndex(Anuncio.nomcondicion)));
                    anuncio.setPrecio(cursor.getDouble(cursor.getColumnIndex(Anuncio.nomprecio)));
                    anuncio.setTitulo(cursor.getString(cursor.getColumnIndex(Anuncio.nomtitulo)));
                    anuncio.setUbicacion(cursor.getString(cursor.getColumnIndex(Anuncio.nomubicacion)));
                    anuncio.setDetalle(cursor.getString(cursor.getColumnIndex(Anuncio.nomdetalle)));
                    anuncioslista.add(anuncio);
                    cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
            db.close();
        }
        return anuncioslista;
    }
    public List<?> ListaridUsuario(Integer id) {
        List<Anuncio> anuncioslista = new ArrayList<>();
        String where = Anuncio.nomusuario + " = ?";
        String[] whereArgs = {"" + id + ""};
        db = connection.getReadableDatabase();
        String columnas[] = new String[]{Anuncio.nomid, Anuncio.nomusuario,Anuncio.nomcategoria, Anuncio.nomfecha, Anuncio.nomcondicion, Anuncio.nomprecio, Anuncio.nomtitulo, Anuncio.nomubicacion, Anuncio.nomdetalle};
        Cursor cursor = db.query(Anuncio.nomtableanuncio, columnas,  where, whereArgs, null, null, Anuncio.nomid + " DESC");
        anuncioslista = null;
        try {
            if (cursor.moveToFirst()) {
                anuncioslista = new ArrayList<>();
                while (!cursor.isAfterLast()) {
                    anuncio = new Anuncio();
                    anuncio.setId(cursor.getInt(cursor.getColumnIndex(Anuncio.nomid)));
                    anuncio.setCategoria(cursor.getInt(cursor.getColumnIndex(Anuncio.nomcategoria)));
                    anuncio.setUsuario(  cursor.getInt(cursor.getColumnIndex(Anuncio.nomusuario)));
                    try {
                        anuncio.setFecha(DF.parse(cursor.getString(cursor.getColumnIndex(Anuncio.nomfecha))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    anuncio.setCondicion(cursor.getString(cursor.getColumnIndex(Anuncio.nomcondicion)));
                    anuncio.setPrecio(cursor.getDouble(cursor.getColumnIndex(Anuncio.nomprecio)));
                    anuncio.setTitulo(cursor.getString(cursor.getColumnIndex(Anuncio.nomtitulo)));
                    anuncio.setUbicacion(cursor.getString(cursor.getColumnIndex(Anuncio.nomubicacion)));
                    anuncio.setDetalle(cursor.getString(cursor.getColumnIndex(Anuncio.nomdetalle)));
                    anuncioslista.add(anuncio);
                    cursor.moveToNext();
                }
            }

        } finally {
            cursor.close();
            db.close();
        }
        return anuncioslista;
    }

//    public List<Map<String, String>> ListarPorUsuarioID(Integer id) {
//        db = connection.getReadableDatabase();
//        List<Map<String, String>> lista = new ArrayList<Map<String, String>>();
//        try {
//
//            Cursor cursor = db.rawQuery("select Anuncio.*,ifnull((select "+Usuario.nomnombre+" from "+Usuario.nomtableUsuario+" where "+Usuario.nomid+"="+Anuncio.nomusuario+"),'') as " + Usuario.nomnombre + Usuario.nomtableUsuario + ",ifnull ( (select "+Categoria.nomnombre+" from "+Categoria.nomtableCategoria+" where "+Categoria.nomid+"="+Anuncio.nomcategoria+"),'') as " + Categoria.nomnombre + Categoria.nomtableCategoria + " from Anuncio  where Anuncio."+Anuncio.nomusuario+"="+String.valueOf(id)+" order by "+Anuncio.nomid+" Desc ", null);
//
//
//            if (cursor.moveToFirst())
//            {
//                lista = new ArrayList<Map<String, String>>();
//                while (!cursor.isAfterLast())
//                {
//
//                    Map temp = new HashMap();
//                    temp.put(Anuncio.nomid, cursor.getInt(cursor.getColumnIndex(Anuncio.nomid)));
//                    temp.put(Anuncio.nomcategoria, cursor.getInt(cursor.getColumnIndex(Anuncio.nomcategoria)));
//                    temp.put(Categoria.nomnombre, cursor.getString(cursor.getColumnIndex(Categoria.nomnombre+Categoria.nomtableCategoria)));
//                    temp.put(Anuncio.nomusuario, cursor.getInt(cursor.getColumnIndex(Anuncio.nomusuario)));
//                    temp.put(Usuario.nomnombre, cursor.getString(cursor.getColumnIndex(Usuario.nomnombre+Usuario.nomtableUsuario)));
//                    temp.put(Anuncio.nomfecha, cursor.getString(cursor.getColumnIndex(Anuncio.nomfecha)));
//                    temp.put(Anuncio.nomcondicion, cursor.getString(cursor.getColumnIndex(Anuncio.nomcondicion)));
//                    temp.put(Anuncio.nomprecio, cursor.getDouble(cursor.getColumnIndex(Anuncio.nomprecio)));
//                    temp.put(Anuncio.nomtitulo, cursor.getString(cursor.getColumnIndex(Anuncio.nomtitulo)));
//                    temp.put(Anuncio.nomubicacion, cursor.getString(cursor.getColumnIndex(Anuncio.nomubicacion)));
//                    temp.put(Anuncio.nomdetalle, cursor.getString(cursor.getColumnIndex(Anuncio.nomdetalle)));
//                    lista.add(temp);
//                }
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//        return lista;
//    }


//
//    public List<Map<String, String>> ListarPorUsuarios() {
//        db = connection.getReadableDatabase();
//        List<Map<String, String>> lista = new ArrayList<Map<String, String>>();
//        try {
//
//             Cursor cursor = db.rawQuery("select Anuncio.*,ifnull((select "+Usuario.nomnombre+" from "+Usuario.nomtableUsuario+" where "+Usuario.nomid+"="+Anuncio.nomusuario+"),'') as " + Usuario.nomnombre + Usuario.nomtableUsuario + ",ifnull ( (select "+Categoria.nomnombre+" from "+Categoria.nomtableCategoria+" where "+Categoria.nomid+"="+Anuncio.nomcategoria+"),'') as " + Categoria.nomnombre + Categoria.nomtableCategoria + " from Anuncio "+" order by "+Anuncio.nomid+" Desc ", null);
//            db = connection.getReadableDatabase();
//
//                if (cursor.moveToFirst())
//                {
//                    lista = new ArrayList<Map<String, String>>();
//                    while (!cursor.isAfterLast())
//                    {
//
//                        Map temp = new HashMap();
//                        temp.put(Anuncio.nomid, cursor.getInt(cursor.getColumnIndex(Anuncio.nomid)));
//                        temp.put(Anuncio.nomcategoria, cursor.getInt(cursor.getColumnIndex(Anuncio.nomcategoria)));
//                        temp.put(Categoria.nomnombre,Categoria.nomnombre + Categoria.nomtableCategoria );
//                        temp.put(Anuncio.nomusuario, cursor.getInt(cursor.getColumnIndex(Anuncio.nomusuario)));
//                        temp.put(Usuario.nomnombre, cursor.getString(cursor.getColumnIndex(Usuario.nomnombre+Usuario.nomtableUsuario)));
//                        temp.put(Anuncio.nomfecha, cursor.getString(cursor.getColumnIndex(Anuncio.nomfecha)));
//                        temp.put(Anuncio.nomcondicion, cursor.getString(cursor.getColumnIndex(Anuncio.nomcondicion)));
//                        temp.put(Anuncio.nomprecio, cursor.getDouble(cursor.getColumnIndex(Anuncio.nomprecio)));
//                        temp.put(Anuncio.nomtitulo, cursor.getString(cursor.getColumnIndex(Anuncio.nomtitulo)));
//                        temp.put(Anuncio.nomubicacion, cursor.getString(cursor.getColumnIndex(Anuncio.nomubicacion)));
//                        temp.put(Anuncio.nomdetalle, cursor.getString(cursor.getColumnIndex(Anuncio.nomdetalle)));
//                        lista.add(temp);
//                    }
//                }
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//
//            }
//            return lista;
//        }

        @Override
        public Object Buscar ( int item){

            db = connection.getReadableDatabase();
            String where = Anuncio.nomid + " = ?";
            String[] whereArgs = {"" + item + ""};
            String columnas[] = new String[]{Anuncio.nomid, Anuncio.nomusuario, Anuncio.nomfecha, Anuncio.nomcondicion, Anuncio.nomprecio, Anuncio.nomtitulo, Anuncio.nomubicacion, Anuncio.nomdetalle};
            Cursor cursor = db.query(Anuncio.nomtableanuncio, columnas, where, whereArgs, null, null, null);
            anuncio = null;

            try {
                if (cursor.moveToFirst()) {

                    anuncio = new Anuncio();
                    anuncio.setId(cursor.getInt(cursor.getColumnIndex(Anuncio.nomid)));
                    anuncio.setCategoria(cursor.getInt(cursor.getColumnIndex(Anuncio.nomcategoria)));
                    //anuncio.setUsuario(Usuario.va(  cursor.getString(cursor.getColumnIndex(RegistroAnuncio.nomusuario)));
                    // u.setTipousuario(TipoUsuario.valueOf( cursor.getString(cursor.getColumnIndex("tipousuario"))));

                    //  anuncio.setFecha(cursor.getString(cursor.getColumnIndex(RegistroAnuncio.nomfecha)));
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
