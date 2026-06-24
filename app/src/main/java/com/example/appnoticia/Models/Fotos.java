package com.example.appnoticia.Models;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.gson.annotations.SerializedName;

public class Fotos {
    @SerializedName("name")
    String nome_Foto;
    @SerializedName("mimetype")
    String tipo_Foto;

    public static void upload_status(String status) {
        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("status").setValue(status);

    }

    public String getNome_Foto() {
        return nome_Foto;
    }

    public void setNome_Foto(String nome_Foto) {
        this.nome_Foto = nome_Foto;
    }

    public String getTipo_Foto() {
        return tipo_Foto;
    }

    public void setTipo_Foto(String tipo_Foto) {
        this.tipo_Foto = tipo_Foto;
    }
}
