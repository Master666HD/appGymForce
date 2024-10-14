package com.example.gymforce;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Frecuencia extends AppCompatActivity {

    CheckBox Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo;
    Button btnRegistrarDias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frecuencia);

        // Inicializar los CheckBox
        Lunes = findViewById(R.id.chLunes);
        Martes = findViewById(R.id.chMartes);
        Miercoles = findViewById(R.id.chMiercoles);
        Jueves = findViewById(R.id.chJueves);
        Viernes = findViewById(R.id.chViernes);
        Sabado = findViewById(R.id.chSabado);
        Domingo = findViewById(R.id.chDomingo);

        btnRegistrarDias = findViewById(R.id.btnRegistrarDias);

        // Configurar el botón para registrar los días seleccionados
        btnRegistrarDias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarDiasAsistidos();
            }
        });
    }

    // Método para registrar los días seleccionados
    private void registrarDiasAsistidos() {
        StringBuilder diasAsistidos = new StringBuilder();

        if (Lunes.isChecked()) {
            diasAsistidos.append("Lunes ");
        }
        if (Martes.isChecked()) {
            diasAsistidos.append("Martes ");
        }
        if (Miercoles.isChecked()) {
            diasAsistidos.append("Miércoles ");
        }
        if (Jueves.isChecked()) {
            diasAsistidos.append("Jueves ");
        }
        if (Viernes.isChecked()) {
            diasAsistidos.append("Viernes ");
        }
        if (Sabado.isChecked()) {
            diasAsistidos.append("Sábado ");
        }
        if (Domingo.isChecked()) {
            diasAsistidos.append("Domingo ");
        }

        // Mostrar los días asistidos en un Toast o almacenarlos en la base de datos
        if (diasAsistidos.length() > 0) {
            Toast.makeText(getApplicationContext(), "Días registrados: " + diasAsistidos.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "No seleccionaste ningún día", Toast.LENGTH_SHORT).show();
        }



    }


}
