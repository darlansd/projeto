package com.example.appnoticia.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appnoticia.Activities.Segunda_activity;
import com.example.appnoticia.Config.TrocarFragment;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;
import com.example.appnoticia.navigation.Navigation_drawer_Activity;

public class Fragment_editar_perfil extends Fragment {
    Usuarios user;
    Button informacoes_pessoais,conta_seg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        informacoes_pessoais = view.findViewById(R.id.btn_editar_pessoal);
        conta_seg = view.findViewById(R.id.btn_editar_conta_seg);

        informacoes_pessoais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrocarFragment.intent_PutExtra(requireActivity(),getContext(), Segunda_activity.class,2);
            }
        });

        conta_seg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrocarFragment.intent_PutExtra(requireActivity(),getContext(), Segunda_activity.class,3);

            }
        });

     return view;
    }


}