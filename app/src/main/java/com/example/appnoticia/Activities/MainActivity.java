package com.example.appnoticia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appnoticia.R;
import com.google.firebase.auth.FirebaseAuth;

import com.example.appnoticia.Models.Configuracao_firebase;
import com.example.appnoticia.Models.Usuarios;

public class MainActivity extends AppCompatActivity {

    TextView name_confirmation;
    Button btn_cadastrar,btn_entrar;
    Usuarios user = new Usuarios();
    FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_cadastrar = findViewById(R.id.btn_casdastrar);
        btn_entrar = findViewById(R.id.btn_entrar);
        name_confirmation = findViewById(R.id.textView5);

        //tentando verificar se ha algum usario logado
        if(auth.getCurrentUser() != null){
            //usario logado



        }else{
            //ninguem logado
            Toast.makeText(this,"nao logado",Toast.LENGTH_SHORT).show();
        }

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //tentando fazer com que se um usuario tiver logado nao faça o login

                if (auth.getCurrentUser() != null) {

                    Toast.makeText(getApplicationContext(),"voce ja tava logado " + user.getNome(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), Activity_tela_inicio.class));

                }else{
                    Intent intent = new Intent(getApplicationContext(), Segunda_activity.class);
                    intent.putExtra("SHOW_FRAGMENT",1);
                    startActivity(intent);
                }

            }
        });

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Segunda_activity.class);
                intent.putExtra("SHOW_FRAGMENT", 0);
                startActivity(intent);


            }
        });

    }
}