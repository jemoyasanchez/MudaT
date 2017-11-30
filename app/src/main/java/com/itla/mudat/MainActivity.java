package com.itla.mudat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itla.mudat.View.RegistroUsuario;

public class MainActivity extends AppCompatActivity {

  private EditText txnombre;
  private Button bmostrar;
  private Button Bregistrousuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       txnombre=(EditText) findViewById(R.id.TXTnombre);
       bmostrar=(Button) findViewById(R.id.BTNmostrar);
       Bregistrousuario=(Button) findViewById(R.id.Breigistrousu);

        Bregistrousuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view =new Intent(MainActivity.this,RegistroUsuario.class);

                startActivity(view);

            }
        });
       bmostrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               txnombre.setError(null);
//if (!TextUtils.isEmpty(txnombre.getText())) {
//    Toast.makeText(MainActivity.this,"HOLA: ".concat(txnombre.getText().toString()), Toast.LENGTH_LONG).show();
//}
//else
//{
//    Toast.makeText(MainActivity.this,"Debe proporcionar el texto.", Toast.LENGTH_LONG).show();
//    txnombre.setError("texto");
//}

               Intent view =new Intent(MainActivity.this,ViewActivity.class);
               Bundle parametros =new Bundle();
               parametros.putString("nombre","HOLA: ".concat(txnombre.getText().toString()));
               view.putExtras(parametros);
               startActivity(view);

           }
       });
    }
}
