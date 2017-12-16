package com.itla.mudat.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.itla.mudat.ClasesConsta.ClaseConstante;
import com.itla.mudat.Dao.AnuncioDao;
import com.itla.mudat.Dao.CategoriaDao;
import com.itla.mudat.Entity.Anuncio;
import com.itla.mudat.Entity.Categoria;
import com.itla.mudat.PerfilDeUsuario;
import com.itla.mudat.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.itla.mudat.Dao.DBConnection.LOG_T;

public class RegistroAnuncio extends android.support.v4.app.Fragment {
    private EditText txttitulo;
    private EditText txtdetalle;
    private EditText txtubicacion;
    private EditText txtprecio;
    private EditText txtcondicion;
    private Button bregistroanuncio;
    private Anuncio anuncio;
    private AnuncioDao anuncioDao;
    private Spinner spinnerCategoria;
    private Categoria categoria;
    private CategoriaDao categoriaDao;
    private Map elegido;
    private String iniclognuevo;
    List<Map<String, String>> lista;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_anuncio, container, false);
        try {
            init();
            txttitulo = (EditText) v.findViewById(R.id.Txtnombretitulo);
            txtdetalle = (EditText) v.findViewById(R.id.Txtidetalle);
            txtubicacion = (EditText) v.findViewById(R.id.Txtubicacion);
            txtprecio = (EditText) v.findViewById(R.id.Txtprecios);
            txtcondicion = (EditText) v.findViewById(R.id.Txtcondicion);
            bregistroanuncio = (Button) v.findViewById(R.id.Bregistroanuncio);
            spinnerCategoria = (Spinner) v.findViewById(R.id.Spcategoriaanuncio);

            cargarunidad();

            bregistroanuncio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vw) {
                    try {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Aviso");
                        builder.setMessage("Desea Registrar el Registro Anuncio?");
                        builder.setIcon(R.drawable.ic_guardar);

                        builder.setPositiveButton("GUARDAR", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (validar()) {
                                    anuncioDao = new AnuncioDao(getActivity());
                                    anuncio.setCategoria((Integer) elegido.get(Categoria.nomid));
                                    anuncio.setUsuario(ClaseConstante.getUsuario().getId());
                                    anuncio.setTitulo(txttitulo.getText().toString().toUpperCase());
                                    anuncio.setDetalle(txtdetalle.getText().toString().trim());
                                    anuncio.setUbicacion(txtubicacion.getText().toString());
                                    anuncio.setCondicion(txtcondicion.getText().toString());
                                    anuncio.setPrecio(Double.valueOf(txtprecio.getText().toString()));
                                    Date dateobj = new Date();
                                    anuncio.setFecha(dateobj);

                                    Log.i(LOG_T, "Registrando Anuncio " + anuncio.toString());

                                    if (anuncioDao.Crear(anuncio)) {
                                        toast("Anuncio Registrado Exitosamente.");
                                        Nuevo();
                                        try {
                                            if(iniclognuevo.equals("perfilusuario"))
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
                                            else if (iniclognuevo.equals("publicaciones")){
                                                Publicaciones fragment1 = new Publicaciones();
                                                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                fragmentTransaction.replace(R.id.frameloy, fragment1);
                                                fragmentTransaction.commit();
                                            }
                                            else if (iniclognuevo.equals("anunciolistadapter"))
                                            {
                                                Publicaciones fragment1 = new Publicaciones();
                                                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                                fragmentTransaction.replace(R.id.frameloy, fragment1);
                                                fragmentTransaction.commit();
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    } else {
                                        toast("Anuncio no se  Registrado Exitosamente.");
                                    }
                                } else {
                                    toast("Debes Completar los datos faltantes.");
                                }

                            }
                        });

                        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    if(iniclognuevo.equals("perfilusuario"))
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
                                    else if (iniclognuevo.equals("publicaciones")){
                                        Publicaciones fragment1 = new Publicaciones();
                                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction.replace(R.id.frameloy, fragment1);
                                        fragmentTransaction.commit();
                                    }
                                    else if (iniclognuevo.equals("anunciolistadapter"))
                                    {
                                        Publicaciones fragment1 = new Publicaciones();
                                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                        fragmentTransaction.replace(R.id.frameloy, fragment1);
                                        fragmentTransaction.commit();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return;
                            }
                        });

                        builder.show();

                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        elegido = (Map) parent.getItemAtPosition(position);
                        if (elegido != null) {

                        } else {
                            elegido = null;
                        }
                    } catch (Exception e) {

                        toast(e.getMessage());
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle parametros = getArguments();
        if (parametros != null && parametros.getSerializable(Anuncio.nomtableanuncio) != null) {
            anuncio = (Anuncio) parametros.getSerializable(Anuncio.nomtableanuncio);

            if (anuncio != null) {
                cargarunidad();
if(elegido!=null) {
    selectSpinnerValueEdit(lista, (String) elegido.get(Categoria.nomid));
}
                txttitulo.setText(anuncio.getTitulo());
                txtdetalle.setText(anuncio.getDetalle());
                txtcondicion.setText(anuncio.getCondicion());
                txtubicacion.setText(anuncio.getUbicacion());
                txtprecio.setText(String.valueOf( anuncio.getPrecio()));
            }
        }

        if (parametros != null && !TextUtils.isEmpty(parametros.getString("nuevo"))) {
            iniclognuevo = parametros.getString("nuevo");
        } else {
            iniclognuevo = "nuevo";
        }
    }

    private void Nuevo() {
        anuncio = new Anuncio();
        txttitulo.setText("");
        txtdetalle.setText("");
        txtubicacion.setText("");
        txtcondicion.setText("");
        txtprecio.setText("");

    }

    private void selectSpinnerValueEdit(List<Map<String, String>> spinner, String id) {
        try {
            int index;
            String uni;
            for (index = 0; index < spinner.size(); index++) {
                Map<String, String> myMap = spinner.get(index);

                uni = "";

                uni = myMap.get(Categoria.nomid).toString().trim().toLowerCase();


                if (uni.equals(id.trim().toLowerCase())) {
                    spinnerCategoria.setSelection(index);
                    break;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            toast(e.getMessage());

        }
    }

    private void cargarunidad() {
        try {

            lista = new ArrayList<Map<String, String>>();
            List<Categoria> categoriaslista = new ArrayList<>();
            categoriaslista = categoriaDao.Listar();
            if (categoriaslista.size() > 0) {

                for (Categoria c : categoriaslista) {
                    Map temp = new HashMap();
                    temp.put(Categoria.nomid, c.getId());
                    temp.put(Categoria.nomnombre, c.getNombre());

                    lista.add(temp);
                }
                SimpleAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext()
                        , lista, R.layout.items_categoria_anuncio, new String[]{Categoria.nomid, Categoria.nomnombre}, new int[]{R.id.idcategoriaspinner, R.id.idnombrecategoriapinner});
                spinnerCategoria.setAdapter(adapter);
            }

        } catch (Exception e) {

            toast(e.getMessage());

        }
    }

    private Boolean validar() {
        Boolean Valvali = true;

        if (TextUtils.isEmpty(txttitulo.getText())) {
            txttitulo.setError("Ej: Casa de verano!");
            toast("Debe Registrar el titulo del registroAnuncio.");
            Valvali = false;
        } else {
            txttitulo.setError(null);
        }

        if (elegido == null) {

            toast("Debe seleccionar una Categoria.");
            Valvali = false;
        } else {

        }
        if (TextUtils.isEmpty(txtdetalle.getText())) {
            txtdetalle.setError("Ej.");
            toast("Debe Registrar detalle del registroAnuncio.");
            Valvali = false;
        } else {
            txtdetalle.setError(null);
        }

        if (TextUtils.isEmpty(txtubicacion.getText())) {
            txtubicacion.setError("Ej: Santiago");
            toast("Debe Registrar la ubicacion del registroAnuncio.");
            Valvali = false;
        } else {
            txtubicacion.setError(null);
        }

        if (TextUtils.isEmpty(txtcondicion.getText())) {
            txtcondicion.setError("Ej: Nuevo");
            toast("Debe Registrar la codicion.");
            Valvali = false;
        } else {
            txtcondicion.setError(null);
        }

        if (TextUtils.isEmpty(txtprecio.getText())) {
            txtprecio.setError("Ej: 1,000.000.00");
            toast("Debe Registrar el precio.");
            Valvali = false;
        } else {
            txtprecio.setError(null);
        }


        return Valvali;
    }

    private void init() {
        try {
            anuncio = new Anuncio();
            anuncioDao = new AnuncioDao(getActivity().getApplicationContext());
            categoria = new Categoria();
            categoriaDao = new CategoriaDao(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
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
