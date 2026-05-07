package com.example.appnoticia.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;
import com.example.appnoticia.navigation.Navigation_drawer_Activity;

public class Fragment_editar_perfil extends Fragment {
    EditText user_name;
    Button confirmar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        user_name = view.findViewById(R.id.edt_userName);

        confirmar = view.findViewById(R.id.btn_confirmar);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome_atualizado = user_name.getText().toString();

                if (!nome_atualizado.isEmpty()){

                    Usuarios.atualizarnome(nome_atualizado);
                    Toast.makeText(getContext(),"Nome Atualizado",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getContext(), Navigation_drawer_Activity.class));

                }else{
                    Toast.makeText(getContext(),"preecha o campo nome",Toast.LENGTH_SHORT).show();

                }

            }
        });
     return view;
    }


}