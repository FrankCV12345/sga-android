package com.frank.sga.ui.crusos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.frank.sga.adapters.AdapterListaCursos;
import com.frank.sga.data.model.Curso;
import com.frank.sga.data.model.cursosCarreras;
import com.frank.sga.ui.mantUser.manteniminetoUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaCursos extends AppCompatActivity {
    Toolbar toolbarMenu;
    RequestQueue mQueue;
    RecyclerView recyclerViewCurso;
    List<cursosCarreras> listaCursos = new ArrayList<>();
    AdapterListaCursos adapterListaCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cursos);
        toolbarMenu = findViewById(R.id.toolbar);
        toolbarMenu.setTitle(R.string.MisCursos);
        setSupportActionBar(toolbarMenu);
        recyclerViewCurso = findViewById(R.id.recyclerListaCursos);
        recyclerViewCurso.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapterListaCursos = new AdapterListaCursos(getApplicationContext(),listaCursos);
        recyclerViewCurso.setAdapter(adapterListaCursos);
        mQueue = new Volley().newRequestQueue(getApplicationContext());
        Integer idGurpo = Integer.parseInt( MemoriaLocal.getDefaults("idGrupo",MemoriaLocal.CONTEXTOLOGIN));
        serviseListaCursos(idGurpo);

    }


    private void serviseListaCursos(Integer idGrupo){
        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_CURSOS_EN_GRUPO+"/"+idGrupo;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i =0 ; i < response.length();i ++ ){
                        JSONObject jsonCursoEngrupo = response.getJSONObject(i);
                        JSONObject jsonCurso = jsonCursoEngrupo.getJSONObject("curso");
                        cursosCarreras cursoEnGrupo = new cursosCarreras();

                        Curso curso = new Curso();
                        curso.setNombrecurso(jsonCurso.getString("nombrecurso"));
                        curso.setId(jsonCurso.getInt("id"));

                        cursoEnGrupo.setId(jsonCursoEngrupo.getInt("id"));
                        cursoEnGrupo.setCurso(curso);

                        listaCursos.add(cursoEnGrupo);
                    }
                    adapterListaCursos.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Problemas al cargar",Toast.LENGTH_LONG).show();
            }
        }) {
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
                startActivity(new Intent(getApplicationContext(), MenuPrincipal.class));
                break;
            case R.id.IrActualizaDatos:
                startActivity(new Intent(getApplicationContext(), manteniminetoUser.class));
                break;

        }
        return true;
    }
}
