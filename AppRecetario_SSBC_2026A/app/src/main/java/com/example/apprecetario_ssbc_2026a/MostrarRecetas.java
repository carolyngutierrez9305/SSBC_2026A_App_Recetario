package com.example.apprecetario_ssbc_2026a;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;


import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;

public class MostrarRecetas extends RecyclerView.Adapter<MostrarRecetas.RecetaViewHolder> {

    private ArrayList<Recetas> listaDeRecetas;
    private Context context;

    // Constructor con el nuevo nombre de tu clase
    public MostrarRecetas(ArrayList<Recetas> listaDeRecetas, Context context) {
        this.listaDeRecetas = listaDeRecetas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el diseño de la celda/tarjeta individual para cada receta
        // Cambia 'item_receta' por el nombre real de tu archivo XML para cada fila
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        Recetas receta = listaDeRecetas.get(position);

        // Asignamos los textos
        holder.txtNombre.setText(receta.getNombre());

        String rutaImagen = receta.getImagen();

        // VALIDACIÓN DE LA IMAGEN: ¿Viene de la galería o es un recurso local?
        if (rutaImagen != null && (rutaImagen.startsWith("content://") || rutaImagen.startsWith("file://"))) {
            // Si es una URI de la galería, la parseamos y cargamos directamente
            holder.imagenReceta.setImageURI(Uri.parse(rutaImagen));
        } else {
            try {
                // Si es un número (ID de los recursos), lo convertimos a entero
                int idRecurso = Integer.parseInt(rutaImagen);
                holder.imagenReceta.setImageResource(idRecurso);
            } catch (NumberFormatException e) {
                // Si está vacío o hay un error, ponemos la imagen por defecto
                holder.imagenReceta.setImageResource(R.drawable.logo);
            }
        }
    }

    @Override
    public int getItemCount() {
        return listaDeRecetas.size();
    }

    // Clase interna ViewHolder para mapear los componentes del XML de la celda
    public static class RecetaViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        ImageView imagenReceta;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            // Asegúrate de que estos IDs coincidan con los de tu XML 'item_receta'
            txtNombre = itemView.findViewById(R.id.nombreReceta);
            imagenReceta = itemView.findViewById(R.id.ImagenReceta);
        }
    }
}

