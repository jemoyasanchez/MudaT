package com.itla.mudat.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.itla.mudat.Dao.AnuncioDao;
import com.itla.mudat.Entity.Anuncio;
import com.itla.mudat.ListAdapter.AnuncioListAdapter;
import com.itla.mudat.ListAdapter.UsuarioListAdapter;
import com.itla.mudat.R;

import java.util.ArrayList;
import java.util.List;

public class Publicaciones extends Fragment  {
    private static ListView listViewpublicacion;
    private static Button Bregistroanuncio;

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_publicaciones, container, false);
        try{

            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            listViewpublicacion = (ListView) v.findViewById(R.id.LWlistaAnuncios);
            Bregistroanuncio = (Button) v.findViewById(R.id.Bregistroanuncio);
            cargaranuncios(getActivity());
            Bregistroanuncio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vw) {
                    try {
                        try {
                            RegistroAnuncio fragment1 = new RegistroAnuncio();
                            android.support.v4.app.FragmentTransaction fragmentTransaction =getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frameloy, fragment1);
                            fragmentTransaction.commit();
                        }catch (Exception e){e.printStackTrace();}

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


  



    public static  void cargaranuncios(Activity activity)
    {
        try
        {
            AnuncioDao anuncioDao = new AnuncioDao( activity);

            List<Anuncio> anunciosl = new ArrayList<>();
            anunciosl = (List<Anuncio>) anuncioDao.Listar();
            if (anunciosl != null) {
                listViewpublicacion.setAdapter(new AnuncioListAdapter(anunciosl, activity));
            }else {
                listViewpublicacion.setAdapter(null);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
