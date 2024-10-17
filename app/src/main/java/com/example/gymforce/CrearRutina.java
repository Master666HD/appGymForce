
package com.example.gymforce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class CrearRutina extends AppCompatActivity {

     ImageView image1, image2, image3, image4, image5, image6;
     TextView textView1, textView2, textView3, textView4, textView5, textView6;
     Spinner musculares;
     HashMap<String, int[]> imageMap;
    HashMap<String, String[]> nombresEjerciciosMap;
    ImageView selectedImageView = null;
    Button frecuencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_rutina);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        frecuencia = findViewById(R.id.btnFrecuencia);

        image1 = findViewById(R.id.img1);
        image2 = findViewById(R.id.img2);
        image3 = findViewById(R.id.img3);
        image4 = findViewById(R.id.img4);
        image5 = findViewById(R.id.img5);
        image6 = findViewById(R.id.img6);



        textView1 = findViewById(R.id.txt1);
        textView2 = findViewById(R.id.txt2);
        textView3 = findViewById(R.id.txt3);
        textView4 = findViewById(R.id.txt4);
        textView5 = findViewById(R.id.txt5);
        textView6 = findViewById(R.id.txt6);

        setClickListener(image1);
        setClickListener(image2);
        setClickListener(image3);
        setClickListener(image4);
        setClickListener(image5);
        setClickListener(image6);



        musculares = findViewById(R.id.sp_musculo);
        String[] gruposMusculares = {"Pecho", "Espalda", "Piernas", "Hombros", "Bíceps"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gruposMusculares);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        musculares.setAdapter(adapter);

        // Inicializa el mapa de imágenes
        imageMap = new HashMap<>();
        imageMap.put("Pecho", new int[]{R.drawable.pecho1, R.drawable.pecho2, R.drawable.pecho3, R.drawable.pecho4, R.drawable.pecho5, R.drawable.pecho6});
        imageMap.put("Espalda", new int[]{R.drawable.espalda1, R.drawable.espalda2, R.drawable.espalda3, R.drawable.espalda4, R.drawable.espalda5, R.drawable.espalda6});
        imageMap.put("Piernas", new int[]{R.drawable.piernas1, R.drawable.piernas2, R.drawable.piernas3, R.drawable.piernas4, R.drawable.piernas5, R.drawable.piernas6});
        imageMap.put("Hombros", new int[]{R.drawable.hombros1, R.drawable.hombros2, R.drawable.hombros3, R.drawable.hombros4, R.drawable.hombros5, R.drawable.hombros6});
        imageMap.put("Bíceps", new int[]{R.drawable.brazos1, R.drawable.brazos2, R.drawable.brazos3, R.drawable.brazos4, R.drawable.brazos5, R.drawable.brazos6});


        nombresEjerciciosMap = new HashMap<>();
        nombresEjerciciosMap.put("Pecho", new String[]{"Press de pecho en máquina", "Press banca", "Aperturas em máquina", "Cruces en polea alta", "Press banca inclinado", "Cruces en polea baja"});
        nombresEjerciciosMap.put("Espalda", new String[]{"Remo en polea agarre abierto", "Remo con barra", "Jalon al pecho", "Remo con barra T", "Remo con máquina", "Remo con marcuernas"});
        nombresEjerciciosMap.put("Piernas", new String[]{"Prensa", "Extensiones", "Sentadilla en maquina Smith", "Sentadilla split en Smith", "Sentadilla Hack Squat", "Curl femoral"});
        nombresEjerciciosMap.put("Hombros", new String[]{"Rotación externa en polea", "Press militar con barra", "Press Arnold", "Aperturas posteriores en polea", "Elevaciones laterales", "Elevación frontal con polea"});
        nombresEjerciciosMap.put("Bíceps", new String[]{"Curl de bíceps con barra", "Curl concentrado con máquina", "Curl martillo", "Curl concentrado con mancuerna", "Curl biceps con maquina", "Curl de biceps con polea"});

        // Maneja el evento de selección del Spinner
        musculares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtén el grupo muscular seleccionado
                String grupoSeleccionado = (String) parent.getItemAtPosition(position);

                // Cambia las imágenes basadas en el grupo seleccionado
                cambiarImagenesYNombres(grupoSeleccionado);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No se seleccionó nada
            }
        });

        frecuencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Frecuencia.class);
                startActivity(it);
            }
        });
    }

    // Método para cambiar las imágenes según el grupo seleccionado
    public void cambiarImagenesYNombres(String grupo) {
        int[] imagenes = imageMap.get(grupo);
        String[] nombresEjercicios = nombresEjerciciosMap.get(grupo);

        if (imagenes != null && nombresEjercicios != null) {
            // Asignar imágenes
            image1.setImageResource(imagenes[0]);
            image2.setImageResource(imagenes[1]);
            image3.setImageResource(imagenes[2]);
            image4.setImageResource(imagenes[3]);
            image5.setImageResource(imagenes[4]);
            image6.setImageResource(imagenes[5]);

            // Asignar nombres de ejercicios
            textView1.setText(nombresEjercicios[0]);
            textView2.setText(nombresEjercicios[1]);
            textView3.setText(nombresEjercicios[2]);
            textView4.setText(nombresEjercicios[3]);
            textView5.setText(nombresEjercicios[4]);
            textView6.setText(nombresEjercicios[5]);

        }

    }

    public void setClickListener(final ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si hay una imagen previamente seleccionada, quítale el borde
                if (selectedImageView != null) {
                    selectedImageView.setBackgroundResource(R.drawable.border_default);
                }

                // Aplica el borde azul a la imagen seleccionada
                imageView.setBackgroundResource(R.drawable.border_selected);

                // Almacena la nueva imagen seleccionada
                selectedImageView = imageView;
            }
        });
    }





}

