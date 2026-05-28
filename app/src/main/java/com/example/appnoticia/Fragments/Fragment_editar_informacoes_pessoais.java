package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.appnoticia.Config.API;
import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Models.Ceps;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_editar_informacoes_pessoais extends Fragment {
    String novo_nome;
    String novo_cep;
    TextInputEditText edt_novo_nome, edt_novo_cep;
    AppCompatButton alterar;
    Ceps ceps = new Ceps();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_informacoes_pessoais, container, false);


        edt_novo_nome = view.findViewById(R.id.edt_novo_nome);
        edt_novo_cep = view.findViewById(R.id.edt_novo_cep);
        alterar = view.findViewById(R.id.btn_alterar);
        edt_novo_nome.setText(Configuracao_firebase.getfirebaseUser().getDisplayName());

        //validaçao so vai acontecer se o usuario digitar alguma coisa em algum campo
        //senao o botao nao habilita pra clickar


        edt_novo_nome.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                novo_nome = edt_novo_nome.getText().toString();

                if (!novo_nome.equals(Configuracao_firebase.getfirebaseUser().getDisplayName())) {
                    alterar.setVisibility(View.VISIBLE);
                } else {
                    alterar.setVisibility(View.GONE);
                }
            }
        });

        edt_novo_cep.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                novo_cep = edt_novo_cep.getText().toString().trim();

                if (!novo_cep.isEmpty()) {
                    alterar.setVisibility(View.VISIBLE);
                } else {
                    alterar.setVisibility(View.GONE);
                }
            }
        });

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novo_cep = edt_novo_cep.getText().toString().trim();
                if (!novo_cep.isEmpty()) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://viacep.com.br/ws/").addConverterFactory(GsonConverterFactory.create()).build();
                    API api = retrofit.create(API.class);
                    Call<Ceps> call = api.getceps(novo_cep);
                    call.enqueue(new Callback<Ceps>() {
                        @Override
                        public void onResponse(Call<Ceps> call, Response<Ceps> response) {
                            if(response.isSuccessful()){
                                Ceps cep = response.body();
                                ceps.uploadrua(cep.getlogradouro());
                            }
                        }

                        @Override
                        public void onFailure(Call<Ceps> call, Throwable t) {

                        }
                    });


                    /*
                    api.getceps(novo_cep).enqueue(new Callback<Ceps>() {
                        @Override
                        public void onResponse(Call<Ceps> call, Response<Ceps> response) {
                            if(response.isSuccessful()){
                                ceps.setlogradouro(response.body().getlogradouro());
                                ceps.uploadtodatabase();

                            }

                        }

                        @Override
                        public void onFailure(Call<Ceps> call, Throwable t) {

                        }
                    });

                     */

                    //ceps.setRua(api.getceps().toString());


                }else{
                    Toast.makeText(getContext(), "fudeu", Toast.LENGTH_SHORT).show();
                }
                //if(!novo_nome.isEmpty()){
                   // Usuarios.atualizarnome(novo_nome);
                  //  requireActivity().finish();
                   // Toast.makeText(getContext(), "Nome Atualizado", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
}