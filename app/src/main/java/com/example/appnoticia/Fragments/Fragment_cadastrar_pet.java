package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.appnoticia.R;


public class Fragment_cadastrar_pet extends Fragment {
    LinearLayout cadastar_pet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_pet, container, false);

        cadastar_pet = view.findViewById(R.id.btn_cadastrar_pet);
        cadastar_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"clicou",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}