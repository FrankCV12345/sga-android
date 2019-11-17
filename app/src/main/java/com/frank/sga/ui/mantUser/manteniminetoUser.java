package com.frank.sga.ui.mantUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
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
import com.frank.sga.MenuPrincipal;
import com.frank.sga.R;
import com.frank.sga.Utilidades.DirecionServicioRest;
import com.frank.sga.Utilidades.MemoriaLocal;
import com.frank.sga.data.model.tipoDoc;
import com.frank.sga.data.model.usuario;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.frank.sga.data.model.sexo;

public class manteniminetoUser extends AppCompatActivity {
    Toolbar toolbarMenu;
    private usuario user = new usuario();
    private RequestQueue mQueque;
    private ProgressBar progressBar;
    private Long idUser;
    private EditText ETNombreUSer,ETApellidos,ETdni,ETCorreo,ETTelefono;
    private Button btnCancela, btnActualiza;
    private Spinner SpinnerTipoSexo, SpinnerTipoDOC;
    private List<sexo> listTipoSexo = new ArrayList<>();
    private List<tipoDoc> listTipoDoc = new ArrayList<>();
    Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimineto_user);
        progressBar = findViewById(R.id.ProgressMantuser);
        progressBar.setVisibility(View.VISIBLE);
        toolbarMenu = findViewById(R.id.toolbar);
        toolbarMenu.setTitle(R.string.EditaPerfil);
        ETNombreUSer = findViewById(R.id.ETNombreUser);
        ETApellidos = findViewById(R.id.ETApellidoUser);
        ETdni = findViewById(R.id.ETdni);
        ETCorreo = findViewById(R.id.ETCorreo);
        ETTelefono = findViewById(R.id.ETTelefono);
        btnCancela = findViewById(R.id.BtnCancelaAcrualizacion);
        btnActualiza = findViewById(R.id.btnActualizaDatos);
        SpinnerTipoDOC = findViewById(R.id.SpinerTipoDoc);
        SpinnerTipoSexo = findViewById(R.id.SpinerTipoSexo);
        btnActualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long iduser = Long.parseLong(MemoriaLocal.getDefaults("idUser",MemoriaLocal.CONTEXTOLOGIN));
                String nombre = ETNombreUSer.getText().toString();
                String apellidos = ETApellidos.getText().toString();
                String dni = ETdni.getText().toString();
                String correo  = ETCorreo.getText().toString();
                String telefono = ETTelefono.getText().toString();
                ActualizaUser(iduser ,nombre,apellidos,dni,correo,telefono);
            }
        });
        btnCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MenuPrincipal.class));
            }
        });
        setSupportActionBar(toolbarMenu);
        mQueque = new Volley().newRequestQueue(getApplicationContext());

        idUser = Long.parseLong(MemoriaLocal.getDefaults("idUser",MemoriaLocal.CONTEXTOLOGIN));
        SpinnerTipoSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    user.setSexo(new sexo(listTipoSexo.get(position).getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerTipoDOC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user.setTipoDoc(new tipoDoc(listTipoDoc.get(position).getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if(idUser != null){
            ServicioBuscaUser(idUser);
        }else {
            Toast.makeText(getApplicationContext(),"No hay id de usuario",Toast.LENGTH_LONG).show();
        }





    }
//lleno el spinner de TiposSexo
    private void llenaSpinnerTipoSexo(final long idSexoPredeterminado){
        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_TIPOS_SEXO;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<String> listaTiposDocNombre = new ArrayList<>();
                Integer posicionValorPredeterminado = null;
                try {
                    for (int  i = 0 ; i < response.length(); i++){
                            JSONObject jsonTIpoSexo =  response.getJSONObject(i);
                            listTipoSexo.add(
                                    new sexo(
                                            jsonTIpoSexo.getInt("id"),
                                            jsonTIpoSexo.getString("nombreSexo"),""
                                    )
                            );
                            listaTiposDocNombre.add(jsonTIpoSexo.getString("nombreSexo"));

                            if (idSexoPredeterminado ==  jsonTIpoSexo.getInt("id")){
                                posicionValorPredeterminado = i;
                            }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SpinnerTipoSexo.setAdapter(new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.spiner_items,
                        listaTiposDocNombre
                ));

                if (posicionValorPredeterminado != null){
                    SpinnerTipoSexo.setSelection(posicionValorPredeterminado);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Erro al cargar Los tipos de doc",Toast.LENGTH_LONG).show();
            }
        } )
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
        mQueque.add(jsonArrayRequest);
    }
//lleno el spinner TipoDoc
    private void llenaSpinnerTipoDoc(final long idTipoDocPrederminado){
        String url = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_TIPOS_DOC;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<String> listaTipoSexoNombre = new ArrayList<>();
                Integer posicionValorPrederminado = null ;
                try {
                    for (int  i = 0 ; i < response.length();i++){

                            JSONObject jsonTipoDoc = response.getJSONObject(i);
                            listTipoDoc.add(new tipoDoc(
                                    jsonTipoDoc.getInt("id"),
                                    jsonTipoDoc.getString("nombreDoc"),
                                    jsonTipoDoc.getString("descripcion")
                            ));
                        listaTipoSexoNombre.add(jsonTipoDoc.getString("nombreDoc"));
                        if(idTipoDocPrederminado == jsonTipoDoc.getInt("id")){
                            posicionValorPrederminado = i;
                        }
                }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SpinnerTipoDOC.setAdapter(new ArrayAdapter<String>(
                        getApplicationContext(),
                        R.layout.spiner_items,
                        listaTipoSexoNombre
                ));
                if(posicionValorPrederminado != null){
                    SpinnerTipoDOC.setSelection(posicionValorPrederminado);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"No se pudo caragar Tipos de documentos",Toast.LENGTH_LONG).show();
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

        mQueque.add(jsonArrayRequest);

    }

    private void ServicioBuscaUser(long idUser){
        String url  = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_USUARIO;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url+"/"+idUser, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    usuario user = new usuario();
                    user.setDni(response.getString("dni"));
                    user.setNombre(response.getString("nombre"));
                    user.setCorreo(response.getString("correo"));
                    user.setApellidos(response.getString("apellidos"));
                    user.setDirecion(response.getString("direcion"));
                    user.setTelefono(response.getString("telefono"));
                    user.setTipoDoc(new tipoDoc(response.getJSONObject("tipoDoc").getInt("id")));
                    user.setSexo(new sexo(response.getJSONObject("sexo").getInt("id")));
                    llenaFormMantenimietouser(user);
                    llenaSpinnerTipoDoc(user.getTipoDoc().getId());
                    llenaSpinnerTipoSexo(user.getSexo().getId());
                    progressBar.setVisibility(View.INVISIBLE);
                   //Toast.makeText(getApplicationContext(),user.getTipoDoc().getId() + " - "+ user.getSexo().getId(),Toast.LENGTH_LONG).show();
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
        mQueque.add(jsonObjectRequest);
    }

    private void ActualizaUser(Long idUser , String  nombre , String apellidos  , String dni , String correo , String telefono){

        progressBar.setVisibility(View.VISIBLE);
        String url = DirecionServicioRest.IP_SERVICIO_REST +DirecionServicioRest.PATH_USUARIO;

        user.setNombre(nombre);
        user.setApellidos(apellidos);
        user.setDni(dni);
        user.setCorreo(correo);
        user.setTelefono(telefono);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gson.toJson(user,usuario.class));
            System.out.println(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url+"/"+idUser, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ETNombreUSer.setText(response.getString("nombre"));
                            ETApellidos.setText(response.getString("apellidos"));
                            ETdni.setText(response.getString("dni"));
                            ETCorreo.setText(response.getString("correo"));
                            ETTelefono.setText(response.getString("telefono"));
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(),"Actualizado",Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);
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
        }

                ;
        mQueque.add(jsonObjectRequest);
    }
    private void llenaFormMantenimietouser(usuario user){
            ETApellidos.setText(user.getApellidos());
            ETNombreUSer.setText(user.getNombre());
            ETTelefono.setText(user.getTelefono());
            ETdni.setText(user.getDni());
            ETCorreo.setText(user.getCorreo());
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
                startActivity(new Intent(getApplicationContext(),manteniminetoUser.class));
                break;
        }
        return true;
    }

}
