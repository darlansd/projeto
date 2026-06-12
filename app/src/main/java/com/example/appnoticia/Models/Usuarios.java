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
    //todo fazer com que um novo usuario nao tenha o nome nulo
    private String email, senha, bairro, cidade;
    private static String uid,nome;
    private static DatabaseReference child_nome;

    //tenho que fazer com que o get uid seja dinamico para o child_nome sempre estar atualizado

    //serve para caso ocorra uma troca de usuario | o header do NavigationDrawer estar Atualizado | comparar se o getdisplayname confere com o valor do nó nome | caso nao
    public static DatabaseReference getChild_nome() {

        String displayName = Configuracao_firebase.getfirebaseUser().getDisplayName();

        if (child_nome == null || !displayName.equals(nome)){

            child_nome = FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(getUid()).child("NOME");
        }

        //captura o valor do nó nome |
        child_nome.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    nome = snapshot.getValue().toString();
                }else {
                    Log.i("get_child", "erro");
                }
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
        salvar_dados_usuario.child("nome").setValue(user.getNome());
        salvar_dados_usuario.child("e-mail").setValue(user.getEmail());

    }

    public static boolean atualizarnome(String nome) {

        try {
            //aqui eu faço referencia ate o nó nome e troco o valor pelo parametro
            Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(getUid()).child("nome").setValue(nome);

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

    public static void setUid(String uid) {
        Usuarios.uid = uid;
    }
    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Usuarios.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
