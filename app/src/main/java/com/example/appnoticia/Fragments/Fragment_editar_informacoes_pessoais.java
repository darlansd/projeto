package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;

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
        edt_novo_nome.setHint(Configuracao_firebase.getfirebaseUser().getDisplayName());

        //validaçao so vai acontecer se o usuario digitar alguma coisa em algum campo
        //senao o botao nao habilita pra clickar

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                novo_nome = edt_novo_nome.getText().toString();

                if(!novo_nome.isEmpty()){
                    Usuarios.atualizarnome(novo_nome);
                    requireActivity().finish();
                }else{
                    Toast.makeText(getContext(),"preencha o nome",Toast.LENGTH_SHORT).show();
                }

            }
        });








        return view;
    }
}