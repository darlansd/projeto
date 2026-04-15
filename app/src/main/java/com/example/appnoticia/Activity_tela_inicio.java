package com.example.appnoticia;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
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
import androidx.lifecycle.LifecycleOwner;
import com.google.firebase.auth.FirebaseAuth;
import org.jspecify.annotations.NonNull;
import java.util.List;
import Models.Configuracao_firebase;
import Models.Usuarios;

public class Activity_tela_inicio extends AppCompatActivity  {

   TextView text ;
   Usuarios user = new Usuarios();
   List<Configuracao_firebase> dados;
   FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_inicio);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu_tela_inicio, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();

                if(id == R.id.item_sair){
                    Toast.makeText(getApplicationContext(),"fodase",Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }


        },this, Lifecycle.State.RESUMED);

    }

}