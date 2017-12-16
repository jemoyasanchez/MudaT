package com.itla.mudat.View;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.ListUpdateCallback;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.itla.mudat.ClasesConsta.ClaseConstante;
import com.itla.mudat.Dao.UsuarioDao;
import com.itla.mudat.Entity.Usuario;

import com.itla.mudat.ListAdapter.UsuarioListAdapter;
import com.itla.mudat.MenuPrincipal;
import com.itla.mudat.PerfilDeUsuario;
import com.itla.mudat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListaUsuario extends Fragment   {
private ListView listView;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_lista_usuario,container,false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        try {
                        listView = (ListView) v.findViewById(R.id.LWlistausuarios);



            try {
                UsuarioDao usuarioDao = new UsuarioDao(getActivity());

                List<Usuario> usuariosl = new ArrayList<>();
                usuariosl = (List<Usuario>) usuarioDao.Listar();
                if (usuariosl != null) {
                    listView.setAdapter(new UsuarioListAdapter(usuariosl, getActivity()));
                } else {
                    listView.setAdapter(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Usuario u = (Usuario) adapterView.getItemAtPosition(position);
                    PerfilDeUsuario fragment1 = new PerfilDeUsuario();
                    android.support.v4.app.FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameloy, fragment1);
                    Bundle args = new Bundle();
                    args.putSerializable(Usuario.nomtableUsuario, u);
                    args.putString("nuevo", "listausuario");
                    fragment1.setArguments(args);
                    fragmentTransaction.commit();
                }
            });
        }catch (Exception e){e.printStackTrace();}
        return v;

    }




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//    });
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_lista_usuario);
//
//        Bresgtrousuario=(Button) findViewById(R.id.BregistrodeUsu);
//        listView=(ListView) findViewById(R.id.LWlistausuarios);
//        UsuarioDao usuarioDao=new UsuarioDao(this);
//        List<Usuario> usuariosl =new ArrayList<>();
//        usuariosl=(List<Usuario>) usuarioDao.Listar();
//        if (usuariosl!=null)
//        {
//            listView.setAdapter(new UsuarioListAdapter(usuariosl, this));
//        }
//        else
//        {
//            listView.setAdapter(null);
//        }
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Intent rUsuario=new Intent(ListaUsuario.this,RegistroUsuario.class);
//                Usuario u=(Usuario) adapterView.getItemAtPosition(position);
//                rUsuario.putExtra(Usuario.nomtableUsuario,u);
//                startActivity(rUsuario);
//            }
//        });
//        Bresgtrousuario.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent view =new Intent(ListaUsuario.this,RegistroUsuario.class);
//
//                startActivity(view);
//            }
//        });
//    }
}
