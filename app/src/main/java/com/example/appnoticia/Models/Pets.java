package com.example.appnoticia.Models;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Pets {
    private String NOME, RACA, SEXO, DESCRIÇAO, CASTRADO, VERMIFUGADO;
    private int IDADE;
    // FOTO

    public static void delete_ppu(String path, String value) {
        DatabaseReference ppu = Configuracao_firebase.getfirebasedatabase().child("PETS-POR-USUARIO").child(Usuarios.getUid());

        //sempre que eu quiser fazer pesquisas dados no firebase preciso ordenar primeiro para depois pesquisar
        Query child_nome = ppu.orderByChild(path).equalTo(value);

        child_nome.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("snapshot ppu", snapshot.getValue().toString());
                if (snapshot.exists()) {
                    for (DataSnapshot kaka : snapshot.getChildren()) {
                        String key_ppu = kaka.getKey();
                        ppu.child(key_ppu).removeValue();
                    }
                } else {
                    Log.i("snapshot ppu", "NULL");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public static void delete_pf(String path, String value) {
        DatabaseReference pf = Configuracao_firebase.getfirebasedatabase().child("PETS-FEED");

        //sempre que eu quiser fazer pesquisas dados no firebase preciso ordenar primeiro para depois pesquisar
        Query child_nome = pf.orderByChild(path).equalTo(value);

        child_nome.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("snapshot pf", snapshot.getValue().toString());
                if (snapshot.exists()) {
                    for (DataSnapshot kaka : snapshot.getChildren()) {
                        String key_pf = kaka.getKey();
                        pf.child(key_pf).removeValue();
                    }
                } else {
                    Log.i("snapshot pf", "NULL");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public void uploadtodatabase() {
        DatabaseReference pets_por_user = Configuracao_firebase.getfirebasedatabase().child("PETS-POR-USUARIO").child(Usuarios.getUid()).push();
        DatabaseReference pets_feed = Configuracao_firebase.getfirebasedatabase().child("PETS-FEED").push();

        pets_feed.setValue(this);
        pets_feed.child("UID_USER").setValue(Usuarios.getUid());
        pets_por_user.setValue(this);
    }

    public Pets() {
    }

    public Pets(String nome_pet, String raca_pet, String sexo_pet, String descricao_pet, int idade_pet, String castrado, String vermifugado) {
        setNOME(nome_pet.trim());
        setRACA(raca_pet.trim());
        setSEXO(sexo_pet.trim());
        setDESCRIÇAO(descricao_pet.trim());
        setIDADE(idade_pet);
        setCASTRADO(castrado.trim());
        setVERMIFUGADO(vermifugado.trim());
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