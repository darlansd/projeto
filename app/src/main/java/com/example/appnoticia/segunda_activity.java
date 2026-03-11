package com.example.appnoticia;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import Fragments.Fragment_cadastro;
import Fragments.Fragment_entrar;

public class segunda_activity extends AppCompatActivity {

    Button btn_cadastrar;
    int show_fragment;

    Fragment frag;

    Bundle dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        dados =getIntent().getExtras();

        show_fragment = dados.getInt("SHOW_FRAGMENT");

        switch (show_fragment){

            case 0:
                FragmentTransaction cadastrar = getSupportFragmentManager().beginTransaction();
                cadastrar.replace(R.id.frame_principal_2, new Fragment_cadastro());
                cadastrar.commit();
            break;

            case 1:
                FragmentTransaction entrar = getSupportFragmentManager().beginTransaction();
                entrar.replace(R.id.frame_principal_2,new Fragment_entrar());
                entrar.commit();
            break;
        }




    }


}