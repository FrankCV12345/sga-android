package com.frank.sga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class PerfilProfesor extends AppCompatActivity {
    LinearLayout linealPreg1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_profesor);

        linealPreg1 = findViewById(R.id.porcentagePreg1);
        linealPreg1.getLayoutParams().width=10;
    }
}
