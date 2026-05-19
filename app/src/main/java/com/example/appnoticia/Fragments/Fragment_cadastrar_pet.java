package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.appnoticia.Activities.Segunda_activity;
import com.example.appnoticia.Config.TrocarFragment;
import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;


public class Fragment_cadastrar_pet extends Fragment {
    AppCompatButton btn_gato,btn_cachorro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_pet, container, false);

        btn_gato = view.findViewById(R.id.btn_cadastrar_gato);
        btn_cachorro = view.findViewById(R.id.btn_cadastrar_cachorro);

        btn_gato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrocarFragment.intent_PutExtra(requireActivity(),getContext(), Segunda_activity.class,4);
            }
        });

        btn_cachorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrocarFragment.intent_PutExtra(requireActivity(),getContext(), Segunda_activity.class,5);
            }
        });
        return view;
    }


}