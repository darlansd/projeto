package com.example.appnoticia.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Usuarios {
    private static String uid, nome, email, senha;
    private static DatabaseReference child_nome;

    //tenho que fazer com que o get uid seja dinamico para o child_nome sempre estar atualizado

    //serve para caso ocorra uma troca de usuario | o header do NavigationDrawer estar Atualizado | comparar se o getdisplayname confere com o valor do nó nome | caso nao
    public static DatabaseReference getChild_nome() {

        String displayName = Configuracao_firebase.getfirebaseUser().getDisplayName();

        if (child_nome == null || !displayName.equals(nome)) {

            child_nome = FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(getUid()).child("NOME");
        }

        //captura o valor do nó nome |
        child_nome.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                nome = snapshot.getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return child_nome;
    }


    public static String getUid() {
        if (uid == null || !uid.equals(Configuracao_firebase.getfirebaseUser().getUid())) {

            uid = Configuracao_firebase.getfirebaseUser().getUid();
        }
        return uid;
    }

    public Usuarios() {
    }


    public static void uploadtodatabase() {

        Usuarios user = new Usuarios();

        //nesse atributo eu passo um metodo de criar 2 nós no realtime database
        DatabaseReference salvar_dados_usuario = Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(getUid());

        //e dentro do ultimo nó (uid) salvo os atributos dessa classe (nome, email)
        salvar_dados_usuario.child("NOME").setValue(user.getNome());
        salvar_dados_usuario.child("E-MAIL").setValue(user.getEmail());

    }

    public static boolean atualizarnome(String nome) {

        try {
            //aqui eu faço referencia ate o nó nome e troco o valor pelo parametro
            Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(getUid()).child("NOME").setValue(nome);

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
