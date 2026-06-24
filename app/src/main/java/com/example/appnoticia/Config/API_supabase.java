package com.example.appnoticia.Config;

import com.example.appnoticia.Models.Fotos;
import com.example.appnoticia.body.Fotos_List_body;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API_supabase {
    @Headers({"Authorization: bearereyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBxZ2hjcWR0dWFldXBkbHJkYnl1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzkzODA4ODQsImV4cCI6MjA5NDk1Njg4NH0.HgHfvIpJ6ry2C72qpHiWLWapEmtpYljGQhGHWM8ZCe0",
              "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBxZ2hjcWR0dWFldXBkbHJkYnl1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzkzODA4ODQsImV4cCI6MjA5NDk1Njg4NH0.HgHfvIpJ6ry2C72qpHiWLWapEmtpYljGQhGHWM8ZCe0"})

    @POST("storage/v1/object/list/Adopet")
    Call<List<Fotos>> getFotos(@Body Fotos_List_body prefix);

}
