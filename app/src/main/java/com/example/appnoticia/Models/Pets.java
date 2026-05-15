package com.example.appnoticia.Models;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.firebase.database.DatabaseReference;

public class Pets {
    DatabaseReference fireref = Configuracao_firebase.getfirebasedatabase();
    String NOME, RACA, SEXO, DESCRIÇAO, CASTRADO, VERMIFUGADO;
    int IDADE;

    // FOTO


    public void uploadtodatabase() {
        DatabaseReference pets_Por_Usuario = fireref.child("PETS-POR-USUARIO").child(Configuracao_firebase.getfirebaseUser().getUid()).push();
        DatabaseReference pets_feed = fireref.child("PETS-FEED").push();

        pets_feed.setValue(this);
        pets_feed.child("UID_USER").setValue(Configuracao_firebase.getfirebaseUser().getUid());
        pets_Por_Usuario.setValue(this);
    }
    public Pets(){}

    public Pets(String nome_pet, String raca_pet, String sexo_pet, String descricao_pet, int idade_pet, String castrado, String vermifugado) {
        setNOME(nome_pet);
        setRACA(raca_pet);
        setSEXO(sexo_pet);
        setDESCRIÇAO(descricao_pet);
        setIDADE(idade_pet);
        setCASTRADO(castrado);
        setVERMIFUGADO(vermifugado);
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getRACA() {
        return RACA;
    }

    public void setRACA(String RACA) {
        this.RACA = RACA;
    }

    public String getSEXO() {
        return SEXO;
    }

    public void setSEXO(String SEXO) {
        this.SEXO = SEXO;
    }

    public String getDESCRIÇAO() {
        return DESCRIÇAO;
    }

    public void setDESCRIÇAO(String DESCRIÇAO) {
        this.DESCRIÇAO = DESCRIÇAO;
    }

    public int getIDADE() {
        return IDADE;
    }

    public void setIDADE(int IDADE) {
        this.IDADE = IDADE;
    }

    public String getCASTRADO() {
        return CASTRADO;
    }

    public void setCASTRADO(String CASTRADO) {
        this.CASTRADO = CASTRADO;
    }

    public String getVERMIFUGADO() {
        return VERMIFUGADO;
    }

    public void setVERMIFUGADO(String VERMIFUGADO) {
        this.VERMIFUGADO = VERMIFUGADO;
    }
}