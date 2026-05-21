package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fragment_editar_informacoes_pessoais extends Fragment {
    String novo_nome;
    EditText edt_novo_nome;
    AppCompatButton alterar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_informacoes_pessoais, container, false);


        edt_novo_nome = view.findViewById(R.id.edt_novo_nome);
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

               if (!novo_nome.equals(Configuracao_firebase.getfirebaseUser().getDisplayName())){
                    alterar.setVisibility(View.VISIBLE);
               }else {
                    alterar.setVisibility(View.GONE);
               }
            }
        });

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuarios.atualizarnome(novo_nome);
                requireActivity().finish();
                Toast.makeText(getContext(), "Nome Atualizado", Toast.LENGTH_SHORT).show();

                //edt_novo_nome.setError("Campo Obrigatorio");
                //edt_novo_nome.setHintTextColor(-65536);
                //Toast.makeText(getContext(), "preencha o nome", Toast.LENGTH_SHORT).show();


            }
        });


        return view;
    }
}