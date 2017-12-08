package com.itla.mudat.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itla.mudat.Dao.CategoriaDao;
import com.itla.mudat.Entity.Categoria;
import com.itla.mudat.R;

import java.util.List;
import java.util.Map;


public class RegistroCategoria extends AppCompatActivity {
    private EditText Tbnombrecategoria;
    private Button Bnuevo;
    private Button Bguardar;
    private Button Beliminar;
    private Button Bretornar;
    private Button Blistacategoria;
    private Categoria categoria;
    private CategoriaDao Categoria;
    private View rootView;
  //  private SpinnerDialog splistpais;

    List<Map<String, String>> listpais;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_categoria);
    }
}
