package com.example.appnoticia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appnoticia.Models.Configuracao_firebase;
import com.example.appnoticia.R;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_perfil extends AppCompatActivity {
    TextView user_name,editar_perfil;
    FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();


    @Override
    protected void onResume() {
        super.onResume();

        user_name.setText(auth.getCurrentUser().getDisplayName());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        user_name = findViewById(R.id.txt_user_name);
        user_name.setText(auth.getCurrentUser().getDisplayName());

        editar_perfil = findViewById(R.id.txt_editar_perfil);
        editar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Segunda_activity.class);
                intent.putExtra("SHOW_FRAGMENT", 4);
                startActivity(intent);
            }
        });

    }
}