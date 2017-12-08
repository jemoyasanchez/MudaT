package com.itla.mudat.Dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.itla.mudat.Entity.Anuncio;
import com.itla.mudat.Entity.Categoria;
import com.itla.mudat.Entity.Usuario;

public class DBConnection extends SQLiteOpenHelper {
    public static String DATABASE_NAME="mudat.db";
    public static String LOG_T="DbConnection";

    public DBConnection(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            Log.i(LOG_T, "Iniciando onCreate...");
            db.execSQL(SqlHelperSchema.TIPOUSUARIO_TABLE);
            db.execSQL(SqlHelperSchema.USUARIO_TABLE);
            db.execSQL(SqlHelperSchema.CATEGORIA_TABLE);
            db.execSQL(SqlHelperSchema.ANUNCIO_TABLE);
            db.execSQL(SqlHelperSchema.CONSTANTE_TABLE);

            dataprueba(db);
        }catch (Exception e)
        {e.printStackTrace();}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

private void dataprueba(SQLiteDatabase db)
{
    try {
        db.execSQL("INSERT or replace INTO "+ Categoria.nomtableCategoria+" ("+Categoria.nomid+","+Categoria.nomnombre+") values(1,'Venta');");
        db.execSQL("INSERT or replace INTO "+ Categoria.nomtableCategoria+" ("+Categoria.nomid+","+Categoria.nomnombre+") values(2,'Renta');");

        db.execSQL("INSERT or replace INTO "+ Usuario.nomtableUsuario+" ("+Usuario.nomid+","+ Usuario.nomnombre+","+ Usuario.nomtipousuario+","+ Usuario.nomidentificacion+"," +Usuario.nomemail+","+ Usuario.nomtelefono+","+Usuario.nomclave+","+Usuario.nomstatus+") values(1,'johan',1,'124567','johan@gmail.com','829-867-8635','1234',1);");
        db.execSQL("INSERT or replace INTO "+ Usuario.nomtableUsuario+" ("+Usuario.nomid+","+ Usuario.nomnombre+","+ Usuario.nomtipousuario+","+ Usuario.nomidentificacion+"," +Usuario.nomemail+","+ Usuario.nomtelefono+","+Usuario.nomclave+","+Usuario.nomstatus+") values(2,'maria',1,'12gfg34567','maria@gmail.com','829-847-8465','1234',1);");
        db.execSQL("INSERT or replace INTO "+ Usuario.nomtableUsuario+" ("+Usuario.nomid+","+ Usuario.nomnombre+","+ Usuario.nomtipousuario+","+ Usuario.nomidentificacion+"," +Usuario.nomemail+","+ Usuario.nomtelefono+","+Usuario.nomclave+","+Usuario.nomstatus+") values(3,'luis',1,'1342347','luis@gmail.com','829-347-8536','1234',1);");
        db.execSQL("INSERT or replace INTO "+ Usuario.nomtableUsuario+" ("+Usuario.nomid+","+ Usuario.nomnombre+","+ Usuario.nomtipousuario+","+ Usuario.nomidentificacion+"," +Usuario.nomemail+","+ Usuario.nomtelefono+","+Usuario.nomclave+","+Usuario.nomstatus+") values(4,'josefina',1,'104e234567','josefina@gmail.com','829-363-7636','1234',1);");
        db.execSQL("INSERT or replace INTO "+ Usuario.nomtableUsuario+" ("+Usuario.nomid+","+ Usuario.nomnombre+","+ Usuario.nomtipousuario+","+ Usuario.nomidentificacion+"," +Usuario.nomemail+","+ Usuario.nomtelefono+","+Usuario.nomclave+","+Usuario.nomstatus+") values(5,'justin',1,'32443127','justin@gmail.com','829-467-4645','1234',1);");
        db.execSQL("INSERT or replace INTO "+ Usuario.nomtableUsuario+" ("+Usuario.nomid+","+ Usuario.nomnombre+","+ Usuario.nomtipousuario+","+ Usuario.nomidentificacion+"," +Usuario.nomemail+","+ Usuario.nomtelefono+","+Usuario.nomclave+","+Usuario.nomstatus+") values(6,'pepe',1,'143424334567','pepe@gmail.com','829-767-7645','1234',1);");


        db.execSQL("INSERT or replace INTO "+ Anuncio.nomtableanuncio+" ("+  Anuncio.nomid+","+Anuncio.nomcategoria+","+Anuncio.nomusuario+","+ Anuncio.nomfecha+","+ Anuncio.nomcondicion+","+ Anuncio.nomprecio+","+ Anuncio.nomtitulo+","+  Anuncio.nomubicacion+","+ Anuncio.nomdetalle+") values(1,1,1,'12/12/2017','Nueva','2000000','Casa nueva en los cerros de gurabo','Santiago','');");
        db.execSQL("INSERT or replace INTO "+ Anuncio.nomtableanuncio+" ("+  Anuncio.nomid+","+Anuncio.nomcategoria+","+Anuncio.nomusuario+","+ Anuncio.nomfecha+","+ Anuncio.nomcondicion+","+ Anuncio.nomprecio+","+ Anuncio.nomtitulo+","+  Anuncio.nomubicacion+","+ Anuncio.nomdetalle+") values(2,2,2,'13/12/2017','Nueva','2000000','Casa en cerro alto Santiago RD','Santiago','');");
        db.execSQL("INSERT or replace INTO "+ Anuncio.nomtableanuncio+" ("+  Anuncio.nomid+","+Anuncio.nomcategoria+","+Anuncio.nomusuario+","+ Anuncio.nomfecha+","+ Anuncio.nomcondicion+","+ Anuncio.nomprecio+","+ Anuncio.nomtitulo+","+  Anuncio.nomubicacion+","+ Anuncio.nomdetalle+") values(3,1,3,'12/12/2017','Nueva','2000000','Casa nueva en los cerros de gurabo','Santo Domingo','');");
        db.execSQL("INSERT or replace INTO "+ Anuncio.nomtableanuncio+" ("+  Anuncio.nomid+","+Anuncio.nomcategoria+","+Anuncio.nomusuario+","+ Anuncio.nomfecha+","+ Anuncio.nomcondicion+","+ Anuncio.nomprecio+","+ Anuncio.nomtitulo+","+  Anuncio.nomubicacion+","+ Anuncio.nomdetalle+") values(4,2,1,'12/12/2017','Nueva','2000000','Casa nueva en los cerros de gurabo','Santiago','');");
        db.execSQL("INSERT or replace INTO "+ Anuncio.nomtableanuncio+" ("+  Anuncio.nomid+","+Anuncio.nomcategoria+","+Anuncio.nomusuario+","+ Anuncio.nomfecha+","+ Anuncio.nomcondicion+","+ Anuncio.nomprecio+","+ Anuncio.nomtitulo+","+  Anuncio.nomubicacion+","+ Anuncio.nomdetalle+") values(5,1,2,'11/12/2017','Nueva','2000000','Casa nueva en los cerros de gurabo','Puerto plata','');");
        db.execSQL("INSERT or replace INTO "+ Anuncio.nomtableanuncio+" ("+  Anuncio.nomid+","+Anuncio.nomcategoria+","+Anuncio.nomusuario+","+ Anuncio.nomfecha+","+ Anuncio.nomcondicion+","+ Anuncio.nomprecio+","+ Anuncio.nomtitulo+","+  Anuncio.nomubicacion+","+ Anuncio.nomdetalle+") values(6,2,3,'01/12/2017','Nueva','2000000','Casa nueva en los cerros de gurabo','Santiago','');");
        db.execSQL("INSERT or replace INTO "+ Anuncio.nomtableanuncio+" ("+  Anuncio.nomid+","+Anuncio.nomcategoria+","+Anuncio.nomusuario+","+ Anuncio.nomfecha+","+ Anuncio.nomcondicion+","+ Anuncio.nomprecio+","+ Anuncio.nomtitulo+","+  Anuncio.nomubicacion+","+ Anuncio.nomdetalle+") values(7,2,4,'15/12/2017','Nueva','2000000','Casa en el jobo tamboril santiago rd','Santiago','');");
        db.execSQL("INSERT or replace INTO "+ Anuncio.nomtableanuncio+" ("+  Anuncio.nomid+","+Anuncio.nomcategoria+","+Anuncio.nomusuario+","+ Anuncio.nomfecha+","+ Anuncio.nomcondicion+","+ Anuncio.nomprecio+","+ Anuncio.nomtitulo+","+  Anuncio.nomubicacion+","+ Anuncio.nomdetalle+") values(8,1,5,'12/12/2017','Nueva','2000000','Casa amplia de tres habitaciones','Santiago','');");
    }
    catch (Exception e)
    {e.printStackTrace();}
}
}
