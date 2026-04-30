package com.example.appnoticia.Activities;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.appnoticia.Fragments.Fragment_cadastrar_pet;
import com.example.appnoticia.Fragments.Fragment_cadastro;
import com.example.appnoticia.Fragments.Fragment_editar_perfil;
import com.example.appnoticia.Fragments.Fragment_entrar;
import com.example.appnoticia.R;

public class Segunda_activity extends AppCompatActivity {

    int show_fragment;
    Bundle dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        dados = getIntent().getExtras();

        show_fragment = dados.getInt("SHOW_FRAGMENT");

        switch (show_fragment) {

            case 0:
                FragmentTransaction cadastrar = getSupportFragmentManager().beginTransaction();
                cadastrar.replace(R.id.frame_principal, new Fragment_cadastro());
                cadastrar.commit();
                break;

            case 1:
                FragmentTransaction entrar = getSupportFragmentManager().beginTransaction();
                entrar.replace(R.id.frame_principal, new Fragment_entrar());
                entrar.commit();
                break;

            case 4:
                FragmentTransaction editar = getSupportFragmentManager().beginTransaction();
                editar.replace(R.id.frame_principal, new Fragment_editar_perfil());
                editar.commit();
                break;

            case 5:
                FragmentTransaction cad_pet = getSupportFragmentManager().beginTransaction();
                cad_pet.replace(R.id.frame_principal, new Fragment_cadastrar_pet());
                cad_pet.commit();
                break;
        }


    }


}