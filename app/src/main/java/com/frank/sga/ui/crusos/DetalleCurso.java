package com.frank.sga.ui.crusos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.frank.sga.R;

public class DetalleCurso extends AppCompatActivity {
    Toolbar toolbarMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_curso);
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

                break;
            case R.id.IrMenu:
                break;
        }
        return true;
    }

}
