package com.frank.sga.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.frank.sga.MenuPrincipal;
import com.frank.sga.Utilidades.DirecionServicioRest;
import com.frank.sga.Utilidades.MemoriaLocal;
import com.frank.sga.R;
import com.frank.sga.data.model.usuario;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private RequestQueue mQueque;
    Button btnLogin;
    EditText editTextCorreo,editTextPassword;
    String correo =null;
    String password = null;
    usuario user  = new usuario();
    Gson gson = new Gson();
    ProgressBar progressBar ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_login);
            MemoriaLocal.CONTEXTOLOGIN = getApplicationContext();
            progressBar = findViewById(R.id.loading);
            progressBar.setVisibility(View.INVISIBLE);
            if(MemoriaLocal.getDefaults("token",getApplicationContext()) !=null){
                    startActivity(new Intent(getApplicationContext(),MenuPrincipal.class));
            }
            btnLogin = findViewById(R.id.btnLogin);
            editTextCorreo = findViewById(R.id.Correo);
            editTextPassword = findViewById(R.id.password);
            mQueque = new Volley().newRequestQueue(getApplicationContext());

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    correo = editTextCorreo.getText().toString();
                    password = editTextPassword.getText().toString();

                    if(correo != null && password !=null){
                       if(correo.length() > 0 && password.length() > 0){
                           progressBar.setVisibility(View.VISIBLE);
                           Logea();
                       }else{
                           Toast.makeText(getApplicationContext(),"Datos no validos",Toast.LENGTH_LONG).show();
                       }
                    }else{
                        Toast.makeText(getApplicationContext(),"Datos no validos",Toast.LENGTH_LONG).show();
                    }
                    editTextCorreo.setText("");
                    editTextPassword.setText("");

                }
            });
    }

    public void Logea(){
        String ServcioLogin = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_LOGIN;
        user.setCorreo(correo);
        user.setPassword(password);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gson.toJson(user,usuario.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ServcioLogin, jsonObject,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println("ESTOO "+response.toString());
                    MemoriaLocal.setDefaults("token",response.getString("token"),getApplicationContext());
                    MemoriaLocal.setDefaults("idUser",response.getString("idUser"),getApplicationContext());

                    MemoriaLocal.setDefaults("Nombre",response.getString("nombre"),getApplicationContext());
                    startActivity(new Intent(getApplicationContext(),MenuPrincipal.class));
                    Toast.makeText(getApplicationContext(),response.getString("nombre"),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);

                } catch (JSONException e) {
                    progressBar.setVisibility(View.INVISIBLE);

                    System.out.println("ERROR JsonException ! " + e.getMessage());
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try{

                    if(error.networkResponse != null){

                        if(error.networkResponse.statusCode == 401){
                            Toast.makeText(getApplicationContext(),"Usuario o contrasena invalidos",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getApplicationContext(),"Puede que tenga problemas con la red",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    System.out.println("ERROR ! " + error.getMessage());


                }catch (Exception e){
                    progressBar.setVisibility(View.INVISIBLE);
                        e.printStackTrace();
                }
            }
        })  ;

    mQueque.add(jsonObjectRequest);

    }




}
