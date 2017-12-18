package com.itla.mudat;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itla.mudat.ClasesConsta.ClaseConstante;
import com.itla.mudat.Dao.AnuncioDao;
import com.itla.mudat.Entity.Anuncio;
import com.itla.mudat.Entity.Usuario;

import com.itla.mudat.ListAdapter.AnuncioListAdapter;
import com.itla.mudat.View.RegistroAnuncio;
import com.itla.mudat.View.RegistroUsuario;

import java.util.ArrayList;
import java.util.List;

public class PerfilDeUsuario extends Fragment {
    private static ListView listViewpublicacion;
    private static Button Bregistroanuncio;
    private TextView txtnombre;
    private TextView txtemail;
    private TextView txtidentifi;
    private TextView txttipousuario;
    private TextView txttelefono;
    private Usuario usuario;
    private Button Beditarperfil;

    private String iniclognuevo;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_perfil_de_usuario, container, false);
        try {


            txtnombre = (TextView) v.findViewById(R.id.idperfilnombre);
            txtemail = (TextView) v.findViewById(R.id.idperfilemail);
            txtidentifi = (TextView) v.findViewById(R.id.idperfilidentificacion);
            txttipousuario = (TextView) v.findViewById(R.id.idperfiltipocliente);
            txttelefono = (TextView) v.findViewById(R.id.idperfiltelefono);
            Beditarperfil = (Button) v.findViewById(R.id.Bperfileditarusuario);
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            listViewpublicacion = (ListView) v.findViewById(R.id.listviewperfildeusuario);
            Bregistroanuncio = (Button) v.findViewById(R.id.Bperfilusuariiregistroanuncio);


            Bregistroanuncio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vw) {
                    try {
                        try {
                            RegistroAnuncio fragment1 = new RegistroAnuncio();
                            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frameloy, fragment1);
                            Bundle args = new Bundle();

                            args.putSerializable(Anuncio.nomtableanuncio, null);
                            args.putString("nuevo", "perfilusuario");
                            fragment1.setArguments(args);
                            fragmentTransaction.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            Beditarperfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vw) {
                    try {
                        try {

                            RegistroUsuario fragment1 = new RegistroUsuario();
                            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frameloy, fragment1);
                            Bundle args = new Bundle();

                            args.putSerializable(Usuario.nomtableUsuario, ClaseConstante.getUsuario());
                            args.putString("nuevo", "perfilusuario");
                            fragment1.setArguments(args);
                            fragmentTransaction.commit();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle parametros = getArguments();
        if (parametros != null && parametros.getSerializable(Usuario.nomtableUsuario) != null) {
            usuario = (Usuario) parametros.getSerializable(Usuario.nomtableUsuario);
            if (usuario != null) {
                txtnombre.setText(usuario.getNombre());
                txtemail.setText(usuario.getEmail());
                txtidentifi.setText(usuario.getIdentificacion());

                if (usuario.getTipousuario() == 1) {
                    txttipousuario.setText("Cliente");
                } else {
                    txttipousuario.setText("Publicador");
                }
                txttelefono.setText(usuario.getTelefono());
            }
        }
        if (parametros != null && !TextUtils.isEmpty(parametros.getString("nuevo"))) {
            iniclognuevo = parametros.getString("nuevo");
        } else {
            iniclognuevo = "nuevo";
        }

        cargaranuncios(getActivity());

    }

    public void cargaranuncios(Activity activity) {
        try {
            AnuncioDao anuncioDao = new AnuncioDao(activity);

            List<Anuncio> anunciosl = new ArrayList<>();
            anunciosl = (List<Anuncio>) anuncioDao.ListaridUsuario(usuario.getId());
            if (anunciosl != null) {
                listViewpublicacion.setAdapter(new AnuncioListAdapter(anunciosl, activity));
            } else {
                listViewpublicacion.setAdapter(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}