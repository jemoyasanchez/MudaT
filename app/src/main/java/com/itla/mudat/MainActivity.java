package com.itla.mudat;

import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itla.mudat.View.ListaUsuario;
import com.itla.mudat.View.RegistroCategoria;
import com.itla.mudat.View.RegistroUsuario;


public class MainActivity extends AppCompatActivity {

  private EditText txnombre;
  private Button bmostrar;
  private Button Bregistrousuario;
  private Button Bcategoria;
  private Button Banuncios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Bregistrousuario=(Button) findViewById(R.id.Breigistrousu);
       Bcategoria=(Button) findViewById(R.id.BCategoria);
       Banuncios=(Button) findViewById(R.id.BAnuncios);

        Bregistrousuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view =new Intent(MainActivity.this,ListaUsuario.class);

                startActivity(view);

            }
        });

        Bcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view=new Intent(MainActivity.this, RegistroCategoria.class);
                startActivity(view);
            }
        });

        Banuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view=new Intent(MainActivity.this, RegistroCategoria.class);
                startActivity(view);
            }
        });
//       bmostrar.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//
//
//               Intent view =new Intent(MainActivity.this,ViewActivity.class);
//               Bundle parametros =new Bundle();
//               parametros.putString("nombre","HOLA: ".concat(txnombre.getText().toString()));
//               view.putExtras(parametros);
//               startActivity(view);
//
//           }
//       });
    }
}
