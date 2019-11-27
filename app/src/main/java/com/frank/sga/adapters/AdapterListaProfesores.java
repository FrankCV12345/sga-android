package com.frank.sga.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frank.sga.R;
import com.frank.sga.data.model.usuario;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaProfesores extends RecyclerView.Adapter<AdapterListaProfesores.ViewHolderDatos> {
    Context contexto;
    List<usuario> listaProfesores;

    public AdapterListaProfesores(Context contexto, List<usuario> listaProfesores) {
        this.contexto = contexto;
        this.listaProfesores = listaProfesores;
    }

    @NonNull
    @Override
    public AdapterListaProfesores.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto)
                .inflate(R.layout.item_list_recycler,null,false);
        return new ViewHolderDatos( view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaProfesores.ViewHolderDatos holder, int position) {
            holder.asignarDatos(listaProfesores.get(position));
    }

    @Override
    public int getItemCount() {
        return listaProfesores.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvIdProfesor ;
        TextView tvNombreProfesor ;
        TextView tvApellidosProfesor ;
        Button btnCalificaProfesor;
        Button btnVerPerfil;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvIdProfesor = itemView.findViewById(R.id.tvIdProfesor);
            tvNombreProfesor = itemView.findViewById(R.id.tvNombreProfesor);
            tvApellidosProfesor = itemView.findViewById(R.id.tvApellidoProfesor);
            btnCalificaProfesor = itemView.findViewById(R.id.btnCalificaProfe);
            btnVerPerfil = itemView.findViewById(R.id.btnVerPerfil);
        }

        public void asignarDatos(usuario usuario) {
            tvIdProfesor.setText(String.valueOf(usuario.getId()));
            tvApellidosProfesor.setText(usuario.getApellidos());
            tvNombreProfesor.setText(usuario.getNombre());


        }
    }
}
