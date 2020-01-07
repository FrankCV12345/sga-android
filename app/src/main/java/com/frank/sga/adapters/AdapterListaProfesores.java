package com.frank.sga.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frank.sga.R;
import com.frank.sga.data.model.usuario;
import com.frank.sga.ui.CalificacionProfesor.CalificaProfesor;
import com.frank.sga.ui.CalificacionProfesor.PerfilProfesor;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaProfesores extends RecyclerView.Adapter<AdapterListaProfesores.ViewHolderDatos> implements View.OnClickListener {
    Context contexto;
    List<usuario> listaProfesores;
    private View.OnClickListener onClickListener;
    public AdapterListaProfesores(Context contexto, List<usuario> listaProfesores) {
        this.contexto = contexto;
        this.listaProfesores = listaProfesores;
    }

    @NonNull
    @Override
    public AdapterListaProfesores.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contexto)
                .inflate(R.layout.item_list_recycler,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos( view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaProfesores.ViewHolderDatos holder, final int position) {
            holder.btnVerPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer idProfesor = listaProfesores.get(position).getId();
                    Intent intent = new Intent(contexto, PerfilProfesor.class);
                    intent.putExtra("IdProfesor",idProfesor);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    contexto.startActivity(intent);
                }
            });
            holder.btnCalificaProfesor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer idProfesor = listaProfesores.get(position).getId();
                    Intent intent =new Intent(contexto, CalificaProfesor.class);
                    intent.putExtra("IdProfesor",idProfesor);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    contexto.startActivity(intent);
                }
            });
            holder.asignarDatos(listaProfesores.get(position));
    }

    public void setOnClickListener(View.OnClickListener listener ){
        this.onClickListener = listener;

    }

    @Override
    public int getItemCount() {
        return listaProfesores.size();
    }

    @Override
    public void onClick(View v) {
            if(onClickListener !=null){
                onClickListener.onClick(v);
            }
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
