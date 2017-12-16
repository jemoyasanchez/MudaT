package com.itla.mudat;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itla.mudat.Entity.Usuario;
import com.itla.mudat.View.RegistroUsuario;

public class ContenidoIniciar extends Fragment implements View.OnClickListener {

    private Button Biniciarsesion;
    private Button Bcancelarsesion;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_contenido_iniciar, container, false);
        try {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

            Biniciarsesion = (Button) v.findViewById(R.id.idsplasjiniciarsesioncon);
            Bcancelarsesion = (Button) v.findViewById(R.id.idsplasjcrearusuariocon);
            Biniciarsesion.setOnClickListener(this);
            Bcancelarsesion.setOnClickListener(this);
            v.findViewById(R.id.idfragmentactiviprincipal).setVisibility(View.GONE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    v.findViewById(R.id.idfragmentactiviprincipal).setVisibility(View.VISIBLE);
                }
            }, 5000);

        } catch (Exception e) {
            toast(e.getMessage());
        }
        return v;

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.idsplasjiniciarsesioncon:


                try {

                    IniciarSesionInic fragment1 = new IniciarSesionInic();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.idframnglayoutimg, fragment1);
                    fragmentTransaction.commit();


                } catch (Exception e) {
                    toast(e.getMessage());
                }
                break;
            case R.id.idsplasjcrearusuariocon:
                try {

                    RegistroUsuario fragment1 = new RegistroUsuario();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.idframnglayoutimg, fragment1);
                    Bundle args = new Bundle();
                    args.putSerializable(Usuario.nomtableUsuario, null);
                    args.putString("nuevo", "login");
                    fragment1.setArguments(args);
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

}