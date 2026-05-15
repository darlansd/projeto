package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.R;
import com.google.firebase.database.DatabaseReference;

public class Fragment_seus_pets_adocao extends Fragment {
    DatabaseReference fire = Configuracao_firebase.getfirebasedatabase();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seus_pets_adocao, container, false);

        return view;
    }
}