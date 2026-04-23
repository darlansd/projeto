package com.example.appnoticia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;

import androidx.core.view.MenuProvider;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Lifecycle;

import com.example.appnoticia.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jspecify.annotations.NonNull;

import java.util.List;

import com.example.appnoticia.Models.Configuracao_firebase;
import com.example.appnoticia.Models.Usuarios;

public class Activity_tela_inicio extends AppCompatActivity {
    LinearLayout l_perfil;
    TextView user_name;
    Button adote;
    Usuarios user = new Usuarios();
    List<Configuracao_firebase> dados;
    Toolbar toolbar;
    FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();

    @Override
    protected void onStart() {
        super.onStart();

        user_name.setText(auth.getCurrentUser().getDisplayName());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_inicio);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        user_name = findViewById(R.id.txt_user_name);
        user_name.setText(auth.getCurrentUser().getDisplayName());

        l_perfil = findViewById(R.id.linear_perfil);
        l_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Activity_perfil.class));
            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        adote = findViewById(R.id.btn_adote);


        //funçoes que usam o MenuProvider api
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


        adote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Terceira_Activity.class);
                intent.putExtra("SHOW_FRAGMENT", 3);
                startActivity(intent);

            }
        });

    }

}