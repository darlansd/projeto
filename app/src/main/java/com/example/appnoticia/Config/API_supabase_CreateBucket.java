package com.example.appnoticia.Config;

import com.example.appnoticia.Models.Fotos;
import com.example.appnoticia.body.Create_Bucket_Body;
import com.example.appnoticia.body.Fotos_List_body;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API_supabase_CreateBucket {
    @Headers({"Authorization: bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBxZ2hjcWR0dWFldXBkbHJkYnl1Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc3OTM4MDg4NCwiZXhwIjoyMDk0OTU2ODg0fQ.1vZFhhuSXPToJINHrdnfRU_i2p__i7qe_vaelGJQx9M",
              "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBxZ2hjcWR0dWFldXBkbHJkYnl1Iiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc3OTM4MDg4NCwiZXhwIjoyMDk0OTU2ODg0fQ.1vZFhhuSXPToJINHrdnfRU_i2p__i7qe_vaelGJQx9M"})

    @POST("storage/v1/bucket")
    Call<Void> postBucket(@Body Create_Bucket_Body body);

}
