package com.example.appnoticia.Activities;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;


import com.example.appnoticia.Config.TrocarFragment;
import com.example.appnoticia.Fragments.Fragment_cadastrar_cachorro;
import com.example.appnoticia.Fragments.Fragment_cadastrar_gato;
import com.example.appnoticia.Fragments.Fragment_cadastro;
import com.example.appnoticia.Fragments.Fragment_editar_informacoes_pessoais;
import com.example.appnoticia.Fragments.Fragment_entrar;
import com.example.appnoticia.R;

public class Segunda_activity extends AppCompatActivity {

    int show_fragment;
    Bundle dados;
    int cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        //responsaveis por alguma coisa que eu tenho que intender no fragment de cadastrar gato e cachorro
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.frame_principal), (view, windowInsets) -> {

            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());

            view.setPadding(
                    view.getPaddingLeft(),
                    view.getPaddingTop(),
                    view.getPaddingRight(),
                    insets.bottom
            );

            return windowInsets;
        });


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

            case 4:
                TrocarFragment.trocar_putExtra(this,R.id.frame_principal,new Fragment_cadastrar_gato(),getApplicationContext(), Fragment_cadastrar_gato.class,0);
                break;

            case 5:
                TrocarFragment.trocar_putExtra(this,R.id.frame_principal,new Fragment_cadastrar_cachorro(),getApplicationContext(), Fragment_cadastrar_gato.class,1);
                break;
        }

        /*
        cadastrar = dados.getInt("CADASTRAR");

        switch (cadastrar){
            case 1:
                TrocarFragment.trocar(this,R.id.frame_principal,new Fragment_cadastro());
                break;
        }

         */

    }


}