package com.example.appnoticia.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.appnoticia.Adapter.Adapter;
import com.example.appnoticia.Config.API_ceps;
import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Models.Ceps;
import com.example.appnoticia.Models.Foto_perfis;
import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_editar_informacoes_pessoais extends Fragment {
    String novo_nome, novo_cep, cidade, bairro;
    TextInputEditText edt_novo_nome, edt_novo_cep;
    TextInputLayout layout_novo_cep, layout_novo_nome;
    AppCompatButton alterar;
    ShapeableImageView imagem_perfil;
    boolean finalizar, campo_cep, campo_nome = false;
    List<Pets> dados;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_informacoes_pessoais, container, false);

        layout_novo_nome = view.findViewById(R.id.layout_novo_nome);
        edt_novo_nome = view.findViewById(R.id.edt_novo_nome);
        layout_novo_cep = view.findViewById(R.id.layout_novo_cep);
        edt_novo_cep = view.findViewById(R.id.edt_novo_cep);
        edt_novo_cep.setText(null);
        alterar = view.findViewById(R.id.btn_alterar);
        imagem_perfil = view.findViewById(R.id.img_imagem_perfil);
        edt_novo_nome.setText(Configuracao_firebase.getfirebaseUser().getDisplayName());


        imagem_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
                View mview = getLayoutInflater().inflate(R.layout.placeholder_imagens_perfil,null);
                alertDialog.setTitle("Escolha Seu Avatar Adopet");

                alertDialog.setPositiveButton("definir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setView(mview);
                alertDialog.create().show();
            }
        });
        Glide.with(imagem_perfil).load(Foto_perfis.getFotoAtual() ).placeholder(R.drawable.placeholder_img_perfil).into(imagem_perfil);

        //Configuracao_firebase.getfirebasedatabase().child("IMAGENS PERFIL").addValueEventListener();

        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("cidade").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    cidade = snapshot.getValue().toString();
                    edt_novo_cep.setHint(cidade);
                } else {
                    Log.i("get cidade", "leitado");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("String cidade", error.getMessage());
            }
        });

        Configuracao_firebase.getfirebasedatabase().child("USUARIOS").child(Usuarios.getUid()).child("bairro").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    bairro = snapshot.getValue().toString() + " - " + edt_novo_cep.getHint().toString();
                    edt_novo_cep.setHint(bairro);
                } else {
                    Log.i("get bairro", "leitado");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("String bairro", error.getMessage());
            }
        });

        //fazer o botao confirmar aparecer para o campo nome
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
                    campo_nome = true;
                    layout_novo_nome.setCounterEnabled(true);
                } else {
                    novo_nome = Configuracao_firebase.getfirebaseUser().getDisplayName();
                    layout_novo_nome.setCounterEnabled(false);
                    campo_nome = false;
                    alterar.setVisibility(View.GONE);
                }
            }
        });
        //fazer o botao confirmar aparecer para o campo novo-cep
        //todo saber porque o campo de cep nao some o counter quando o campo esta vazio
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
                    campo_cep = true;
                    layout_novo_cep.setCounterEnabled(true);
                } else {
                    layout_novo_cep.setCounterEnabled(false);
                    campo_cep = false;
                    alterar.setVisibility(View.GONE);
                }
            }
        });

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validaçao campo nome
                if (!campo_nome) {
                    Log.i("Sting novo_nome", "null");
                } else {
                    finalizar = true;
                    Usuarios.atualizarnome(novo_nome);
                }


                //validaçao campo endereço(cep)
                if (!campo_cep) {
                    Log.i("Sting novo_cep", "null");
                } else {
                    novo_cep = edt_novo_cep.getText().toString().trim();

                    //todo pensar em um jeito de fazer todo essa configuraçao ficar em uma das models class pra assim conseguir recuperar de qualquer lugar

                    // instaciei a biblioteca
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://viacep.com.br/ws/").addConverterFactory(GsonConverterFactory.create()).build();
                    // instaciei a helper class
                    API_ceps api = retrofit.create(API_ceps.class);
                    //usei o metodo call pra me retornar o objeto ceps
                    Call<Ceps> call = api.getceps(novo_cep);

                    call.enqueue(new Callback<Ceps>() {
                        @Override
                        public void onResponse(Call<Ceps> call, Response<Ceps> response) {
                            if (response.isSuccessful()) {
                                Ceps cep = response.body();
                                Ceps.uploadlocalizacao(cep.getBairro(), cep.getCidade(), novo_cep);
                            }
                        }

                        @Override
                        public void onFailure(Call<Ceps> call, Throwable t) {
                            Toast.makeText(getContext(), "Erro Fatal no Servidor", Toast.LENGTH_SHORT).show();
                        }
                    });
                    finalizar = true;
                }

                if (finalizar) {
                    Toast.makeText(getContext(), "Informações Atualizadas", Toast.LENGTH_SHORT).show();
                    requireActivity().finish();
                }

            }
        });


        return view;
    }
}