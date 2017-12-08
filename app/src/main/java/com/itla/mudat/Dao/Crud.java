package com.itla.mudat.Dao;

import java.sql.SQLException;
import java.util.List;

    interface Crud
    {
        Boolean Crear(Object item);

        Boolean Eliminar(Object item);
        List<?> Listar() throws SQLException;
        Object Buscar(int item);
    }
