package com.example.appnoticia.Fragments;

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


import com.example.appnoticia.Activities.Activity_tela_inicio;
import com.example.appnoticia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import com.example.appnoticia.Models.Configuracao_firebase;
import com.example.appnoticia.Models.Usuarios;

public class Fragment_cadastro extends Fragment {

    EditText edt_nome, edt_email, edt_senha, edt_conf_senha;
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

                validar_cadastrar_usuario();


            }
        });


        return view;
    }

    //tenho que fazer o metodo validar se os dois campos de senha foram validados e que todos os campos foram preechidos pra poder fazer o cadastro
    private void validar_cadastrar_usuario() {

        nome = edt_nome.getText().toString();
        email = edt_email.getText().toString();
        senha = edt_senha.getText().toString();
        senha_conf = edt_conf_senha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!senha.isEmpty()) {

                    if (senha_conf.equals(senha)) {

                        Toast.makeText(getContext(), "Cadastrando ...", Toast.LENGTH_SHORT).show();

                        users.setNome(nome);
                        users.setEmail(email);
                        users.setSenha(senha);

                        cadastrar_usuario(users.getEmail(), users.getSenha());

                    } else {
                        Toast kaka = Toast.makeText(getContext(), "as senhas precisao ser iguais", Toast.LENGTH_SHORT);
                        kaka.show();
                    }

                } else {
                    Toast kaka = Toast.makeText(getContext(), "as senhas precisao ser iguais", Toast.LENGTH_SHORT);
                    kaka.show();
                }
            } else {
                Toast kaka = Toast.makeText(getContext(), "preecha o campo de email", Toast.LENGTH_SHORT);
                kaka.show();
            }
        } else {
            Toast kaka = Toast.makeText(getContext(), "preecha o seu nome", Toast.LENGTH_SHORT);
            kaka.show();
        }

    }
    private void cadastrar_usuario(String email, String senha) {

        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    users.setUid(task.getResult().getUser().getUid());
                    users.uploadtodatabase();

                    Usuarios.atualizarnome(users.getNome());

                    startActivity(new Intent(getContext(), Activity_tela_inicio.class));
                    getActivity().finish();

                    Toast.makeText(getContext(), "cadastro concluido", Toast.LENGTH_SHORT).show();


                } else {
                    String execao = "";

                    try { //vai tentar capturar as exeçoes que o objeto task pode lançar
                        throw (task.getException());
                    } catch (FirebaseAuthWeakPasswordException e) {
                        execao = "Digite Uma Senha Com Mais De 5 Caracteres";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        execao = "Digite o Email No Formato ***@gmail.com";
                    } catch (FirebaseAuthUserCollisionException e) {
                        execao = "Email Ja Cadastrado";
                    } catch (Exception e) {
                        execao = "Erro ao Cadastrar Usuário" + e.getMessage();

                        //printa o erro no log
                        e.printStackTrace();

                    }Toast.makeText(getContext(), execao, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}