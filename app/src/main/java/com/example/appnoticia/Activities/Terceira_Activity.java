package com.example.appnoticia.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.MenuProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.example.appnoticia.Fragments.Fragment_adote_um_pet;
import com.example.appnoticia.Models.Configuracao_firebase;
import com.example.appnoticia.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jspecify.annotations.NonNull;

public class Terceira_Activity extends AppCompatActivity {
    TextView user_name;
    Bundle dados;
    int show_fragment;
    FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();

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


        user_name = findViewById(R.id.txt_user_name);
        user_name.setText(auth.getCurrentUser().getDisplayName());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dados = getIntent().getExtras();
        show_fragment = dados.getInt("SHOW_FRAGMENT");


        addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu_tela_inicio, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if (id == R.id.item_sair) {

                    Toast.makeText(getApplicationContext(), "Usuario Deslogado", Toast.LENGTH_SHORT).show();
                    auth.signOut();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    return true;
                }
                return false;
            }
        }, this, Lifecycle.State.RESUMED);

        switch (show_fragment) {
            case 3:
                FragmentTransaction adote = getSupportFragmentManager().beginTransaction();
                adote.replace(R.id.frame_principal, new Fragment_adote_um_pet());
                adote.commit();
                break;
        }

    }
}