package com.example.apprecetario_ssbc_2026a;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalleReceta extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_receta2);

        TextView tvNombre = findViewById(R.id.titulo_receta_detalle);
        TextView tvIngredientes = findViewById(R.id.ingredientes_receta_detalle);
        TextView tvPreparacion = findViewById(R.id.procedimiento_receta_detalle);
        ImageView ivReceta = findViewById(R.id.imagen_receta_detalle);


        String nombre = getIntent().getStringExtra("nombre_receta");
        String ingredientes = getIntent().getStringExtra("ingredientes_receta");
        String preparacion = getIntent().getStringExtra("preparacion_receta");
        String rutaImagen = getIntent().getStringExtra("imagen_receta");

        tvNombre.setText(nombre);
        tvIngredientes.setText(ingredientes);
        tvPreparacion.setText(preparacion);


        if (rutaImagen != null && (rutaImagen.startsWith("content://") || rutaImagen.startsWith("file://"))) {
            ivReceta.setImageURI(Uri.parse(rutaImagen));
        } else {
            try {
                int idRecurso = Integer.parseInt(rutaImagen);
                ivReceta.setImageResource(idRecurso);
            } catch (NumberFormatException e) {
                ivReceta.setImageResource(R.drawable.logo);
            }
        }
    }
}
