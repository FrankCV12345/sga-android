package com.frank.sga.ui.CalificacionProfesor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frank.sga.MenuPrincipal;
import com.frank.sga.R;
import com.frank.sga.Utilidades.DirecionServicioRest;
import com.frank.sga.Utilidades.MemoriaLocal;
import com.frank.sga.Utilidades.MetodosUtilitarios;
import com.frank.sga.adapters.AdapterListaProfesores;
import com.frank.sga.data.model.usuario;
import com.frank.sga.ui.mantUser.manteniminetoUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaProfesores extends AppCompatActivity {
    Toolbar toolbarMenu;
    //ListView ListViewProfesores;
    RequestQueue mQueue;
    List<usuario> listaPorfesores = new ArrayList<>();
    //List<String> listaProfesoresnombre = new ArrayList<>();
    RecyclerView recyclerViewProfesores;
    AdapterListaProfesores  adapterProfersore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_profesores);
        //ListViewProfesores = findViewById(R.id.listViewProfesores);
        mQueue = new Volley().newRequestQueue(getApplicationContext());
        recyclerViewProfesores = findViewById(R.id.recyclerviewProfesores);
        recyclerViewProfesores.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapterProfersore = new AdapterListaProfesores(getApplicationContext(),listaPorfesores);
        /*adapterProfersore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/
        recyclerViewProfesores.setAdapter(adapterProfersore);
        Integer idGrupo = Integer.parseInt(MemoriaLocal.getDefaults("idGrupo",MemoriaLocal.CONTEXTOLOGIN));
        llenaLisViewProfesores(idGrupo);
        toolbarMenu = findViewById(R.id.toolbar);
        toolbarMenu.setTitle(R.string.ListaProfe);
        setSupportActionBar(toolbarMenu);
    }


    private  void llenaLisViewProfesores(int idgrupo){
        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_CALIFICACION_PROFESORES
                +DirecionServicioRest.PATH_PROFESORES_GRUPO;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url + "/" + idgrupo, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int  i =0 ; i < response.length(); i++){
                        JSONObject jsonProfesor = response.getJSONObject(i);
                        usuario profesor = new usuario();
                        profesor.setNombre(jsonProfesor.getString("nombre"));
                        profesor.setId(jsonProfesor.getInt("id"));
                        profesor.setApellidos(jsonProfesor.getString("apellidos"));
                        listaPorfesores.add(profesor);
                        //listaProfesoresnombre.add(profesor.getId()+" "+profesor.getNombre() + "-"+profesor.getApellidos());
                    }
                    adapterProfersore.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
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
