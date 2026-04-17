package com.example.appnoticia.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.appnoticia.Fragments.Fragment_adote_um_pet;
import com.example.appnoticia.R;

public class Terceira_Activity extends AppCompatActivity {

    Bundle dados;
    int show_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_terceira);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        dados = getIntent().getExtras();
        show_fragment = dados.getInt("SHOW_FRAGMENT");

        switch (show_fragment){
            case 3:
                FragmentTransaction adote = getSupportFragmentManager().beginTransaction();
                adote.replace(R.id.frame_principal,new Fragment_adote_um_pet());
                adote.commit();
        }



    }
}