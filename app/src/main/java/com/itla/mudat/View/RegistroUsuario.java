package com.itla.mudat.View;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itla.mudat.Dao.UsuarioDao;
import com.itla.mudat.Entity.Usuario;
import com.itla.mudat.R;

import java.util.ArrayList;
import java.util.List;

public class RegistroUsuario extends AppCompatActivity {
    private EditText Editnombreusuario;
    private EditText Editidentificacion;
    private EditText Edittelefono;
    private EditText Editcorreoelectronico;
    private EditText Editcontrasena;
    private EditText Editrepetirccontrasena;
    private Button Bregistrar;
    private Button Blistarusuarios;
    private Usuario usuario;
    private UsuarioDao UsuarioDao;
    private static final String LOG_T="RegistroUsuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_registro_usuario);

        Editnombreusuario=(EditText) findViewById(R.id.Txtnombreusuario);
        Editidentificacion=(EditText) findViewById(R.id.Txtidentificacion);
        Edittelefono=(EditText) findViewById(R.id.Txttelefono);
        Editcorreoelectronico=(EditText) findViewById(R.id.Txtemail);
        Editcontrasena=(EditText) findViewById(R.id.Txtclave);
        Editrepetirccontrasena=(EditText) findViewById(R.id.Txtclaverepetir);
        Bregistrar=(Button) findViewById(R.id.Bregistousuario);
        Blistarusuarios=(Button) findViewById(R.id.Bregistousuariolistar);
        Nuevo();


        Bregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    UsuarioDao = new UsuarioDao(getApplicationContext());

                    usuario.setNombre(Editnombreusuario.getText().toString());
                    usuario.setIdentificacion(Editidentificacion.getText().toString());
                    usuario.setTelefono(Edittelefono.getText().toString());
                    usuario.setEmail(Editcorreoelectronico.getText().toString());
                    usuario.setClave(Editcontrasena.getText().toString());
                    usuario.setStatus(true);


                    Log.i(LOG_T, "Registrando Usuario " + usuario.toString());
                    UsuarioDao.Crear(usuario);
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                Nuevo();
            }
        });
Blistarusuarios.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try {
            UsuarioDao = new UsuarioDao(getApplicationContext());
            List<Usuario> listausu =new ArrayList<Usuario>();
            listausu=(List<Usuario> ) UsuarioDao.Listar();
            if (listausu!=null) {
                for (Usuario usu : listausu) {
                    Toast.makeText(getApplicationContext(), usu.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
});

    }

    private void Nuevo()
    {
        usuario=new Usuario();
        Editnombreusuario.setText("");
        Editidentificacion.setText("");
        Edittelefono.setText("");
        Editcorreoelectronico.setText("");
        Editcontrasena.setText("");
        Editrepetirccontrasena.setText("");
    }

    private Boolean validar()
    {
        Boolean Valvali=true;

        if(TextUtils.isEmpty( Editnombreusuario.getText()))
        {
            Editnombreusuario.setError("Ej: Juan Perez");
            Toast.makeText(RegistroUsuario.this,"Debe Registrar el nombre de Usuario.",Toast.LENGTH_LONG).show();
            Valvali=false;
        }
        else
        {
            Editnombreusuario.setError(null);
        }

        if(TextUtils.isEmpty( Editidentificacion.getText()))
        {
            Editidentificacion.setError("Ej. 09903-34443-3444");
            Toast.makeText(RegistroUsuario.this,"Debe Registrar la Identificacion.",Toast.LENGTH_LONG).show();
            Valvali=false;
        }
        else
        {
            Editidentificacion.setError(null);
        }

        if(TextUtils.isEmpty( Edittelefono.getText()))
        {
            Edittelefono.setError("Ej: 829-528-5446");
            Toast.makeText(RegistroUsuario.this,"Debe Registrar el Nùmero Telefonico.",Toast.LENGTH_LONG).show();
            Valvali=false;
        }
        else
        {
            Edittelefono.setError(null);
        }

        if(TextUtils.isEmpty( Editcorreoelectronico.getText()))
        {
            Editcorreoelectronico.setError("Ej: JuanPerez@Itla.com");
            Toast.makeText(RegistroUsuario.this,"Debe Registrar el Correo Electronico.",Toast.LENGTH_LONG).show();
            Valvali=false;
        }
        else
        {
            Editcorreoelectronico.setError(null);
        }

        if(TextUtils.isEmpty( Editcontrasena.getText()))
        {
            Editcontrasena.setError("Ej: **********");
            Toast.makeText(RegistroUsuario.this,"Debe Registrar la Contraseña.",Toast.LENGTH_LONG).show();
            Valvali=false;
        }
        else
        {
            Editcontrasena.setError(null);
        }

        if(TextUtils.isEmpty( Editrepetirccontrasena.getText()))
        {
            Editrepetirccontrasena.setError("Ej: *****");
            Toast.makeText(RegistroUsuario.this,"Debe Registrar Repetir la Contraseña.",Toast.LENGTH_LONG).show();
            Valvali=false;
        }
        else
        {
            Editrepetirccontrasena.setError(null);
        }

        if(Editcontrasena.getText().toString().trim()!=Editrepetirccontrasena.getText().toString().trim())
        {
            Editrepetirccontrasena.setError("Ej: *****");
            Editcontrasena.setError("Ej: **********");
            Toast.makeText(RegistroUsuario.this,"La Contraseña no Coinciden.",Toast.LENGTH_LONG).show();
            Valvali=false;
        }
        else
        {
            Editrepetirccontrasena.setError(null);
            Editcontrasena.setError(null);
        }

        return  Valvali;
    }


}
