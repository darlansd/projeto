package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.R;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Fragment_cadastrar_gato extends Fragment {
    NestedScrollView nested;
    TextInputEditText edt_nome_pet, edt_desc_pet;
    MaterialAutoCompleteTextView edt_raca_pet, edt_idade_pet,edt_porte_pet;
    RadioButton rb_macho;
    CheckBox cb_castrado, cb_vermifugado, cb_vacinado, cb_cuidados_esp, cb_docil, cb_agressivo, cb_ariso, cb_calmo, cb_brincalhao, cb_indep, cb_criancas, cb_crianca, cb_cachorros, cb_gatos, cb_apart, cb_casa;
    AppCompatButton btn_enviar;
    String nome_pet,raca_pet,idade_pet,porte,sexo,castrado,vermifugado,vacinado,cuidados_esp,docil,agressivo,arisco,calmo,brincalhao,indep,crianca,criancas,gatos,cachorros,apart,casa,desc_pet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_gato, container, false);

        nested = view.findViewById(R.id.nested);

        edt_nome_pet = view.findViewById(R.id.edt_nome_pet);
        edt_desc_pet = view.findViewById(R.id.edt_desc_pet);

        edt_raca_pet = view.findViewById(R.id.edt_raca_pet);
        edt_idade_pet = view.findViewById(R.id.edt_idade_pet);
        edt_porte_pet = view.findViewById(R.id.edt_porte_pet);

        rb_macho = view.findViewById(R.id.rb_macho);

        cb_castrado = view.findViewById(R.id.cb_castrado);
        cb_vermifugado = view.findViewById(R.id.cb_vermifugado);
        cb_vacinado = view.findViewById(R.id.cb_vacinado);
        cb_cuidados_esp = view.findViewById(R.id.cb_cuidados);
        cb_docil = view.findViewById(R.id.cb_docil);
        cb_agressivo = view.findViewById(R.id.cb_agressivo);
        cb_apart = view.findViewById(R.id.cb_apartamento);
        cb_ariso = view.findViewById(R.id.cb_arisco);
        cb_calmo = view.findViewById(R.id.cb_calmo);
        cb_brincalhao = view.findViewById(R.id.cb_brincalhao);
        cb_indep = view.findViewById(R.id.cb_independente);
        cb_crianca = view.findViewById(R.id.cb_crianca);
        cb_criancas = view.findViewById(R.id.cb_criancas);
        cb_gatos = view.findViewById(R.id.cb_gato);
        cb_cachorros = view.findViewById(R.id.cb_cachorros);
        cb_casa = view.findViewById(R.id.cb_casa);


        btn_enviar = view.findViewById(R.id.btn_cadastrar_pet);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validar_cadastrar_pet();

            }
        });

        return view;
    }

    private void validar_cadastrar_pet(){
        nome_pet = tiet_string(edt_nome_pet);
        desc_pet = tiet_string(edt_desc_pet);

        raca_pet = mact_string(edt_raca_pet);
        idade_pet = mact_string(edt_idade_pet);
        porte = mact_string(edt_porte_pet);

        sexo = rb_macho.isChecked() ? "Macho" : "Fêmea";
        castrado = cb_check(cb_castrado,"Castrado","Nao Castrado");
        vermifugado = cb_check(cb_vermifugado,"Vermifugado","Nao Vermifugado");
        vacinado = cb_check(cb_vacinado,"Vacinado","Nao Vacinado");
        cuidados_esp = cb_check(cb_cuidados_esp,"Precisa De Cuidados Especiais","Nao Precisa De Cuidados Especiais");
        docil = cb_check(cb_docil,"Docil","Não Docil");
        calmo = cb_check(cb_calmo,"Calma","Nao Calma");
        agressivo = cb_check(cb_agressivo,"Agressiva","Nao Agressivo");
        brincalhao = cb_check(cb_brincalhao,"Brincalhona","Nao Brincalhona");
        arisco = cb_check(cb_ariso,"Arisca","Nao Arisca");
        indep = cb_check(cb_indep,"Independente","Nao Independente");

        if(!nome_pet.isEmpty()){
            if (!raca_pet.isEmpty()){
                if (!idade_pet.isEmpty()){
                    if (!porte.isEmpty()){
                        if(!sexo.isEmpty()){
                            if (!desc_pet.isEmpty()){

                                Pets pets = new Pets(nome_pet,raca_pet,sexo,desc_pet,idade_pet,castrado,vermifugado,porte,vacinado,cuidados_esp,docil,calmo,agressivo,brincalhao,arisco,indep);
                                pets.uploadtodatabase();
                                requireActivity().finish();
                                Toast.makeText(getContext(),"Gato Cadastrado Com Sucesso",Toast.LENGTH_SHORT).show();

                            }else {edt_desc_pet.setError("Campo Obrigatorio");
                                Toast.makeText(getContext(),"Fale Um Pouco Sobre Seu Pet",Toast.LENGTH_SHORT).show();}
                        }else{Toast.makeText(getContext(),"Defina Um Sexo ao Pet",Toast.LENGTH_SHORT).show();}
                    }else{edt_porte_pet.setError("Campo Obrigatorio");
                    Toast.makeText(getContext(),"Defina o Porte Do Pet",Toast.LENGTH_SHORT).show();}
                }else{edt_idade_pet.setError("Campo Obrigatorio");
                    Toast.makeText(getContext(),"Preecha a Idade Do Pet",Toast.LENGTH_SHORT).show();}
            }else{edt_raca_pet.setError("Campo Obrigatorio");
                Toast.makeText(getContext(),"Preecha a Raça Do Pet",Toast.LENGTH_SHORT).show();}
        }else{edt_nome_pet.setError("Campo Obrigatorio");
            Toast.makeText(getContext(),"Preecha o Nome Do Pet",Toast.LENGTH_SHORT).show();}
    }
    private String tiet_string(TextInputEditText edt){
        String str = edt.getText().toString();
        return str;
    }
    private String mact_string(MaterialAutoCompleteTextView edt){
        String str = edt.getText().toString();
        return str;
    }
    private String cb_check(CheckBox checkBox, String CaseTrue, String CaseFalse){
        String str = checkBox.isChecked() ? CaseTrue : CaseFalse;
        return str;
    }

}