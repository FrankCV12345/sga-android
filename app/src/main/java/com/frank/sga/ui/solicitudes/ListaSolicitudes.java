package com.frank.sga.ui.solicitudes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.frank.sga.R;
import com.frank.sga.Utilidades.DirecionServicioRest;
import com.frank.sga.Utilidades.MemoriaLocal;
import com.frank.sga.data.model.TiposSolicitud;
import com.frank.sga.data.model.Usuario_Solicitud;
import com.frank.sga.data.model.estadoSolicitud;
import com.frank.sga.data.model.usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaSolicitudes extends AppCompatActivity {
    Toolbar toolbarMenu;
    ImageButton imageButtonAddSolicitud;
    ListView listaSolicitudesView;
    List<Usuario_Solicitud> listaSolicitudesPorAlumno = new ArrayList<>();
    RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_solicitudes);
        toolbarMenu = findViewById(R.id.toolbar);
        toolbarMenu.setTitle(R.string.ListaMisSolicitud);
        setSupportActionBar(toolbarMenu);
        listaSolicitudesView = findViewById(R.id.ListViewSolicitudes);
        imageButtonAddSolicitud = findViewById(R.id.BtnaddSolicitud);
        imageButtonAddSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AgregaSolicirud.class));
            }
        });
        mQueue = new Volley().newRequestQueue(getApplicationContext());
        Long idUsuario = Long.parseLong(MemoriaLocal.getDefaults("idUser",MemoriaLocal.CONTEXTOLOGIN));
        if(idUsuario != null){
            llenaSolictudes(idUsuario);
        }
    }


    private void llenaSolictudes(Long idUSer){
        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_SOLICITUDES_POR_ALUMNO(idUSer);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<String> lstaDescripcionSolicitudes = new ArrayList<>();
                try {
                    for (int  i= 0 ; i <  response.length(); i++){
                        JSONObject jsonSolicitud = response.getJSONObject(i);
                        Usuario_Solicitud solicitud = new Usuario_Solicitud();
                        TiposSolicitud tiposSolicitud = new TiposSolicitud();
                        estadoSolicitud estadoSolicitud = new estadoSolicitud();
                        usuario usuarioResponsableDelaSolicitud = new usuario();
                            tiposSolicitud.setNombresolicitud(
                                    jsonSolicitud.getJSONObject("tiposolicitud").getString("nombresolicitud")
                            );
                            estadoSolicitud.setNombresolicitud(
                                    //nombre de solicitud es el nombre del estado de solicitud fue un error de tipeo al desarrollar el api rest
                                    jsonSolicitud.getJSONObject("estadosolicitud").getString("nombresolicitud")

                            );
                            if (jsonSolicitud.isNull("usuarioresponsable") ){
                                usuarioResponsableDelaSolicitud.setNombre("No asignado");
                            }else{
                                JSONObject jsonUSuarioResponable = jsonSolicitud.getJSONObject("usuarioresponsable");
                                usuarioResponsableDelaSolicitud.setNombre(jsonUSuarioResponable.getString("nombre"));
                            }
                        solicitud.setId(jsonSolicitud.getLong("id"));
                            solicitud.setTiposolicitud(tiposSolicitud);
                            solicitud.setEstadosolicitud(estadoSolicitud);
                            solicitud.setUsuarioresponsable(usuarioResponsableDelaSolicitud);
                        listaSolicitudesPorAlumno.add(solicitud);
                        lstaDescripcionSolicitudes.add(
                                solicitud.getId() +"-"+solicitud.getTiposolicitud().getNombresolicitud()+
                                        "-"+solicitud.getEstadosolicitud().getNombresolicitud()+"->"
                                        +solicitud.getUsuarioresponsable().getNombre()
                                );
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spiner_items,lstaDescripcionSolicitudes);
                    listaSolicitudesView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error al cargar sus Solicitudes",Toast.LENGTH_LONG).show();
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

        mQueue.add(jsonArrayRequest);


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
