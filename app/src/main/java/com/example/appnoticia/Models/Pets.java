package com.example.appnoticia.Models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Pets {
    DatabaseReference fireref = Configuracao_firebase.getfirebasedatabase();
    String nome,raca,sexo,bairro,cidade,descriçao;
    Boolean castrado,vermifugado;

    // FOTO


    public void uploadtodatabase(){
        DatabaseReference oloco = fireref.child("Pets").child(Configuracao_firebase.getfirebaseUser().getUid()).push();
        oloco.setValue(this);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getDescriçao() {
        return descriçao;
    }

    public void setDescriçao(String descriçao) {
        this.descriçao = descriçao;
    }

    public Boolean getCastrado() {
        return castrado;
    }

    public void setCastrado(Boolean castrado) {
        this.castrado = castrado;
    }

    public Boolean getVermifugado() {
        return vermifugado;
    }

    public void setVermifugado(Boolean vermifugado) {
        this.vermifugado = vermifugado;
    }
}
