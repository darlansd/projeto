package com.example.appnoticia.Models;

public class Foto_perfis {

    String link;
    private static String fotoAtual;

    public Foto_perfis(String link){

    }

    public static String getFotoAtual() {

        fotoAtual = "https://pqghcqdtuaeupdlrdbyu.supabase.co/storage/v1/object/public/Adopet/windows-11-preto.jpg";

        return fotoAtual;
    }

    public String getLink() {
        return link;
    }
}
