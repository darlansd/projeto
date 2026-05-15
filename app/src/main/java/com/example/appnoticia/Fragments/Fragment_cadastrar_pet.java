package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
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

import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;


public class Fragment_cadastrar_pet extends Fragment {
    EditText edt_nome_pet, edt_raca_pet, edt_idade_pet, edt_desc_pet;
    RadioButton rb_macho, rb_femea;
    RadioGroup rg_M_ou_F;
    CheckBox cb_castrado, cb_vermifugado;
    AppCompatButton btn_enviar;
    String nome_pet,raca_pet,idade_pet,desc_pet,sexo,castrado,vermifugado;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_pet, container, false);

        edt_nome_pet = view.findViewById(R.id.edt_nome_pet);
        edt_raca_pet = view.findViewById(R.id.edt_raca_pet);
        edt_idade_pet = view.findViewById(R.id.edt_idade_pet);
        edt_desc_pet = view.findViewById(R.id.edt_desc_pet);
        rb_macho = view.findViewById(R.id.radioButton);
        rb_femea = view.findViewById(R.id.radioButton2);
        rg_M_ou_F = view.findViewById(R.id.radioGroup);
        cb_castrado = view.findViewById(R.id.checkBox);
        cb_vermifugado = view.findViewById(R.id.checkBox2);
        btn_enviar = view.findViewById(R.id.btn_cadastrar_pet);



        //validar permissoes



        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar_cadastrar_pet();

            }
        });




        return view;
    }

    private void validar_cadastrar_pet(){
        nome_pet = p_string(edt_nome_pet);
        raca_pet = p_string(edt_raca_pet);
        idade_pet = p_string(edt_idade_pet);
        desc_pet = p_string(edt_desc_pet);
        sexo = rb_macho.isChecked() ? "Macho" : "Fêmea";
        castrado = cb_castrado.isChecked() ? "Castrado" : "Nao Castrado";
        vermifugado =cb_vermifugado.isChecked() ? "Vermifugado" : "Nao Vermifugado";


        if(!nome_pet.isEmpty()){
            if (!raca_pet.isEmpty()){
                if (!idade_pet.isEmpty()){
                    if (!desc_pet.isEmpty()){
                        if (!sexo.isEmpty()){

                            Pets pets = new Pets(nome_pet,raca_pet,sexo,desc_pet,Integer.parseInt(idade_pet),castrado,vermifugado);
                            pets.uploadtodatabase();

                        }else {Toast.makeText(getContext(),"Defina Um Sexo ao Pet",Toast.LENGTH_SHORT).show();}
                    }else{Toast.makeText(getContext(),"Fale Um Pouco Sobre Seu Pet",Toast.LENGTH_SHORT).show();}
                }else{Toast.makeText(getContext(),"Preecha a Idade Do Pet",Toast.LENGTH_SHORT).show();}
            }else{Toast.makeText(getContext(),"Preecha a Raça Do Pet",Toast.LENGTH_SHORT).show();}
        }else{Toast.makeText(getContext(),"Preecha o Nome Do Pet",Toast.LENGTH_SHORT).show();}

    }
    private String p_string(EditText edt){
        String str = edt.getText().toString();
        return str;
    }
}