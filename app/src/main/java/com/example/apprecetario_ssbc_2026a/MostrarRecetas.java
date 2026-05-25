package com.example.apprecetario_ssbc_2026a;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MostrarRecetas extends RecyclerView.Adapter<MostrarRecetas.RecetaViewHolder> {

    public MostrarRecetas() {
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class RecetaViewHolder extends RecyclerView.ViewHolder {

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
