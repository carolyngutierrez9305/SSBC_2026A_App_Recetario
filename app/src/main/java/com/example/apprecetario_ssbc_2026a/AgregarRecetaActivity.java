package com.example.apprecetario_ssbc_2026a;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class AgregarRecetaActivity extends AppCompatActivity {

    private EditText inputNombre, inputIngredientes, inputPreparacion;
    private ImageView imagenSeleccionar;
    private Button btnGuardar;
    private String uriImagenSeleccionada = "";

    // Registro del componente para acceder de forma segura a los archivos multimedia
    private final ActivityResultLauncher<String> abrirGaleriaLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    if (uri != null) {
                        imagenSeleccionar.setImageURI(uri);
                        uriImagenSeleccionada = uri.toString();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_receta);

        inputNombre = findViewById(R.id.nombre_receta_input);
        inputIngredientes = findViewById(R.id.ingredientes_receta_input);
        inputPreparacion = findViewById(R.id.procedimiento_receta_input);
        imagenSeleccionar = findViewById(R.id.imagen_seleccionar);
        btnGuardar = findViewById(R.id.btn_guardar_receta);

        imagenSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleriaLauncher.launch("image/*");
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = inputNombre.getText().toString();
                String ingredientes = inputIngredientes.getText().toString();
                String preparacion = inputPreparacion.getText().toString();

                Intent intentDevolucion = new Intent();
                intentDevolucion.putExtra("nombre_receta", nombre);
                intentDevolucion.putExtra("ingredientes_receta", ingredientes);
                intentDevolucion.putExtra("preparacion_receta", preparacion);
                intentDevolucion.putExtra("imagen_receta", uriImagenSeleccionada);

                setResult(RESULT_OK, intentDevolucion);
                finish();
            }
        });
    }
}
