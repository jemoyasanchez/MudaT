package com.itla.mudat;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.itla.mudat.ClasesConsta.ClaseConstante;
import com.itla.mudat.Dao.ConstanteDao;
import com.itla.mudat.Dao.UsuarioDao;
import com.itla.mudat.Entity.Constante;
import com.itla.mudat.Entity.Usuario;


public class IniciarSesionInic extends Fragment implements View.OnClickListener {
    private Button Biniciarsesion;
    private Button Bcancelarsesion;
    private EditText Txtnombreusuario;
    private EditText Txtcontrasena;
    private CheckBox Ckmantenersesion;
    private Usuario usuario;
    private UsuarioDao usuarioDao;
    private Constante constante;
    private ConstanteDao constanteDao;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_iniciar_sesion_inic, container, false);
        try {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

            usuario = new Usuario();
            usuarioDao = new UsuarioDao(getActivity());
            constante = new Constante();
            constanteDao = new ConstanteDao(getActivity());

            Biniciarsesion = (Button) v.findViewById(R.id.Biniciarusuario);
            Bcancelarsesion = (Button) v.findViewById(R.id.Binicusuariocancelar);
            Txtnombreusuario = (EditText) v.findViewById(R.id.Txtloginnombreusuario);
            Txtcontrasena = (EditText) v.findViewById(R.id.Txtloginclave);
            Ckmantenersesion = (CheckBox) v.findViewById(R.id.CKmantenersesion);
            Biniciarsesion.setOnClickListener(this);
            Bcancelarsesion.setOnClickListener(this);
            //     Nuevo();
        } catch (Exception e) {
            toast(e.getMessage());
        }
        return v;

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Biniciarusuario:
                try {
                    if (validar()) {
                        usuario = (Usuario) usuarioDao.BuscarUsuarioSesion(Txtnombreusuario.getText().toString().trim(), Txtcontrasena.getText().toString().trim());
                        if (usuario != null) {
                            if (Ckmantenersesion.isChecked()) {
                                constante.setIp(1);
                                constante.setId(usuario.getId());
                                constanteDao.Crear(constante);
                            }
                            constante.setIp(1);
                            constante.setId(usuario.getId());
                            ClaseConstante.setConstante(constante);
                            ClaseConstante.SetConfiguracionConstate(constante, getActivity());

                            Intent ventana1 = new Intent(getActivity().getApplicationContext(), MenuPrincipal.class);
                            startActivity(ventana1);
                            //  getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        } else {
                            toast("Usuario y/o Contraseña son Incorrectos.");
                            Txtnombreusuario.setError("Ej: juanperez");
                            Txtcontrasena.setError("Ej: **********");
                        }
                    }
                } catch (Exception e) {
                    toast(e.getMessage());
                }
                break;
            case R.id.Binicusuariocancelar:
                try {
                    ContenidoIniciar fragment1 = new ContenidoIniciar();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.idframnglayoutimg, fragment1);
                    fragmentTransaction.commit();
                } catch (Exception e) {
                    toast(e.getMessage());
                }
                break;
            default:
                break;
        }

    }

    private void toast(final String text) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Boolean validar() {
        Boolean val = true;
        if (TextUtils.isEmpty(Txtnombreusuario.getText())) {
            Txtnombreusuario.setError("Ej: juanperez");

            toast("Debe Registrar El Nombre de Usuario.");
            val = false;
        } else {
            Txtnombreusuario.setError(null);
        }

        if (TextUtils.isEmpty(Txtcontrasena.getText())) {
            Txtcontrasena.setError("Ej: **********");
            toast("Debe Registrar la Contraseña.");
            val = false;
        } else {
            Txtcontrasena.setError(null);
        }

        return val;
    }

}
