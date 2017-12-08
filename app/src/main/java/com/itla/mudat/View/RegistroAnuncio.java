package com.itla.mudat.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.itla.mudat.Dao.AnuncioDao;
import com.itla.mudat.Entity.Anuncio;
import com.itla.mudat.R;

import static com.itla.mudat.Dao.DBConnection.LOG_T;

public class RegistroAnuncio extends DialogFragment   {
      private EditText txttitulo;
      private EditText txtdetalle;
      private EditText txtubicacion;
      private EditText txtprecio;
      private EditText txtcondicion;
      private Button bregistroanuncio;
      private Anuncio anuncio;
      private AnuncioDao anuncioDao;
    private DialogInterface.OnDismissListener onDismissListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_anuncio,container,false);
        try
        {
            init();
            txttitulo=(EditText) v.findViewById(R.id.Txtnombretitulo);
            txtdetalle=(EditText) v.findViewById(R.id.Txtidetalle);
            txtubicacion=(EditText) v.findViewById(R.id.Txtubicacion);
            txtprecio=(EditText) v.findViewById(R.id.Txtprecios);
            txtcondicion=(EditText) v.findViewById(R.id.Txtcondicion);
            bregistroanuncio=(Button) v.findViewById(R.id.Bregistroanuncio);
            getDialog().setTitle("Registro de Anuncio");

            getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            bregistroanuncio.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View vw)
                {
                    try
                    {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Aviso");
                    builder.setMessage("Desea Registrar el RegistroAnuncio?");
                    builder.setIcon(R.drawable.ic_guardar);

                    builder.setPositiveButton("GUARDAR", new DialogInterface.OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            if (validar()) {
                                anuncioDao = new AnuncioDao(getActivity());
                                anuncio.setCategoria(1);
                                anuncio.setUsuario(1);
                               anuncio.setTitulo(txttitulo.getText().toString().toUpperCase());
                               anuncio.setDetalle(txtdetalle.getText().toString().trim());
                               anuncio.setUbicacion(txtubicacion.getText().toString());
                               anuncio.setCondicion(txtcondicion.getText().toString());
                               anuncio.setPrecio(Double.valueOf( txtprecio.getText().toString()));
                               anuncio.setFecha(DateFormat.format("dd/MM/yyyy hh:mm:ss aa", new java.util.Date()).toString());

                                Log.i(LOG_T, "Registrando Anuncio " + anuncio.toString());

                                if (anuncioDao.Crear(anuncio)) {
                                    toast("Anuncio Registrado Exitosamente.");
                                    Nuevo();
                           Publicaciones.cargaranuncios(getActivity());
                                    getDialog().dismiss();

                                }
                                else {
                                    toast("Anuncio no se  Registrado Exitosamente.");
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

                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                }
            });

        }
         catch (Exception e)
         {
         e.printStackTrace();
         }

         return v;
    }

    private void Nuevo()
    {
        anuncio =new Anuncio();
        txttitulo.setText("");
        txtdetalle.setText("");
        txtubicacion.setText("");
        txtcondicion.setText("");
        txtprecio.setText("");

    }

    private Boolean validar()
    {
        Boolean Valvali=true;

        if(TextUtils.isEmpty( txttitulo.getText()))
        {
            txttitulo.setError("Ej: Casa de verano!");
            toast("Debe Registrar el titulo del registroAnuncio.");
            Valvali=false;
        }
        else
        {
            txttitulo.setError(null);
        }

        if(TextUtils.isEmpty( txtdetalle.getText()))
        {
            txtdetalle.setError("Ej.");
            toast("Debe Registrar detalle del registroAnuncio.");
            Valvali=false;
        }
        else
        {
            txtdetalle.setError(null);
        }

        if(TextUtils.isEmpty( txtubicacion.getText()))
        {
            txtubicacion.setError("Ej: Santiago");
            toast("Debe Registrar la ubicacion del registroAnuncio.");
            Valvali=false;
        }
        else
        {
            txtubicacion.setError(null);
        }

        if(TextUtils.isEmpty( txtcondicion.getText()))
        {
            txtcondicion.setError("Ej: Nuevo");
            toast("Debe Registrar la codicion.");
            Valvali=false;
        }
        else
        {
            txtcondicion.setError(null);
        }

        if(TextUtils.isEmpty( txtprecio.getText()))
        {
            txtprecio.setError("Ej: 1,000.000.00");
            toast("Debe Registrar el precio.");
            Valvali=false;
        }
        else
        {
            txtprecio.setError(null);
        }



        return  Valvali;
    }

    private void  init()
    {
        try
        {
        anuncio =new Anuncio();
        anuncioDao=new AnuncioDao(getActivity().getApplicationContext());
        }
        catch (Exception e)
        {
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

    @Override
    public void onDismiss(final DialogInterface dialog)
    {
        super.onDismiss(dialog);

        if ( getActivity() instanceof DialogInterface.OnDismissListener)
        {
            ((DialogInterface.OnDismissListener) getActivity()  ).onDismiss(dialog);
        }
    }

    @Override
    public void onDestroyView()
    {
        if (getDialog() != null && getRetainInstance())
        {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener)
    {
        this.onDismissListener = onDismissListener;
    }


}
