package com.frank.sga.ui.CalificacionProfesor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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
import com.frank.sga.data.model.CalificacionAlumnoProfesor;
import com.frank.sga.data.model.PerfilCalificacionProfesor;
import com.frank.sga.ui.mantUser.manteniminetoUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerfilProfesor extends AppCompatActivity {

    Toolbar toolbarMenu;
    PerfilCalificacionProfesor perfilCalificacionProfesor = new PerfilCalificacionProfesor();
    List<String> comentarios = new ArrayList<>();
    RequestQueue mQueue;
    LinearLayout linearDominaTema;
    LinearLayout linearAclaraDudas;
    LinearLayout linearExpresaClaramente;
    LinearLayout lineaContExpreClaramente;
    ListView listViewComentarios ;

    TextView tvProcentajeAclaraDudas;
    TextView tvProcentajeDominaTema;
    TextView tvProcentajeExpresaClaramente;
    TextView tvCantidadCalificaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_profesor);
        toolbarMenu = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarMenu);
        mQueue = new Volley().newRequestQueue(getApplicationContext());
        linearDominaTema = findViewById(R.id.linearporcentajeDominaTema);
        linearAclaraDudas = findViewById(R.id.linearPortcentajeAclaraDudas);
        linearExpresaClaramente = findViewById(R.id.linearPorcentajeEcpresaClaridad);
        lineaContExpreClaramente = findViewById(R.id.linearContExpreCalidad);
        listViewComentarios = findViewById(R.id.comentariosPerfil);

        tvCantidadCalificaciones = findViewById(R.id.tvCantidadCalificaciones);
        tvProcentajeAclaraDudas = findViewById(R.id.tvProcentajePorcentajeAclaraDudas);
        tvProcentajeDominaTema = findViewById(R.id.tvPorcentajeDominaTema);
        tvProcentajeExpresaClaramente = findViewById(R.id.tvPorcentajeExpresaDudas);


        Integer idprofesor = getIntent().getIntExtra("IdProfesor",0);
        servicePerfilProfesor(idprofesor);
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

    public void servicePerfilProfesor(Integer idProfesor){

        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_CALIFICACION_PROFESORES+"/"+idProfesor;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    perfilCalificacionProfesor.setDominaTema(response.getInt("dominaTema"));
                    perfilCalificacionProfesor.setPrimedioExpresaClaramente(response.getInt("primedioExpresaClaramente"));
                    perfilCalificacionProfesor.setPromedioAclaraDudas(response.getInt("promedioAclaraDudas"));
                    perfilCalificacionProfesor.setNombreProfesor(response.getString("nombreProfesor"));
                    perfilCalificacionProfesor.setCantidadCalificaciones(response.getInt("cantidadCalificaciones"));
                    for(int i =0 ; i< response.getJSONArray("comentarios").length();i ++){
                        String comentario = (String) response.getJSONArray("comentarios").get(i);
                        comentarios.add(comentario);
                    }

                    perfilCalificacionProfesor.setComentarios(comentarios);


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spiner_items,perfilCalificacionProfesor.getComentarios());
                    listViewComentarios.setAdapter(adapter);
                    llenadatos();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        } ;

        mQueue.add(jsonObjectRequest);

    }

    private void llenadatos(){
        double porcentajeAclaraDudas = calculaPorcentaje(perfilCalificacionProfesor.getPromedioAclaraDudas(),5);
        double porcentajeExpresaClaramente = calculaPorcentaje(perfilCalificacionProfesor.getPrimedioExpresaClaramente(),5);
        double porcentajeDominaTema =  calculaPorcentaje(perfilCalificacionProfesor.getDominaTema(),5);
        tvProcentajeAclaraDudas.setText(  porcentajeAclaraDudas+"%");
        tvProcentajeExpresaClaramente.setText(porcentajeExpresaClaramente+"%");
        tvProcentajeDominaTema.setText(porcentajeDominaTema+"%");


        linearAclaraDudas.getLayoutParams().width = calculanumeroDePorcentaje(
                (int) porcentajeAclaraDudas
                ,lineaContExpreClaramente.getLayoutParams().width) ;
        linearDominaTema.getLayoutParams().width = calculanumeroDePorcentaje(
                (int) porcentajeDominaTema,
                lineaContExpreClaramente.getLayoutParams().width) ;
        linearExpresaClaramente.getLayoutParams().width =calculanumeroDePorcentaje(
            (int) porcentajeExpresaClaramente,
            lineaContExpreClaramente.getLayoutParams().width
        );

        toolbarMenu.setTitle("Nombre Prof. "+perfilCalificacionProfesor.getNombreProfesor());
        tvCantidadCalificaciones.setText("Cant. Calificaciones : " + perfilCalificacionProfesor.getCantidadCalificaciones());

    }

    private double calculaPorcentaje(int numero,double universo){
        return  (numero * 100) / universo;
    }
    private int calculanumeroDePorcentaje( int porcentaje, int universo){
        return (porcentaje * universo) /100;
    }

}
