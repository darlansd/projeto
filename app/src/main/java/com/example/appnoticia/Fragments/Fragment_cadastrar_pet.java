package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.appnoticia.Activities.Segunda_activity;
import com.example.appnoticia.Config.API_supabase_CreateBucket;
import com.example.appnoticia.Config.TrocarFragment;
import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;
import com.example.appnoticia.body.Create_Bucket_Body;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Fragment_cadastrar_pet extends Fragment {
    AppCompatButton btn_gato,btn_cachorro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_pet, container, false);

        btn_gato = view.findViewById(R.id.btn_cadastrar_gato);
        btn_cachorro = view.findViewById(R.id.btn_cadastrar_cachorro);

        btn_gato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrocarFragment.intent_PutExtra(requireActivity(),getContext(), Segunda_activity.class,4);


                // todo metodo de criar bucket deve ser movido para sessão de criar conta
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pqghcqdtuaeupdlrdbyu.storage.supabase.co/").addConverterFactory(GsonConverterFactory.create()).build();
                API_supabase_CreateBucket api = retrofit.create(API_supabase_CreateBucket.class);
                Create_Bucket_Body body = new Create_Bucket_Body(Usuarios.getUid(), 10485760,"image/png","image/jpg");
                Call<Void> call = api.postBucket(body);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Log.i("Bucket", "criado");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("Bucket", "leitado");

                    }
                });
            }
        });

        btn_cachorro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrocarFragment.intent_PutExtra(requireActivity(),getContext(), Segunda_activity.class,5);
            }
        });
        return view;
    }


}