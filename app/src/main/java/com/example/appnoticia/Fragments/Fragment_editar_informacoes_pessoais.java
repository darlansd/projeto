package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_editar_informacoes_pessoais extends Fragment {
    String novo_nome, novo_cep, cidade, bairro;
    TextInputEditText edt_novo_nome, edt_novo_cep;
    AppCompatButton alterar;
    boolean finalizar = false;
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

        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("CIDADE").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    cidade = snapshot.getValue().toString();
                }else{
                    Log.i("get cidade", "leitado");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("String cidade", error.getMessage());
            }
        });

        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("BAIRRO").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    bairro = snapshot.getValue().toString();
                } else {
                    Log.i("get bairro", "leitado");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("String bairro", error.getMessage());
            }
        });

        //todo descobrir porque as variaveis bairro e cidade estao recebendo nulo
        if (bairro != null && cidade != null) {
            edt_novo_cep.setHint(bairro + " - " + cidade);
        } else {
            Log.i("exibir bairro cidade", "leitado");
        }

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
                    novo_nome = Configuracao_firebase.getfirebaseUser().getDisplayName();
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
                if (edt_novo_cep != null) {
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

                //validaçao campo endereço(cep)
                if (!novo_cep.isEmpty()) {
                    // instaciei a biblioteca
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://viacep.com.br/ws/").addConverterFactory(GsonConverterFactory.create()).build();
                    // instaciei a helper class
                    API api = retrofit.create(API.class);
                    //usei o metodo call pra me retornar o objeto ceps
                    Call<Ceps> call = api.getceps(novo_cep);

                    call.enqueue(new Callback<Ceps>() {
                        @Override
                        public void onResponse(Call<Ceps> call, Response<Ceps> response) {
                            if (response.isSuccessful()) {
                                Ceps cep = response.body();
                                bairro = cep.getBairro();
                                cidade = cep.getCidade();
                                Ceps.uploadrua(cep.getBairro(), cep.getCidade());

                            }
                        }
                        @Override
                        public void onFailure(Call<Ceps> call, Throwable t) {
                            Toast.makeText(getContext(), "Erro Fatal no Servidor", Toast.LENGTH_SHORT).show();
                        }
                    });
                    finalizar = true;
                    Toast.makeText(getContext(), "Endereço Atualizado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "fudeu", Toast.LENGTH_SHORT).show();
                }

                //validaçao campo nome
                if (novo_nome == null) {
                    Log.i("Sting novo_nome", "null");
                } else if (novo_nome.equals(Configuracao_firebase.getfirebaseUser().getDisplayName())) {
                    Usuarios.atualizarnome(novo_nome);
                    finalizar = true;
                    Toast.makeText(getContext(), "Nome Atualizado", Toast.LENGTH_SHORT).show();
                }

                if (finalizar) {
                    requireActivity().finish();
                }

            }
        });


        return view;
    }
}