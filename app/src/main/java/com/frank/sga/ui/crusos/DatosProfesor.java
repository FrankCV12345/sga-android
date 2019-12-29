package com.frank.sga.ui.crusos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.frank.sga.MenuPrincipal;
import com.frank.sga.R;
import com.frank.sga.Utilidades.MetodosUtilitarios;
import com.frank.sga.data.model.usuario;
import com.frank.sga.ui.mantUser.manteniminetoUser;

public class DatosProfesor extends AppCompatActivity {
    Toolbar toolbar;
    usuario profesor ;
    TextView tvNombreProfesor;
    TextView tvApellidosProfesor;
    TextView tvCorreo;
    TextView tvTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_profesor);
        toolbar = findViewById(R.id.toolbar);
        tvNombreProfesor = findViewById(R.id.nombreProfe);
        tvApellidosProfesor = findViewById(R.id.ApellidoProfe);
        tvCorreo = findViewById(R.id.CorreoProfe);
        tvTelefono = findViewById(R.id.TelefonoProfe);
        setSupportActionBar(toolbar);
        profesor = (usuario) getIntent().getExtras().getSerializable("profesor");

        tvNombreProfesor.setText( "NOMBRE  : "+profesor.getNombre());
        tvApellidosProfesor.setText("APELLIDOS : "+profesor.getApellidos());
        tvCorreo.setText("CORREO : "+ profesor.getCorreo());
        tvTelefono.setText("TELEFONO : "+ profesor.getTelefono());
    }

    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.CerrarCession:
                MetodosUtilitarios.CerrarSescion();
                finishAffinity();
                break;
            case R.id.IrMenuPrincipal:
                startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
                break;
            case R.id.IrActualizaDatos:
                startActivity(new Intent(getApplicationContext(), manteniminetoUser.class));
                break;

        }
        return true;
    }
}
