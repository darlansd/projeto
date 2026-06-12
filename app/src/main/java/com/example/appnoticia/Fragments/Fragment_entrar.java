package com.example.appnoticia.Fragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Models.Usuarios;

public class Fragment_entrar extends Fragment {

    Button entrar;
    TextInputEditText edt_email, edt_senha;
    TextInputLayout layout_email, layout_senha;
    String email, senha;
    Usuarios user = new Usuarios();
    FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();
    TextView criar_conta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entrar, container, false);

        layout_email = view.findViewById(R.id.layout_email);
        edt_email = view.findViewById(R.id.edt_addresemail);
        layout_senha = view.findViewById(R.id.layout_senha);
        edt_senha = view.findViewById(R.id.edt_Senha);
        entrar = view.findViewById(R.id.btn_entrar);
        criar_conta = view.findViewById(R.id.txt_criar_conta);


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (auth.getCurrentUser() != null) {

                    Toast.makeText(getContext(), "usuario ja esta logado", Toast.LENGTH_SHORT).show();

                } else {
                    validar_logar();
                }

            }
        });

        criar_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.frame_principal, new Fragment_cadastro()).commit();
                //todo: fazer ter uma animaçao melhor
            }
        });


        return view;
    }

    private void validar_logar() {

        email = edt_email.getText().toString();
        senha = edt_senha.getText().toString();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {

                Toast.makeText(getContext(), "Entrando ...", Toast.LENGTH_SHORT).show();

                user.setEmail(email);
                user.setSenha(senha);

                logar();


            } else {
                layout_senha.setHelperText("Obrigatorio*");
                Toast.makeText(getContext(), "Preecha o Campo De Senha", Toast.LENGTH_SHORT).show();
            }
        } else {
            layout_email.setHelperText("Obrigatorio*");
            Toast.makeText(getContext(), "Preecha o Campo De Email", Toast.LENGTH_SHORT).show();
        }

    }

    private void logar() {

        auth.signInWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(getContext(), "bem vindo de volta " + Configuracao_firebase.getfirebaseUser().getDisplayName(), Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getContext(), Navigation_drawer_Activity.class));
                    getActivity().finish();

                } else {
                    String execao = "";

                    try {//vai tentar capturar as exeçoes que o objeto task pode lançar
                        throw (task.getException());
                    } catch (FirebaseAuthInvalidUserException e) {
                        execao = "Email Nao Cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        execao = "Email ou Senha Esta Errado";
                    } catch (Exception e) {
                        execao = "Erro ao Logar" + e.getMessage();

                        //printa o erro no log
                        e.printStackTrace();
                    }

                    Toast.makeText(getContext(), execao, Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}