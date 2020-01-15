package com.frank.sga.ui.mantUser;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.frank.sga.R;

public class DialogActualizaContrasena extends AppCompatDialogFragment  {
    EditText etContraActual , etNvaContra,etConfirmContra;
    IDialogActualizaContrasenaListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_cambia_contra,null);

        builder.setView(view)
                .setTitle("Actualiza Contraseña")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ContraActual = etContraActual.getText().toString();
                        String Nvacontra =  etNvaContra.getText().toString();
                        String ConfirmaNvaContra  =  etConfirmContra.getText().toString();
                        listener.ApplyText(ContraActual,Nvacontra,ConfirmaNvaContra);
                    }
                });
        etContraActual = view.findViewById(R.id.etActualContra);
        etNvaContra = view.findViewById(R.id.etNavContra);
        etConfirmContra =  view.findViewById(R.id.etContraconfirmada);

        return builder.create();
    }
    @Override
    public  void onAttach(Context context){
            super.onAttach(context);
            try{
                listener = (IDialogActualizaContrasenaListener)context;
            }catch (Exception e){

            }
    }

    public interface IDialogActualizaContrasenaListener{
        void ApplyText(String Actualcontra, String NvaContra , String Contraconfirmada);
    }

}
