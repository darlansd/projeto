package com.example.appnoticia.Config;

import com.example.appnoticia.Models.Ceps;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface API_ceps {
    @GET("{cep}/json/")
    Call<Ceps> getceps(@Path("cep")String cep);
}
