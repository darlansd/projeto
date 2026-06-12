package com.example.appnoticia.Models;

import android.widget.Toast;

import com.example.appnoticia.Config.API;
import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Ceps {
    private String cep;
    private int cepUSer;

    private int Cep;
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
