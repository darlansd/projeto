package com.example.appnoticia.Models;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.annotations.SerializedName;

public class Ceps {
    private int cepUSer;

    private int Cep;
    //localidade,estado
    @SerializedName("localidade")
    private String cidade;
    @SerializedName("bairro")
    private String bairro;

    public static void uploadrua(String bairro, String cidade) {
        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("BAIRRO").setValue(bairro);
        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("CIDADE").setValue(cidade);
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return Cep;
    }

    public void setCep(int cep) {
        Cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
