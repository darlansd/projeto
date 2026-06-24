package com.example.appnoticia.body;

public class Create_Bucket_Body {

    //todo fazer o allowed_mime_types aceitar mais de 1 valor
    String name;
    String[] allowed_mime_types;
    int file_size_limit;

    //todo fazer com que eu consiga fazer o body definir o bucket ser publico
    Boolean ispublic;

    public Create_Bucket_Body(String name, int file_size_limit, String... allowed_mime_types){
        this.name = name;
        this.allowed_mime_types = allowed_mime_types;
        this.file_size_limit = file_size_limit;
    }

}
