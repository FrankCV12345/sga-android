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
import com.frank.sga.data.model.cursosCarreras;

import java.util.List;

public class AdapterListaCursos extends  RecyclerView.Adapter<AdapterListaCursos.ViewHolderDatos> {
    Context contexto;
    List<cursosCarreras> listaCursos ;

    public AdapterListaCursos(Context contexto, List<cursosCarreras> listaCursos) {
        this.contexto = contexto;
        this.listaCursos = listaCursos;
    }

    @NonNull
    @Override
    public AdapterListaCursos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(contexto)
                .inflate(R.layout.item_list_recycler_curso,null,false);
        return new ViewHolderDatos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.btnVerMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.asignaDatos(listaCursos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView tvIdCurso;
        TextView tvNombreCurso;
        Button btnVerMas;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tvIdCurso = itemView.findViewById(R.id.tvidCurso);
            tvNombreCurso = itemView.findViewById(R.id.tvNombreCurso);
            btnVerMas = itemView.findViewById(R.id.btnVerMasCurso);
        }

        public void asignaDatos( cursosCarreras curso){
            tvIdCurso.setText(String.valueOf(curso.getId()));
            tvNombreCurso.setText(curso.getCurso().getNombrecurso());

        }
    }
}
