package com.example.appnoticia.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Usuarios {

    private String uid, nome, email, senha;
    DatabaseReference refFire,salvar_dados_usuario;



    public Usuarios() {
    }

    public void uploadtodatabase(){

        refFire = Configuracao_firebase.getfirebasedatabase();

        //nesse atributo eu passo um metodo de criar 2 nós no realtime database
        salvar_dados_usuario = refFire.child("USUARIOS").child(getUid());

        //e dentro do ultimo nó (uid) salvo os atributos dessa classe (nome, email)
        salvar_dados_usuario.child("NOME").setValue(this.nome);
        salvar_dados_usuario.child("E-MAIL").setValue(this.email);

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
