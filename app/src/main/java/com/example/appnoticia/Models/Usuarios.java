package com.example.appnoticia.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private String senha, bairro, cidade;
    private static String uid, nome, email;
    private static DatabaseReference child_nome;

    public Usuarios(String uid, String nome, String email) {
        Usuarios.uid = uid;
        Usuarios.nome = nome;
        Usuarios.email = email;
    }

    public Usuarios() {

    }

    //tenho que fazer com que o get uid seja dinamico para o child_nome sempre estar atualizado
    public static String getUid() {

        try {
            if (uid == null || !uid.equals(Configuracao_firebase.getfirebaseUser().getUid())) {

                uid = Configuracao_firebase.getfirebaseUser().getUid();
            }

        } catch (Exception e) {
            Log.i("getUID", e.getMessage());

        }
        return uid;
    }

    //todo esse metodo da um erro quando voce tentar criar 2 usuarios sem fechar o app antes
    //fazer uma validaçao melhor
    public static DatabaseReference getChild_nome(String uid) {

        if (child_nome == null) {

            child_nome = FirebaseDatabase.getInstance().getReference().child("USUARIOS").child(uid).child("nome");

        }

        return child_nome;
    }

    public static boolean atualizarEmail(String email) {

        try {
            Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(getUid()).child("e-mail").setValue(email);

            //todo descbrir uma maneira de trocar o email tanto no realtime quanto no auth quando trocar pra autenticaçao do google
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean atualizarnome(String nome) {

        try {

            //aqui eu faço referencia ate o nó nome e troco o valor pelo parametro
            Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(getUid()).child("nome").setValue(nome);

            //alterar o nome dentro da variavel displayName
            FirebaseUser user = Configuracao_firebase.getfirebaseUser();
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(nome).build();
            user.updateProfile(profile).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.i("perfil", "Nome De Perfil Atualizado");
                }
            });
            return true;

        } catch (Exception e) {
            Log.i("perfil", "erro ao atualizar o Nome do perfil");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean atualizarFoto(String foto){
        try{
            Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(getUid()).child("foto-perfil").setValue(foto);

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

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Usuarios.email = email;
    }
}
