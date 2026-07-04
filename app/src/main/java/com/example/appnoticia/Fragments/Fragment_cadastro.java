package com.example.appnoticia.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appnoticia.R;
import com.example.appnoticia.navigation.Navigation_drawer_Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Models.Usuarios;

public class Fragment_cadastro extends Fragment {

    TextInputEditText edt_nome, edt_email, edt_senha, edt_conf_senha;
    TextInputLayout layout_senha, layout_conf;
    CheckBox checkBox;
    TextView termos_uso;
    Button continuar;
    String nome, email, senha, senha_conf;
    boolean termos_aceitos = false;
    Usuarios users = new Usuarios();
    FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        edt_nome = view.findViewById(R.id.edt_userName);
        edt_email = view.findViewById(R.id.edt_EmailAddress);
        layout_senha = view.findViewById(R.id.layout_senha);
        edt_senha = view.findViewById(R.id.edt_password);
        edt_conf_senha = view.findViewById(R.id.edt_password_confirmation);
        termos_uso = view.findViewById(R.id.txt_termos_uso);
        checkBox = view.findViewById(R.id.checkBox);
        continuar = view.findViewById(R.id.btn_continuar);

        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {
                termos_aceitos = true;
            } else {
                termos_aceitos = false;
            }
        });

        termos_uso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(requireContext());
                alerta.setTitle("Precisa Concordar Com os Termos De Uso");
                alerta.setMessage("aqui vao estar os termos de uso bem explicativos");
                alerta.setCancelable(false);
                alerta.setNegativeButton("Negar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        termos_aceitos = false;
                        checkBox.setChecked(termos_aceitos);
                    }
                });
                alerta.setPositiveButton("Aceitar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        termos_aceitos = true;
                        checkBox.setChecked(termos_aceitos);
                    }
                });
                alerta.create().show();
            }
        });


        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (termos_aceitos) {
                    validar_cadastrar_usuario();
                } else {
                    Toast.makeText(getContext(), "Voce Precisa Aceitar Os Termos De Uso", Toast.LENGTH_SHORT).show();
                }

            }

        });


        return view;
    }

    //tenho que fazer o metodo validar se os dois campos de senha foram validados e que todos os campos foram preechidos pra poder fazer o cadastro
    private void validar_cadastrar_usuario() {

        nome = edt_nome.getText().toString().trim();
        email = edt_email.getText().toString().trim();
        senha = edt_senha.getText().toString();
        senha_conf = edt_conf_senha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!senha.isEmpty()) {

                    if (senha_conf.equals(senha)) {

                        Toast.makeText(getContext(), "Cadastrando ...", Toast.LENGTH_SHORT).show();

                        Usuarios.setNome(nome);

                        users.setEmail(email);
                        users.setSenha(senha);

                        cadastrar_usuario(users.getEmail(), users.getSenha());

                    } else {
                        Toast.makeText(getContext(), "as senhas precisao ser iguais", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "as senhas precisao ser iguais", Toast.LENGTH_SHORT).show();
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

        boolean upload = Usuarios.uploadtodatabase();

        if (upload) {
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful() && Usuarios.getUid() != null) {

                        Usuarios.setUid(task.getResult().getUser().getUid());
                        Usuarios.atualizarnome(Usuarios.getNome());

                        startActivity(new Intent(getContext(), Navigation_drawer_Activity.class));
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

                        }
                        Toast.makeText(getContext(), execao, Toast.LENGTH_LONG).show();
                    }
                }
            });

        }else{
            Log.i("cadastro","Nao Foi Possivel Cadastrar");
            Toast.makeText(requireContext(), "Nao Foi Possivel Cadastrar", Toast.LENGTH_SHORT).show();
        }

    }

}