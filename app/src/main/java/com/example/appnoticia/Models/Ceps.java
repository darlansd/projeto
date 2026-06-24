package com.example.appnoticia.Models;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.gson.annotations.SerializedName;

public class Ceps {
    //localidade,estado
    @SerializedName("localidade")
    private String cidade;
    @SerializedName("bairro")
    private String bairro;

    public static void uploadlocalizacao(String bairro, String cidade,String cep) {
        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("bairro").setValue(bairro);
        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("cidade").setValue(cidade);
        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("cep").setValue(cep);
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
}
