package com.itla.mudat.ListAdapter;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itla.mudat.ClasesConsta.ClaseConstante;
import com.itla.mudat.Dao.MeGustaDao;
import com.itla.mudat.Dao.UsuarioDao;
import com.itla.mudat.Entity.Anuncio;
import com.itla.mudat.Entity.MeGusta;
import com.itla.mudat.Entity.Usuario;
import com.itla.mudat.R;
import com.itla.mudat.View.RegistroAnuncio;

import java.util.List;

public class AnuncioListAdapter extends BaseAdapter {

    private List<Anuncio> anuncios;
    private Activity context;
    private Anuncio u;

    public AnuncioListAdapter(List<Anuncio> anuncios, Activity context) {
        this.anuncios = anuncios;
        this.context = context;
    }

    @Override
    public int getCount() {
        return anuncios.size();
    }

    @Override
    public Object getItem(int position) {
        return anuncios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.listview_anuncio_row, null);
        }
        try {
            TextView luCodigo = view.findViewById(R.id.LUcodigoAnuncio);
            TextView luTitulo = view.findViewById(R.id.LUtituloanuncio);
            TextView luDetalle = view.findViewById(R.id.LUDetalleanuncio);
            TextView luprecio = view.findViewById(R.id.LUprecioanuncio);
            TextView luubicacion = view.findViewById(R.id.LUbicacion);
            TextView luusuario = view.findViewById(R.id.LUusuario);
            ImageButton luimagebutton = view.findViewById(R.id.ib_popup_menu);
            final Button meg = view.findViewById(R.id.idmegusta);
            Button editar = view.findViewById(R.id.ideditarcc);
            u = anuncios.get(position);

            luCodigo.setText(String.valueOf(u.getId()));
            luTitulo.setText(u.getTitulo().toString().toUpperCase());
            luDetalle.setText(u.getDetalle().toString().toUpperCase());
            luCodigo.setText(String.valueOf(u.getId()));
            luprecio.setText(String.valueOf(u.getPrecio()));
            luubicacion.setText(u.getUbicacion());

            try {
                Usuario usuario = new Usuario();
                UsuarioDao usuarioDao = new UsuarioDao(context);
                usuario = (Usuario) usuarioDao.Buscar(u.getUsuario());
                if (usuario != null) {
                    luusuario.setText(usuario.getNombre().toString());
                } else {
                    luusuario.setText("none");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                MeGusta meGusta = new MeGusta();
                MeGustaDao meGustaDao = new MeGustaDao(context);
                meGusta = (MeGusta) meGustaDao.Buscarid(u.getId(), u.getUsuario());
                if (meGusta != null) {
                    meg.setBackgroundColor(Color.BLUE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (u.getUsuario() == ClaseConstante.getUsuario().getId()) {
                    luimagebutton.setEnabled(true);
                    editar.setVisibility(View.VISIBLE);
                } else {
                    luimagebutton.setEnabled(true);
                    editar.setVisibility(View.GONE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            meg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        MeGusta meGusta = new MeGusta();
                        MeGustaDao meGustaDao = new MeGustaDao(context);
                        meGusta = (MeGusta) meGustaDao.Buscarid(u.getId(), u.getUsuario());
                        if (meGusta == null) {
                            meGusta = new MeGusta();

                            meGusta.setAnuncio(u.getId());
                            meGusta.setUsuario(u.getUsuario());
                            meGusta.setGusta(1);
                            meGustaDao.Crear(meGusta);
                            meg.setBackgroundColor(Color.BLUE);
                        } else {
                            meGusta.setAnuncio(u.getId());
                            meGusta.setUsuario(u.getUsuario());
                            meGusta.setGusta(0);
                            meGustaDao.Eliminar(meGusta);
                            meg.setBackgroundColor(Color.GRAY);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        RegistroAnuncio fragment1 = new RegistroAnuncio();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameloy, fragment1);
                        Bundle args = new Bundle();

                        args.putSerializable(Anuncio.nomtableanuncio, u);
                        args.putString("nuevo", "anunciolistadapter");
                        fragment1.setArguments(args);
                        fragmentTransaction.commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }


}
