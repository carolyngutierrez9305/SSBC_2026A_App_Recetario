package com.example.apprecetario_ssbc_2026a;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Recetas> listaDeRecetas;
    private MostrarRecetas adapter;
    private RecyclerView recyclerView;
    private Button btnAgregar;

    private static final int REQUEST_CODE_AGREGAR_RECETA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaDeRecetas = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new MostrarRecetas(listaDeRecetas, this);
        recyclerView.setAdapter(adapter);


        btnAgregar = findViewById(R.id.agregar_receta);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarRecetaActivity.class);
                startActivityForResult(intent, REQUEST_CODE_AGREGAR_RECETA);
            }
        });
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AGREGAR_RECETA && resultCode == RESULT_OK && data != null) {

            String nombre = data.getStringExtra("nombre_receta");
            String ingredientes = data.getStringExtra("ingredientes_receta");
            String preparacion = data.getStringExtra("preparacion_receta");


            String rutaImagen = data.getStringExtra("imagen_receta");


            if (rutaImagen == null) {
                rutaImagen = "";
            }


            listaDeRecetas.add(new Recetas(nombre, ingredientes, preparacion, rutaImagen));

            adapter.notifyDataSetChanged();
        }
    }
}
