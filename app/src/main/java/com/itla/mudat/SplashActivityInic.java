package com.itla.mudat;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


import com.itla.mudat.ClasesConsta.ClaseConstante;
import com.itla.mudat.Dao.ConstanteDao;
import com.itla.mudat.Dao.DBConnection;
import com.itla.mudat.Dao.UsuarioDao;
import com.itla.mudat.Entity.Constante;
import com.itla.mudat.Entity.Usuario;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivityInic extends AppCompatActivity {

    private static final boolean AUTO_HIDE = false;

    private static final int AUTO_HIDE_DELAY_MILLIS = 500;

    private static final int UI_ANIMATION_DELAY = 1;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    public static int count = 0;

    private Constante constante;
    private ConstanteDao constanteDao;

    Timer _t;
    FrameLayout loay;
    int[] drawablearray = new int[]{R.drawable.nyvitybackg, R.drawable.nybackhd, R.drawable.antiago};

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {

            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };


    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }

        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_inic);

        mVisible = true;

        mContentView = findViewById(R.id.fullscreen_content);

        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        constante = new Constante();
        constanteDao = new ConstanteDao(getApplicationContext());
        constante = (Constante) constanteDao.Buscar(1);
        if (constante != null && constante.getId() != null) {
            if (constante.getId() > 0) {
                ClaseConstante.SetConfiguracionConstate(constante, SplashActivityInic.this);
                if (ClaseConstante.getUsuario() != null && ClaseConstante.getConstante() != null) {
                    Intent ventana1 = new Intent(SplashActivityInic.this, MenuPrincipal.class);
                    startActivity(ventana1);
                }
            }
        } else {
            constante = new Constante();
            constanteDao = new ConstanteDao(getApplicationContext());
        }

        try {
            loay = findViewById(R.id.idframnglayoutimg);
            _t = new Timer();
            _t.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    runOnUiThread(new Runnable() // run on ui thread
                    {
                        public void run() {
                            if (count < drawablearray.length) {

                                loay.setBackgroundResource(drawablearray[count]);
                                count = (count + 1) % drawablearray.length;
                            }
                        }
                    });
                }
            }, 1000, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            ContenidoIniciar fragment1 = new ContenidoIniciar();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.idframnglayoutimg, fragment1);
            fragmentTransaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide(50);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mVisible = false;

        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }


    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
