package com.frank.sga.ui.crusos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frank.sga.MenuPrincipal;
import com.frank.sga.R;
import com.frank.sga.Utilidades.DirecionServicioRest;
import com.frank.sga.Utilidades.MemoriaLocal;
import com.frank.sga.Utilidades.MetodosUtilitarios;
import com.frank.sga.data.model.NotasAlumno;
import com.frank.sga.data.model.usuario;
import com.frank.sga.ui.mantUser.manteniminetoUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class DetalleCurso extends AppCompatActivity {
    Toolbar toolbarMenu;
    Button btnVerDatosProfesor;
    RequestQueue mqueue;
    TextView tvNota1;
    TextView tvNota2;
    TextView tvNota3;
    TextView tvEstado;
    TextView tvPromedio;
    NotasAlumno notasAlumno = new NotasAlumno();
    usuario profesor ;
    SqlDateTypeAdapter sqlAdapter = new SqlDateTypeAdapter();
    Gson gson =  new GsonBuilder()
            .registerTypeAdapter(java.util.Date.class, sqlAdapter )
            .setDateFormat("yyyy-MM-dd")
            .create();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_curso);
        toolbarMenu = findViewById(R.id.toolbar);
        tvNota1 = findViewById(R.id.tvNota1);
        tvNota2 = findViewById(R.id.tvNota2);
        tvNota3 = findViewById(R.id.tvNota3);
        tvPromedio = findViewById(R.id.tvPromedio);
        tvEstado = findViewById(R.id.tvEstadoPromedio);
        btnVerDatosProfesor = findViewById(R.id.btnVerDatosProfesor);
        toolbarMenu.setTitle("Notas Curso ");
        setSupportActionBar(toolbarMenu);
        Long idCursoCarrera = getIntent().getLongExtra("idCursoCarrera",0);
        Integer idAlumno = Integer.parseInt( MemoriaLocal.getDefaults("idUser",MemoriaLocal.CONTEXTOLOGIN));
        mqueue = new Volley().newRequestQueue(getApplicationContext());
        servicioNotas(idAlumno,idCursoCarrera);
        btnVerDatosProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DatosProfesor.class);
                intent.putExtra("profesor",profesor);
                startActivity(intent);
            }
        });
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

    private void servicioNotas(int idAlumno , Long idCursoCarrera ){
        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_NOTAS_POR_ALUMNO(idAlumno,idCursoCarrera);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    notasAlumno.setNota1(evaluaNulo("nota1",response));
                    notasAlumno.setNota2(evaluaNulo("nota2",response));
                    notasAlumno.setNota3(evaluaNulo("nota3",response));
                    if(response.isNull("estadoaprobado")){
                            notasAlumno.setEstadoaprobado(null);
                    }else{
                            notasAlumno.setEstadoaprobado(response.getString("estadoaprobado"));
                    }

                    tvNota1.setText("NOTA 1 :"+evaluaDoubleNuloToString(notasAlumno.getNota1()));
                    tvNota2.setText("NOTA 2 :"+evaluaDoubleNuloToString(notasAlumno.getNota2()));
                    tvNota3.setText("NOTA 3 :"+evaluaDoubleNuloToString(notasAlumno.getNota3()));
                    tvPromedio.setText("PROMEDIO : "+notasAlumno.CalculaPromedio());


                    if(notasAlumno.getEstadoaprobado() != null){
                        tvEstado.setText(notasAlumno.getEstadoaprobado());
                    }else {
                        tvEstado.setText("ESTADO : --");
                    }


                    profesor = gson.fromJson(String.valueOf(response.getJSONObject("curso").getJSONObject("profesor")),usuario.class);

                    System.out.println("Nombre " + profesor.getNombre());

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error inesperado",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                startActivity(new Intent(getApplicationContext(),ListaCursos.class));
                Toast.makeText(getApplicationContext(),"Problemas al cargar",Toast.LENGTH_LONG).show();
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


        mqueue.add(jsonObjectRequest);



    }

    private Double evaluaNulo(String nombrePropiedad,JSONObject JSON) throws JSONException {
        if(JSON.isNull(nombrePropiedad)){
            return null;
        }else{
            return  JSON.getDouble(nombrePropiedad);
        }
    }

    private  String evaluaDoubleNuloToString(Double numero){
        if (numero != null){
            return String.valueOf(numero);
        }else {
            return "--";
        }
    }

}
