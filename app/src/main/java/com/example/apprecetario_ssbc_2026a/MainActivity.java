package com.example.apprecetario_ssbc_2026a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Recetas> listaDeRecetas;
    private MostrarRecetas adapter; // Tu adaptador personalizado para la lista
    private RecyclerView recyclerView; // O ListView, dependiendo de cuál uses
    private Button btnAgregar; // El botón para abrir el formulario

    private static final int REQUEST_CODE_AGREGAR_RECETA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Inicialización de la lista COMPLETAMENTE VACÍA
        listaDeRecetas = new ArrayList<>();

        // 3. Enlace y configuración de la vista de la lista (RecyclerView)
        recyclerView = findViewById(R.id.recyclerView); // Verifica el ID de tu XML
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializamos el adaptador pasándole la lista vacía
        adapter = new MostrarRecetas(listaDeRecetas, this);
        recyclerView.setAdapter(adapter);

        // 4. Configuración del botón para ir a agregar una nueva receta
        btnAgregar = findViewById(R.id.agregar_receta); // Verifica el ID de tu XML
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarRecetaActivity.class);
                startActivityForResult(intent, REQUEST_CODE_AGREGAR_RECETA);
            }
        });
    }

    // 5. Recepción de los datos enviados desde AgregarRecetaActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AGREGAR_RECETA && resultCode == RESULT_OK && data != null) {
            // Extraemos los textos del formulario
            String nombre = data.getStringExtra("nombre_receta");
            String ingredientes = data.getStringExtra("ingredientes_receta");
            String preparacion = data.getStringExtra("preparacion_receta");

            // Extraemos la URI de la imagen seleccionada de la galería
            String rutaImagen = data.getStringExtra("imagen_receta");

            // Si el usuario no eligió foto en la galería, le asignamos una cadena vacía
            if (rutaImagen == null) {
                rutaImagen = "";
            }

            // Agregamos el nuevo objeto "Recetas" a nuestra lista original vacía
            listaDeRecetas.add(new Recetas(nombre, ingredientes, preparacion, rutaImagen));

            // Le avisamos al adaptador que la lista creció para que refresque la pantalla
            adapter.notifyDataSetChanged();
        }
    }
}




