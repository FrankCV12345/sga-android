package com.frank.sga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
//import androidx.appcompat.widget.Toolbar;
//import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import  com.frank.sga.Utilidades.MemoriaLocal;
import com.frank.sga.Utilidades.MetodosUtilitarios;
import com.frank.sga.ui.CalificacionProfesor.ListaProfesores;
import com.frank.sga.ui.crusos.ListaCursos;
import com.frank.sga.ui.solicitudes.ListaSolicitudes;
import com.frank.sga.ui.mantUser.manteniminetoUser;

public class MenuPrincipal extends AppCompatActivity {
    Toolbar toolbarMenu;
    ImageView imageViewCalificaProfe;
    ImageView imageViewSolicitudes;
    ImageView imageViewCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        imageViewCalificaProfe = findViewById(R.id.iconCalificaProfesor);
        imageViewCursos = findViewById(R.id.iconCursos);
        imageViewSolicitudes = findViewById(R.id.iconSolicitudes);
        imageViewCalificaProfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListaProfesores.class));
            }
        });
        imageViewSolicitudes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListaSolicitudes.class));
            }
        });
        imageViewCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ListaCursos.class));
            }
        });
        toolbarMenu = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarMenu);



    }


    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.CerrarCession:
                MetodosUtilitarios.CerrarSescion();
                finish();
                break;
            case R.id.IrMenuPrincipal:
                startActivity(new Intent(getApplicationContext(),MenuPrincipal.class));
                break;
            case R.id.IrActualizaDatos:
                startActivity(new Intent(getApplicationContext(),manteniminetoUser.class));
                break;

        }
        return true;
    }
}
