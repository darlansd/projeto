package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.appnoticia.Activity_tela_inicio;
import com.example.appnoticia.MainActivity;
import com.example.appnoticia.R;
import com.example.appnoticia.Segunda_activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import Models.Configuracao_firebase;
import Models.Usuarios;

public class Fragment_cadastro extends Fragment {

    EditText edt_nome,edt_email, edt_senha, edt_conf_senha;
    Button continuar;
    String uid, nome, email, senha, senha_conf;
    Usuarios users = new Usuarios();
    FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        edt_nome = view.findViewById(R.id.edt_userName);
        edt_email = view.findViewById(R.id.edt_EmailAddress);
        edt_senha = view.findViewById(R.id.edt_password);
        edt_conf_senha = view.findViewById(R.id.edt_password_confirmation);

        continuar = view.findViewById(R.id.btn_continuar);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               validar_usuario();
               cadastrar_usuario();

            }
        });


        return view;
    }

        //tenho que fazer o metodo validar se os dois campos de senha foram validados e que todos os campos foram preechidos pra poder fazer o cadastro
        public void validar_usuario(){

            nome = edt_nome.getText().toString();
            email = edt_email.getText().toString();
            senha = edt_senha.getText().toString();
            senha_conf = edt_conf_senha.getText().toString();

            if(!nome.isEmpty()){
                if(!email.isEmpty()){
                    if(!senha.isEmpty()){

                        if(senha_conf.equals(senha)){

                            users.setNome(nome);
                            users.setEmail(email);
                            users.setSenha(senha);

                        }else{Toast kaka = Toast.makeText(getContext(),"as senhas precisao ser iguais",Toast.LENGTH_SHORT); kaka.show();}

                    }else{Toast kaka = Toast.makeText(getContext(),"as senhas precisao ser iguais",Toast.LENGTH_SHORT); kaka.show();}
                }else{Toast kaka = Toast.makeText(getContext(),"preecha o campo de email",Toast.LENGTH_SHORT); kaka.show();}
            }else{Toast kaka = Toast.makeText(getContext(),"preecha o seu nome",Toast.LENGTH_SHORT); kaka.show();}

        }
        public void cadastrar_usuario(){

            auth.createUserWithEmailAndPassword(users.getEmail(),users.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        users.setUid(task.getResult().getUser().getUid());
                        users.uploadtodatabase();

                        Intent tela_inicio = new Intent(getContext(), Activity_tela_inicio.class);
                        startActivity(tela_inicio);
                        getActivity().finish();;

                        Toast.makeText(getContext(),"cadastro concluido",Toast.LENGTH_SHORT).show();


                    }else{Toast.makeText(getContext(),"cadastro falhou",Toast.LENGTH_SHORT).show();}
                }
            });

        }

}