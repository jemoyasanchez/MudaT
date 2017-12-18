package com.itla.mudat.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.itla.mudat.ContenidoIniciar;
import com.itla.mudat.Dao.UsuarioDao;
import com.itla.mudat.Entity.Usuario;
import com.itla.mudat.PerfilDeUsuario;
import com.itla.mudat.R;

public class RegistroUsuario extends Fragment implements  View.OnClickListener  {
    private EditText Editnombreusuario;
    private EditText Editidentificacion;
    private EditText Edittelefono;
    private EditText Editcorreoelectronico;
    private EditText Editcontrasena;
    private EditText Editrepetirccontrasena;
    private EditText Editcontrasenaanterior;
    private Button Bregistrar;
    private Button Bcancelar;
    private RadioButton RBpublicador;
    private RadioButton RBcliente;
    private Usuario usuario;
    private UsuarioDao UsuarioDao;
    private static final String LOG_T="RegistroUsuario";
    private String iniclognuevo;
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_registro_usuario,container,false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        Editnombreusuario=(EditText) v.findViewById(R.id.Txtnombreusuario);
        Editidentificacion=(EditText) v.findViewById(R.id.Txtidentificacion);
        Edittelefono=(EditText) v.findViewById(R.id.Txttelefono);
        Editcorreoelectronico=(EditText) v.findViewById(R.id.Txtemail);
        Editcontrasena=(EditText) v.findViewById(R.id.Txtclave);
        Editrepetirccontrasena=(EditText) v.findViewById(R.id.Txtclaverepetir);
        Editcontrasenaanterior=(EditText) v.findViewById(R.id.Txtclaveanterior);
        RBpublicador=(RadioButton)v.findViewById(R.id.tbradiobuttonpublicador);
        RBcliente=(RadioButton)v.findViewById(R.id.tbradiobuttonCliente);
        Bregistrar=(Button) v.findViewById(R.id.Bregistousuario);
        Bregistrar.setOnClickListener(this);
        Bcancelar=(Button) v.findViewById(R.id.Bregistousuariocancelar);
        Bcancelar.setOnClickListener(this);
        RBcliente.setChecked(true);
               Nuevo();

        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle parametros = getArguments();
        if (parametros!=null && parametros.getSerializable(Usuario.nomtableUsuario)!=null){
            usuario=(Usuario) parametros.getSerializable(Usuario.nomtableUsuario);
            Editnombreusuario.setText(usuario.getNombre());
            Editidentificacion.setText(usuario.getIdentificacion());
            Edittelefono.setText(usuario.getTelefono());
            Editcorreoelectronico.setText(usuario.getEmail());

            Editcontrasenaanterior.setVisibility(View.VISIBLE);
        }
        else {
            usuario=new Usuario();

            Editcontrasenaanterior.setVisibility(View.GONE);
        }

        if (parametros!=null && !TextUtils.isEmpty(parametros.getString("nuevo")))
        {
            iniclognuevo=parametros.getString("nuevo");
        }
        else
        {
            iniclognuevo= "nuevo";
        }
    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Bregistousuario:
                try {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Aviso");
                    builder.setMessage("Desea Registrar el Usuario?");
                    builder.setIcon(R.drawable.ic_guardar);

                    builder.setPositiveButton("GUARDAR", new DialogInterface.OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            if (validar()) {
                                UsuarioDao = new UsuarioDao(getActivity());

                                usuario.setNombre(Editnombreusuario.getText().toString());
                                usuario.setIdentificacion(Editidentificacion.getText().toString());
                                usuario.setTelefono(Edittelefono.getText().toString());
                                if(RBcliente.isChecked())
                                usuario.setTipousuario(1);
                                else
                                    usuario.setTipousuario(2);

                                usuario.setEmail(Editcorreoelectronico.getText().toString());
                                usuario.setClave(Editcontrasena.getText().toString());
                                usuario.setStatus(true);


                                Log.i(LOG_T, "Registrando Usuario " + usuario.toString());

                                if (UsuarioDao.Crear(usuario)) {
                                    toast("Usuario Registrado Exitosamente.");
                                    Nuevo();
                                    try {
                                    if (iniclognuevo.equals("login"))
                                    {
                                        ContenidoIniciar fragment1 = new ContenidoIniciar();
                                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction.replace(R.id.idframnglayoutimg, fragment1);
                                        fragmentTransaction.commit();
                                    }
                                    else if(iniclognuevo.equals("perfilusuario"))
                                    {
                                        try {
                                            PerfilDeUsuario fragment1 = new PerfilDeUsuario();
                                            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                            fragmentTransaction.replace(R.id.frameloy, fragment1);
                                            fragmentTransaction.commit();
                                        }catch (Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                   }catch (Exception e){e.printStackTrace();}
                                } else {
                                    toast("Usuario no se  Registrado Exitosamente.");
                                }
                            }
                            else
                            {
                                toast("Debes Completar los datos faltantes.");
                            }

                        }
                    });

                    builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                           return;
                        }
                    });

                    builder.show();

                }catch (Exception e)
                {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Bregistousuariocancelar:

                if (iniclognuevo.equals("login"))
                {
                    try {
                        ContenidoIniciar fragment1 = new ContenidoIniciar();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.idframnglayoutimg, fragment1);
                        fragmentTransaction.commit();
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(iniclognuevo.equals("perfilusuario"))
                {
                    try {
                        PerfilDeUsuario fragment1 = new PerfilDeUsuario();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameloy, fragment1);
                        fragmentTransaction.commit();
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }

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
            toast("Debe Registrar el nombre de Usuario.");
            Valvali=false;
        }
        else
        {
            Editnombreusuario.setError(null);
        }

        if(TextUtils.isEmpty( Editidentificacion.getText()))
        {
            Editidentificacion.setError("Ej. 09903-34443-3444");
            toast("Debe Registrar la Identificacion.");
            Valvali=false;
        }
        else
        {
            Editidentificacion.setError(null);
        }

        if(TextUtils.isEmpty( Edittelefono.getText()))
        {
            Edittelefono.setError("Ej: 829-528-5446");
            toast("Debe Registrar el Nùmero Telefonico.");
            Valvali=false;
        }
        else
        {
            Edittelefono.setError(null);
        }

        if(TextUtils.isEmpty( Editcorreoelectronico.getText()))
        {
            Editcorreoelectronico.setError("Ej: JuanPerez@Itla.com");
            toast("Debe Registrar el Correo Electronico.");
            Valvali=false;
        }
        else
        {
            Editcorreoelectronico.setError(null);
        }

        if(TextUtils.isEmpty( Editcontrasena.getText()))
        {
            Editcontrasena.setError("Ej: **********");
            toast("Debe Registrar la Contraseña.");
            Valvali=false;
        }
        else
        {
            Editcontrasena.setError(null);
        }

        if(TextUtils.isEmpty( Editrepetirccontrasena.getText()))
        {
            Editrepetirccontrasena.setError("Ej: *****");
            toast("Debe Registrar Repetir la Contraseña.");
            Valvali=false;
        }
        else
        {
            Editrepetirccontrasena.setError(null);
        }

        if(!Editcontrasena.getText().toString().trim().equals(   Editrepetirccontrasena.getText().toString().trim()))
        {
            Editrepetirccontrasena.setError("Ej: *****");
            Editcontrasena.setError("Ej: **********");
            toast(" Contraseña no Coinciden.");
            Valvali=false;
        }
        else
        {
            Editrepetirccontrasena.setError(null);
            Editcontrasena.setError(null);
        }

        return  Valvali;
    }

    private void toast(final String text) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
