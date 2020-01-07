package com.frank.sga.ui.CalificacionProfesor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frank.sga.MenuPrincipal;
import com.frank.sga.R;
import com.frank.sga.Utilidades.DirecionServicioRest;
import com.frank.sga.Utilidades.MemoriaLocal;
import com.frank.sga.Utilidades.MetodosUtilitarios;
import com.frank.sga.data.model.CalificacionAlumnoProfesor;
import com.frank.sga.data.model.usuario;
import com.frank.sga.ui.mantUser.manteniminetoUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CalificaProfesor extends AppCompatActivity {
    Toolbar toolbarMenu;
    Button btnGuardar;
    Button btbCancelar;
    ProgressBar progressBar;
    RadioGroup radioGroupExpresaClaramente;
    RadioGroup radioGroupDominaTema;
    RadioGroup radioGroupAclaraDudas;
    RadioButton radioBtnAclaraDudas;
    RadioButton radioBtnDominaTemas;
    RadioButton radioBtnExpresaClaro;
    EditText EDComentarios;
    CalificacionAlumnoProfesor calificacionAlumnoProfesor = new CalificacionAlumnoProfesor();
    usuario profesor = new usuario();
    usuario alumno  = new usuario();
    Gson gson = new Gson();
    RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_califica_profesor);
        toolbarMenu = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.ProgressCalificaProfesor);
        progressBar.setVisibility(View.VISIBLE);
        EDComentarios = findViewById(R.id.EditTextComentarios);
        btnGuardar = findViewById(R.id.guradarCalificacionProfesor);
        btbCancelar = findViewById(R.id.CancelaCalificacionProfe);
        radioGroupAclaraDudas = findViewById(R.id.rgAclaraDudas);
        radioGroupDominaTema = findViewById(R.id.rGDominaTema);
        radioGroupExpresaClaramente = findViewById(R.id.rgExpresaClaramente);
        setSupportActionBar(toolbarMenu);
        mQueue = new Volley().newRequestQueue(getApplicationContext());
        profesor.setId(getIntent().getIntExtra("IdProfesor",0));
        alumno.setId(Integer
                .parseInt(MemoriaLocal.getDefaults("idUser",MemoriaLocal.CONTEXTOLOGIN))
        );
        BuscaCalificacion(profesor.getId(),alumno.getId());
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioclaraDudas  = radioGroupAclaraDudas.getCheckedRadioButtonId();
                int radioDominaTema = radioGroupDominaTema.getCheckedRadioButtonId();
                int radioExpresaClaramente = radioGroupExpresaClaramente.getCheckedRadioButtonId();
                if(radioclaraDudas != -1 && radioDominaTema != -1 && radioExpresaClaramente != -1){
                    radioBtnAclaraDudas = findViewById(radioclaraDudas);
                    radioBtnDominaTemas = findViewById(radioDominaTema);
                    radioBtnExpresaClaro = findViewById(radioExpresaClaramente);

                    calificacionAlumnoProfesor.setAclaradudas( Integer.parseInt(radioBtnAclaraDudas.getText().toString()));
                    calificacionAlumnoProfesor.setDominatema(Integer.parseInt(radioBtnDominaTemas.getText().toString()));
                    calificacionAlumnoProfesor.setExpresaclaramente(Integer.parseInt(radioBtnExpresaClaro.getText().toString()));

                    calificacionAlumnoProfesor.setAlumnocalifica(alumno);
                    calificacionAlumnoProfesor.setProfesorcalificado(profesor);
                    calificacionAlumnoProfesor.setComentario(EDComentarios.getText().toString());
                    String jsonObject = gson.toJson(calificacionAlumnoProfesor,CalificacionAlumnoProfesor.class);
                    RegistraCalificacion(jsonObject);
                }else{
                    Toast.makeText(getApplicationContext(),"Debe ingresar calificaiones ",Toast.LENGTH_LONG).show();
                }


            }
        });
        btbCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MenuPrincipal.class));
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

    private void RegistraCalificacion(String jsonCalificacion ){
        JSONObject jsonObject = null;
        try {
             jsonObject = new JSONObject(jsonCalificacion);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_CALIFICACION_PROFESORES;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(),"Calificacion registrada",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

            }
        })
        {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", MemoriaLocal.getDefaults("token",MemoriaLocal.CONTEXTOLOGIN));
                return headers;
            }

        };
        mQueue.add(jsonObjectRequest);
    }
    private void BuscaCalificacion(long idProfesor , long idAlumno){

        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_CALIFICACION_PROFESORES+"/"+idProfesor+DirecionServicioRest.PATH_BUSCARCALIFICACION_POR_PROFESOR_ALUMNO+"/"+idAlumno;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                Toast.makeText(getApplicationContext(),"Usted ya califico a este profesor",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if(error instanceof TimeoutError || error instanceof NoConnectionError ){
                    Toast.makeText(getApplicationContext(),"Error de conexion",Toast.LENGTH_LONG).show();
                }else if( response.statusCode == 404 ){
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        })
        {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", MemoriaLocal.getDefaults("token",MemoriaLocal.CONTEXTOLOGIN));
                return headers;
            }
        };
        mQueue.add(jsonObjectRequest);
    }
}
