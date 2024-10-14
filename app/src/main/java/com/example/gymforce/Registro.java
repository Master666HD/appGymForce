package com.example.gymforce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    EditText etUsuario, etPassword, etNombre, etApellidoPat, etApellidoMat, etCorreo;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de los campos
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etContra);
        etNombre = findViewById(R.id.etNombre);
        etApellidoPat = findViewById(R.id.apellidPat);
        etApellidoMat = findViewById(R.id.apellidoMat);
        etCorreo = findViewById(R.id.etCorreo);  // Añadir el campo de correo si lo necesitas
        btnRegistrar = findViewById(R.id.btnRegistar);

        // Evento al hacer clic en el botón "Registrar"
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarUsuario();
            }
        });
    }

    private void insertarUsuario() {
        // URL de la API (cambia esta URL por la de tu servidor)
        String URL = "http://192.168.0.99/SistemaGym/remote_registro.php";

        // Crear una solicitud de String con método POST
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Procesar respuesta del servidor
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(it);
                            } else {
                                String message = jsonResponse.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error en la respuesta del servidor", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Mostrar error en caso de fallo
                        Toast.makeText(getApplicationContext(), "Error al registrar: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Mapa para enviar los parámetros de los campos
                Map<String, String> params = new HashMap<>();
                params.put("usuario", etUsuario.getText().toString());
                params.put("password", etPassword.getText().toString());
                params.put("nombre", etNombre.getText().toString());
                params.put("apellidoPat", etApellidoPat.getText().toString());
                params.put("apellidoMat", etApellidoMat.getText().toString());
                params.put("correo", etCorreo.getText().toString()); // Si tienes campo de correo
                return params;
            }
        };

        // Crear una cola de solicitudes y agregar la solicitud
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
