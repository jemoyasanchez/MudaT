package com.itla.mudat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.itla.mudat.ClasesConsta.ClaseConstante;
import com.itla.mudat.Dao.ConstanteDao;
import com.itla.mudat.Entity.Constante;
import com.itla.mudat.Entity.Usuario;
import com.itla.mudat.View.ListaUsuario;
import com.itla.mudat.View.Publicaciones;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        cargadatos();
        try {
            Publicaciones fragment1 = new Publicaciones();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameloy, fragment1);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("PUBLICACIONES");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mispublicaciones) {
            try {

                PerfilDeUsuario fragment1 = new PerfilDeUsuario();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameloy, fragment1);
                Bundle args = new Bundle();
                args.putSerializable(Usuario.nomtableUsuario, ClaseConstante.getUsuario());
                args.putString("nuevo", "menuprincipal");
                fragment1.setArguments(args);
                fragmentTransaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.nav_publicaciones) {
            try {
                Publicaciones fragment1 = new Publicaciones();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameloy, fragment1);
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("PUBLICACIONES");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.nav_listausuario) {
            try {
                ListaUsuario fragment1 = new ListaUsuario();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameloy, fragment1);
                fragmentTransaction.commit();
                getSupportActionBar().setTitle("LISTA DE USUARIOS");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.nav_salir) {
            try {
                Constante constante = new Constante();
                ConstanteDao constanteDao = new ConstanteDao(getApplicationContext());
                constante.setIp(1);
                constante.setId(0);
                if (constanteDao.Crear(constante)) {
                    Intent ventana12 = new Intent(MenuPrincipal.this, SplashActivityInic.class);
                    ventana12.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(ventana12);

                } else {
                    Toast.makeText(MenuPrincipal.this, "No se pudo cerrar sesión", Toast.LENGTH_LONG).show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Publicaciones fragment1 = new Publicaciones();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameloy, fragment1);
                fragmentTransaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cargadatos() {
        try {
            usuario = new Usuario();
            usuario = ClaseConstante.getUsuario();
            if (usuario != null) {
                try {
                    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                    if (navigationView != null) {
                        navigationView.setNavigationItemSelectedListener(this);
                    }
                    View navHeaderView = navigationView.getHeaderView(0);

                    TextView headerUserName = (TextView) navHeaderView.findViewById(R.id.username);
                    TextView headerMobileNo = (TextView) navHeaderView.findViewById(R.id.email);


                    headerMobileNo.setText(usuario.getNombre());
                    headerUserName.setText(usuario.getEmail());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
