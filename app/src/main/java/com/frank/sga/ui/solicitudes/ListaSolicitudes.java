package com.frank.sga.ui.solicitudes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.frank.sga.R;

public class ListaSolicitudes extends AppCompatActivity {
    Toolbar toolbarMenu;
    ImageButton imageButtonAddSolicitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_solicitudes);
        toolbarMenu = findViewById(R.id.toolbar);
        toolbarMenu.setTitle(R.string.ListaMisSolicitud);
        setSupportActionBar(toolbarMenu);
        imageButtonAddSolicitud = findViewById(R.id.BtnaddSolicitud);
        imageButtonAddSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AgregaSolicirud.class));
            }
        });
    }

    public boolean onCreateOptionsMenu( Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.CerrarCession:

                break;
            case R.id.IrMenuPrincipal:
                break;
            case R.id.IrActualizaDatos:
                break;

        }
        return true;
    }
}
