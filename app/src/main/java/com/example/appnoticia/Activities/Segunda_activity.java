package com.example.appnoticia.Activities;


import android.app.Fragment;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import com.example.appnoticia.Config.TrocarFragment;
import com.example.appnoticia.Fragments.Fragment_cadastro;
import com.example.appnoticia.Fragments.Fragment_editar_informacoes_pessoais;
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
                TrocarFragment.trocar(this,R.id.frame_principal,new Fragment_cadastro());
                break;

            case 1:
                TrocarFragment.trocar(this,R.id.frame_principal,new Fragment_entrar());
                break;

            case 2:
                TrocarFragment.trocar(this,R.id.frame_principal,new Fragment_editar_informacoes_pessoais());
                break;

            case 3:
                break;
        }


    }


}