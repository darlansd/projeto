package Fragments;

import android.content.Intent;
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

import com.example.appnoticia.Activity_tela_inicio;
import com.example.appnoticia.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

import Models.Configuracao_firebase;
import Models.Usuarios;

public class Fragment_entrar extends Fragment {

    Button entrar;
    EditText edt_email, edt_senha;
    String email, senha;
    Usuarios user = new Usuarios();
    FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();
    DatabaseReference database = Configuracao_firebase.getfirebasedatabase();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entrar, container, false);

        edt_email = view.findViewById(R.id.edt_addresemail);
        edt_senha = view.findViewById(R.id.edt_Senha);
        entrar = view.findViewById(R.id.btn_entrar);


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = edt_email.getText().toString();
                senha = edt_senha.getText().toString();

                Toast.makeText(getContext(), "Entrando ...", Toast.LENGTH_SHORT).show();

                if (auth.getCurrentUser() != null) {

                    Toast.makeText(getContext(), "usuario ja esta logado", Toast.LENGTH_SHORT).show();

                } else {
                    validarLogin();
                    logar();
                }

            }
        });


        return view;
    }

    private void validarLogin(){

    }
    private void logar() {

        user.setEmail(email);
        user.setSenha(senha);

        auth.signInWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(getContext(), "bem vindo de volta ", Toast.LENGTH_SHORT).show();

                    Intent tela_inicio = new Intent(getContext(), Activity_tela_inicio.class);
                    startActivity(tela_inicio);
                    getActivity().finish();

                } else {
                    String execao = "";

                    try {
                        throw (task.getException());
                    } catch (FirebaseAuthInvalidUserException e) {
                        execao = "Email Nao existe no Banco De Dados";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        execao = "Senha Errada";
                    } catch (Exception e) {
                        execao = "Erro ao Logar" + e.getMessage();
                    }

                    Toast.makeText(getContext(), execao, Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}