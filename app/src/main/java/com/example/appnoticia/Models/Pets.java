package com.example.appnoticia.Models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Pets {
    private String NOME, RACA, SEXO, DESCRIÇAO, CASTRADO, VERMIFUGADO,IDADE,PORTE,VACINADO,CUIDADOS_ESP,DOCIL,CALMO,AGRESSIVO,BRINCALHAO,ARISCO,INDEP,especie;
    // FOTO
    public Pets(String nome_pet, String raca_pet, String sexo_pet, String descricao_pet, String idade_pet, String castrado, String vermifugado, String porte, String vacinado, String cuidados_esp, String docil, String calmo, String agressivo, String brincalhao, String arisco, String indep,String especie) {

        setNOME(nome_pet.trim());       setRACA(raca_pet.trim());             setSEXO(sexo_pet.trim());           setDESCRIÇAO(descricao_pet.trim());
        setIDADE(idade_pet);            setCASTRADO(castrado.trim());         setVERMIFUGADO(vermifugado.trim()); setPORTE(porte.trim());
        setVACINADO(vacinado.trim());   setCUIDADOS_ESP(cuidados_esp.trim()); setDOCIL(docil.trim());             setCALMO(calmo.trim());
        setAGRESSIVO(agressivo.trim()); setBRINCALHAO(brincalhao.trim());     setARISCO(arisco.trim());           setINDEP(indep.trim());
        setEspecie(especie.trim());
    }
    public static void delete_ppu(String path, String value) {
        DatabaseReference ppu = Configuracao_firebase.getfirebasedatabase().child("PETS-POR-USUARIO").child(Usuarios.getUid());

        //sempre que eu quiser fazer pesquisas dados no firebase preciso ordenar primeiro para depois pesquisar
        Query child_nome = ppu.orderByChild(path).equalTo(value);

        child_nome.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Log.i("snapshot ppu", snapshot.getValue().toString());

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
    public static void delete_pf(Context context,String path, String value) {
        DatabaseReference pf = Configuracao_firebase.getfirebasedatabase().child("PETS-FEED");

        //sempre que eu quiser fazer pesquisas dados no firebase preciso ordenar primeiro para depois pesquisar
        Query child_nome = pf.orderByChild(path).equalTo(value);

        child_nome.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Log.i("snapshot pf", snapshot.getValue().toString());

                    for (DataSnapshot kaka : snapshot.getChildren()) {
                        String key_pf = kaka.getKey();
                        pf.child(key_pf).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(context,"Postagem de" +value+ "Excluida Com Sucesso!",Toast.LENGTH_SHORT).show();
                                //todo: estudar sobre maneiras de atualizar o fragment apartir de uma helper class
                            }
                        });
                    }
                } else {Log.i("snapshot pf", "NULL");}
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

    }

    public void uploadtodatabase() {
        //DatabaseReference pets_por_user = Configuracao_firebase.getfirebasedatabase().child("PETS-POR-USUARIO").child(Usuarios.getUid()).push();
        DatabaseReference pets_feed = Configuracao_firebase.getfirebasedatabase().child("PETS-FEED").push();

        pets_feed.setValue(this);
        pets_feed.child("UID_USER").setValue(Usuarios.getUid());
        //pets_por_user.setValue(this);
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public Pets() {}
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

    public String getIDADE() {
        return IDADE;
    }

    public void setIDADE(String IDADE) {
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
    public String getPORTE() {
        return PORTE;
    }

    public void setPORTE(String PORTE) {
        this.PORTE = PORTE;
    }

    public String getVACINADO() {
        return VACINADO;
    }

    public void setVACINADO(String VACINADO) {
        this.VACINADO = VACINADO;
    }

    public String getCUIDADOS_ESP() {
        return CUIDADOS_ESP;
    }

    public void setCUIDADOS_ESP(String CUIDADOS_ESP) {
        this.CUIDADOS_ESP = CUIDADOS_ESP;
    }

    public String getDOCIL() {
        return DOCIL;
    }

    public void setDOCIL(String DOCIL) {
        this.DOCIL = DOCIL;
    }

    public String getCALMO() {
        return CALMO;
    }

    public void setCALMO(String CALMO) {
        this.CALMO = CALMO;
    }

    public String getAGRESSIVO() {
        return AGRESSIVO;
    }

    public void setAGRESSIVO(String AGRESSIVO) {
        this.AGRESSIVO = AGRESSIVO;
    }

    public String getBRINCALHAO() {
        return BRINCALHAO;
    }

    public void setBRINCALHAO(String BRINCALHAO) {
        this.BRINCALHAO = BRINCALHAO;
    }

    public String getARISCO() {
        return ARISCO;
    }

    public void setARISCO(String ARISCO) {
        this.ARISCO = ARISCO;
    }

    public String getINDEP() {
        return INDEP;
    }

    public void setINDEP(String INDEP) {
        this.INDEP = INDEP;
    }
}