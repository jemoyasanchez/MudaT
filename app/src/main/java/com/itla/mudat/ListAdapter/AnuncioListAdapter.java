package com.itla.mudat.ListAdapter;


import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itla.mudat.Entity.Anuncio;
import com.itla.mudat.R;

import java.util.List;

public class AnuncioListAdapter  extends BaseAdapter {

    private List<Anuncio> anuncios;
    private Activity context;

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
        if (view==null)
        {
            LayoutInflater inflater=context.getLayoutInflater();
            view=inflater.inflate(R.layout.listview_anuncio_row,null);
        }
        try {
            TextView luCodigo = view.findViewById(R.id.LUcodigoAnuncio);
            TextView luTitulo = view.findViewById(R.id.LUtituloanuncio);
            TextView luDetalle = view.findViewById(R.id.LUDetalleanuncio);
            TextView luprecio = view.findViewById(R.id.LUprecioanuncio);
TextView luubicacion=view.findViewById(R.id.LUbicacion);
            Anuncio u = anuncios.get(position);
            luCodigo.setText(String.valueOf(u.getId()));
            luTitulo.setText(u.getTitulo().toString().toUpperCase());
            luDetalle.setText(u.getDetalle().toString().toUpperCase());
            luCodigo.setText(String.valueOf(u.getId()));
            luprecio.setText(String.valueOf( u.getPrecio()));
            luubicacion.setText(u.getUbicacion());
        }catch (Exception e)
        {
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
