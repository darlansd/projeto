package com.example.appnoticia.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class Usuarios {

    private DatabaseReference child_nome = FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(Configuracao_firebase.getfirebaseUser().getUid()).child("NOME");

    private String uid, nome, email, senha;
    private DatabaseReference salvar_dados_usuario;

    public Usuarios() {
    }


    public static boolean atualizarnome(String nome) {

        try {
            //aqui eu faço referencia ate o nó nome e troco o valor pelo parametro
            Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Configuracao_firebase.getfirebaseUser().getUid()).child("NOME").setValue(nome);

            //alterar o nome dentro da variavel displayName
            FirebaseUser user = Configuracao_firebase.getfirebaseUser();
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(nome).build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (!task.isSuccessful()) {
                        Log.e("perfil", "erro ao atualizar o Nome do perfil");
                    }

                }
            });
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void uploadtodatabase() {

        //nesse atributo eu passo um metodo de criar 2 nós no realtime database
        salvar_dados_usuario = Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(getUid());

        //e dentro do ultimo nó (uid) salvo os atributos dessa classe (nome, email)
        salvar_dados_usuario.child("NOME").setValue(this.nome);
        salvar_dados_usuario.child("E-MAIL").setValue(this.email);

    }

    public DatabaseReference getChild_nome() {
        return child_nome;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude //anotaçao do propria firebase pra nao salvar no banco de dados
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
