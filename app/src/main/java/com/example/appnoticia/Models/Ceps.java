package com.example.appnoticia.Models;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.annotations.SerializedName;

public class Ceps {
    private int cepUSer;

    private int Cep;
    //Logradouro,bairro,localidade,estado

    private String Logradouro;

    public void uploadrua(String rua) {
        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("LOGRADOURO").setValue(rua);
    }

    public int getCep() {
        return Cep;
    }

    public void setCep(int cep) {
        Cep = cep;
    }

    public String getlogradouro() {
        return Logradouro;
    }

    public void setlogradouro(String rua) {
        this.Logradouro = Logradouro;
    }
}
