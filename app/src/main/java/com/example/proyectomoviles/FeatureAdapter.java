package com.example.proyectomoviles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.TareaViewHolder> {

    private List<Feature> tareas;

    public FeatureAdapter(List<Feature> tareas) {
        this.tareas = tareas;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feature, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Feature tarea = tareas.get(position);
        holder.bind(tarea);
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }

    class TareaViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitulo;
        private TextView textViewDescripcion;

        TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
        }

        void bind(Feature tarea) {
            textViewTitulo.setText(tarea.getDescripcion());
            textViewDescripcion.setText(tarea.getDescripcion());

            // Aquí puedes personalizar la lógica según tus necesidades,
            // como asignar colores o iconos dependiendo del estado de la tarea, etc.
        }
    }
}