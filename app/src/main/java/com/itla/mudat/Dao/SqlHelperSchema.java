package com.itla.mudat.Dao;

import com.itla.mudat.Entity.*;

public class SqlHelperSchema {

     public static final String TIPOUSUARIO_TABLE   ="CREATE TABLE IF NOT EXISTS "+ TipoUsuario.nomtableTipoUsuario+" ("+TipoUsuario.nomid+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TipoUsuario.nomnombre+" TEXT NOT NULL );";
     public static final String USUARIO_TABLE ="CREATE TABLE IF NOT EXISTS "+Usuario.nomtableUsuario+" ( "+Usuario.nomid+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Usuario.nomnombre+" TEXT NOT NULL, "+Usuario.nomtipousuario+" INTEGER , "+Usuario.nomidentificacion+" TEXT NOT NULL, "+Usuario.nomemail+" TEXT NOT NULL, "+Usuario.nomtelefono+" TEXT NOT NULL, "+Usuario.nomclave+" TEXT NOT NULL, "+Usuario.nomstatus+" BLOB NOT NULL , FOREIGN KEY("+Usuario.nomtipousuario+") REFERENCES "+TipoUsuario.nomtableTipoUsuario+"("+TipoUsuario.nomid+") ); ";
     public static final String CATEGORIA_TABLE="CREATE TABLE IF NOT EXISTS "+ Categoria.nomtableCategoria+" ("+Categoria.nomid+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Categoria.nomnombre+" TEXT NOT NULL );";
     public static final String ANUNCIO_TABLE="CREATE TABLE IF NOT EXISTS "+Anuncio.nomtableanuncio+" ("  +Anuncio.nomid+"   INTEGER PRIMARY KEY AUTOINCREMENT,"+Anuncio.nomcategoria+" INTEGER NOT NULL,"+Anuncio.nomusuario+" INTEGER NOT NULL, "+Anuncio.nomfecha+" REAL NOT NULL,"+Anuncio.nomcondicion+" INTEGER,"+Anuncio.nomprecio+"  NUMERIC DEFAULT 0,"+Anuncio.nomtitulo+" TEXT NOT NULL,"+Anuncio.nomubicacion+" TEXT NOT NULL,"+Anuncio.nomdetalle+" TEXT, FOREIGN KEY("+Anuncio.nomusuario+") REFERENCES "+Usuario.nomtableUsuario+"("+Usuario.nomid+"), FOREIGN KEY("+Anuncio.nomcategoria+") REFERENCES "+Categoria.nomtableCategoria+"("+Categoria.nomid+"));";
     public static final String CONSTANTE_TABLE="CREATE TABLE IF NOT EXISTS "+Constante.nomtableConstante+" ("+Constante.nomid+" INTERGER PRIMARY KEY);";



 }
