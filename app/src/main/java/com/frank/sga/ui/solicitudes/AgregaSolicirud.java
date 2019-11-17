package com.frank.sga.ui.solicitudes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frank.sga.R;
import com.frank.sga.Utilidades.DirecionServicioRest;
import com.frank.sga.Utilidades.MemoriaLocal;
import com.frank.sga.data.model.TiposSolicitud;
import com.frank.sga.data.model.Usuario_Solicitud;
import com.frank.sga.data.model.usuario;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregaSolicirud extends AppCompatActivity {
    Toolbar toolbarMenu;
    Spinner SpinnerTiposSolicitudes;
    ProgressBar progressBar;
    RequestQueue mQueue;
    List<TiposSolicitud> listaTiposSolicitud = new ArrayList<>();
    EditText ETComentariosSolicitud;
    Button btnEnviaSolicitud , btnCancelaSolicicitud;
    Usuario_Solicitud solicitud = new Usuario_Solicitud();
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrega_solicirud);
        progressBar = findViewById(R.id.progresBarAddSolicitud);
        progressBar.setVisibility(View.VISIBLE);
        mQueue = new Volley().newRequestQueue(getApplicationContext());
        toolbarMenu = findViewById(R.id.toolbar);
        ETComentariosSolicitud = findViewById(R.id.ETComentariosSolicitud);
        btnEnviaSolicitud = findViewById(R.id.btnEnviaSolictud);
        btnCancelaSolicicitud = findViewById(R.id.BtnCancelaSolicitud);
        setSupportActionBar(toolbarMenu);
        SpinnerTiposSolicitudes = findViewById(R.id.SpinnerTipoSolicitud);
        llenaSpinerTipoSolicitudes();

        SpinnerTiposSolicitudes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                solicitud.setTiposolicitud(new TiposSolicitud(listaTiposSolicitud.get(position).getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnEnviaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( (solicitud.getTiposolicitud() !=  null && ETComentariosSolicitud.getText().toString() != null) || !ETComentariosSolicitud.getText().toString().equals(' ')|| !ETComentariosSolicitud.getText().toString().isEmpty()){
                    solicitud.setUsuariosolicitud(new usuario(

                            Integer.parseInt(MemoriaLocal.getDefaults("idUser",MemoriaLocal.CONTEXTOLOGIN))
                    ));
                    solicitud.setComentarios(ETComentariosSolicitud.getText().toString());
                    EnviaSolicitud();
                }else{
                    Toast.makeText(getApplicationContext(),"Falta llenar algunos campos",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
//llenando el spinner tipos de solicitud
    private void llenaSpinerTipoSolicitudes(){
        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_TIPOS_SOLICITUD;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<String> lstaNombreTipoSolicitud = new ArrayList<>();

                try {
                    for (int  i  = 0  ; i< response.length(); i ++){

                        JSONObject jsonTipoSolicitud = response.getJSONObject(i);
                        TiposSolicitud tiposSolicitud = new TiposSolicitud(
                                jsonTipoSolicitud.getInt("id"),
                                jsonTipoSolicitud.getString("nombresolicitud"),
                                jsonTipoSolicitud.getString("descripcion")
                                );

                        listaTiposSolicitud.add(tiposSolicitud);
                        lstaNombreTipoSolicitud.add(tiposSolicitud.getNombresolicitud());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                SpinnerTiposSolicitudes.setAdapter(new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.spiner_items,
                        lstaNombreTipoSolicitud
                ));
                progressBar.setVisibility(View.INVISIBLE);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"No se pudo cargar los tipos de solicitud",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }

        )

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
        mQueue.add(jsonArrayRequest);

    }

//enviarSolicitud
    private void EnviaSolicitud(){

        progressBar.setVisibility(View.VISIBLE);
        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_SOLICITUDALUMNO;

        JSONObject jsonSolicitud = null;
        try {
            jsonSolicitud = new JSONObject(gson.toJson(solicitud, Usuario_Solicitud.class));
            System.out.println(jsonSolicitud);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),"Problemas al castear el json de Solicitud",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                jsonSolicitud,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),"Solicitud Registrada",Toast.LENGTH_LONG).show();

                        progressBar.setVisibility(View.INVISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),"Error al registrar",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
        )
        {
            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", MemoriaLocal.getDefaults("token",MemoriaLocal.CONTEXTOLOGIN));
                return headers;
            }

        }
                ;
        mQueue.add(jsonObjectRequest);
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
