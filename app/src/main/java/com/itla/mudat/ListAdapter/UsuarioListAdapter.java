package com.itla.mudat.ListAdapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itla.mudat.Entity.Usuario;
import com.itla.mudat.R;

import java.util.List;

public class UsuarioListAdapter extends BaseAdapter {

    private List<Usuario> usuarios;
    private Activity context;

    public UsuarioListAdapter(List<Usuario> usuarios, Activity context) {
        this.usuarios = usuarios;
        this.context = context;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarios.get(position);
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
            view=inflater.inflate(R.layout.listview_usuario_row,null);
        }
        try {
            TextView luCodigo = view.findViewById(R.id.LUcodigocontacto);
            TextView luNombre = view.findViewById(R.id.LUnombrecontacto);
            TextView luEmial = view.findViewById(R.id.LUemailcontacto);
            TextView luTelefono = view.findViewById(R.id.LUtelefonocliente);

            Usuario u = usuarios.get(position);
            luNombre.setText(u.getNombre().toString().toUpperCase());
            luEmial.setText(u.getEmail().toString().toUpperCase());
            luCodigo.setText(String.valueOf(u.getId()));
            luTelefono.setText(u.getTelefono());
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
