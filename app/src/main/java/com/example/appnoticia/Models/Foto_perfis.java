package com.example.appnoticia.Models;

public class Foto_perfis {

    String link;
    private static String fotoAtual;

    public Foto_perfis(){

    }

    public static String getFotoAtual() {

        fotoAtual = "https://pqghcqdtuaeupdlrdbyu.supabase.co/storage/v1/object/public/Adopet/padrao.jpg";

        return fotoAtual;
    }

    public String getLink() {
        return link;
    }
}
