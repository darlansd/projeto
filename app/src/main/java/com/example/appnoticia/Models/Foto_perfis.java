package com.example.appnoticia.Models;

import androidx.annotation.NonNull;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Foto_perfis {

    String link;
    private static String fotoAtual;

    public Foto_perfis() {

    }

    //todo fazer com que o usuario consiga alterar o valor dessa variavel
    public static String getFotoAtual() {
        try {
            if (fotoAtual == null) {
                Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("foto-perfil").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {

                            fotoAtual = snapshot.getValue().toString();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fotoAtual;
    }


    public String getLink() {
        return link;
    }
}
