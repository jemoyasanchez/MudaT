package com.itla.mudat.Dao;


import android.database.sqlite.SQLiteDatabase;

import com.itla.mudat.Entity.Categoria;


import java.sql.SQLException;
import java.util.List;

public class CategoriaDao implements Crud {
    private DBConnection connection;
    private SQLiteDatabase db;
    private Categoria categoria;

    @Override
    public Boolean Crear(Object item) {
        return null;
    }

    @Override
    public Boolean Actualizar(Object item) {
        return null;
    }

    @Override
    public Boolean Eliminar(Object item) {
        return null;
    }

    @Override
    public List<?> Listar() throws SQLException {
        return null;
    }

    @Override
    public Object Buscar(int item) {
        return null;
    }
}
