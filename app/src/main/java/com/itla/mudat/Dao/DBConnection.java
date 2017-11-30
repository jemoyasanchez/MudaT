package com.itla.mudat.Dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBConnection extends SQLiteOpenHelper {
    public static String DATABASE_NAME="mudat.db";
    public static String LOG_T="DbConnection";

    public DBConnection(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
             Log.i(LOG_T,"Iniciando onCreate...");
             db.execSQL(SqlHelperSchema.TIPOUSUARIO_TABLE);
             db.execSQL(SqlHelperSchema.USUARIO_TABLE);
             db.execSQL(SqlHelperSchema.CATEGORIA_TABLE);
             db.execSQL(SqlHelperSchema.ANUNCIO_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
