package Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.appnoticia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Fragment_cadastro extends Fragment {

    EditText email, senha;

    Button continuar;

    FirebaseAuth usuario = FirebaseAuth.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        email = view.findViewById(R.id.edt_EmailAddress);
        senha = view.findViewById(R.id.edt_password);


        continuar = view.findViewById(R.id.btn_continuar);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email_recuperado = email.getText().toString();
                String senha_recuperada = senha.getText().toString();

                usuario.createUserWithEmailAndPassword(email_recuperado,senha_recuperada)

                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast kaka = Toast.makeText(getContext(),"cadastro funcionou",Toast.LENGTH_SHORT);
                            kaka.show();

                            Log.i("create user","funcionou");

                        }else{
                            Toast kaka = Toast.makeText(getContext(),"cadastro falhou",Toast.LENGTH_SHORT);
                            kaka.show();

                            Log.i("create user","Nao funcionou");
                        }

                    }
                });


            }
        });


        return view;
    }
}