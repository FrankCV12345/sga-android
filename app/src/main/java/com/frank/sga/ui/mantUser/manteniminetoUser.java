package com.frank.sga.ui.mantUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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
import com.frank.sga.data.model.usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class manteniminetoUser extends AppCompatActivity {
    Toolbar toolbarMenu;
    private RequestQueue mQueque;
    private Long idUser;
    private EditText ETNombreUSer,ETApellidos,ETdni,ETCorreo,ETTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantenimineto_user);
        toolbarMenu = findViewById(R.id.toolbar);
        toolbarMenu.setTitle(R.string.EditaPerfil);
        ETNombreUSer = findViewById(R.id.ETNombreUser);
        ETApellidos = findViewById(R.id.ETApellidoUser);
        ETdni = findViewById(R.id.ETdni);
        ETCorreo = findViewById(R.id.ETCorreo);
        ETTelefono = findViewById(R.id.ETTelefono);
        setSupportActionBar(toolbarMenu);
        mQueque = new Volley().newRequestQueue(getApplicationContext());

        idUser = Long.parseLong(MemoriaLocal.getDefaults("idUser",MemoriaLocal.CONTEXTOLOGIN));
        if(idUser != null){
            ServicioBuscaUser(idUser);
        }else {
            Toast.makeText(getApplicationContext(),"No hay id de usuario",Toast.LENGTH_LONG).show();
        }



    }
    private void ServicioBuscaUser(long idUser){
        String url  = DirecionServicioRest.IP_SERVICIO_REST+DirecionServicioRest.PATH_USUARIO;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url+"/"+idUser, null, new Response.Listener<JSONObject>() {
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
                    llenaFormMantenimietouser(user);
                    //Toast.makeText(getApplicationContext(),,Toast.LENGTH_LONG).show();
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
