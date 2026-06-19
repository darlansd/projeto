package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Fragment_cadastrar_cachorro extends Fragment {
    NestedScrollView nested;
    TextInputEditText edt_nome_cao, edt_desc_cao;
    TextInputLayout layout_nome_cao, layout_raca_cao, layout_idade_cao, layout_porte, layout_desc;
    MaterialAutoCompleteTextView edt_raca_cao, edt_idade_cao, edt_porte_cao;
    RadioButton rb_macho;
    CheckBox cb_castrado, cb_vermifugado, cb_vacinado, cb_cuidados_esp, cb_docil, cb_agressivo, cb_ariso, cb_calmo, cb_brincalhao, cb_indep, cb_criancas, cb_crianca, cb_cachorros, cb_gatos, cb_apart, cb_casa;
    AppCompatButton btn_enviar;
    String nome_cao, raca_cao, idade_cao, porte, sexo, castrado, vermifugado, vacinado, cuidados_esp, docil, agressivo, arisco, calmo, brincalhao, indep, crianca, criancas, gatos, cachorros, apart, casa, desc_cao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_cachorro, container, false);

        nested = view.findViewById(R.id.nested);

        layout_nome_cao = view.findViewById(R.id.layout_nome_cao);
        edt_nome_cao = view.findViewById(R.id.edt_nome_cao);

        layout_desc = view.findViewById(R.id.layout_desc_cao);
        edt_desc_cao = view.findViewById(R.id.edt_desc_cao);

        layout_raca_cao = view.findViewById(R.id.layout_raca_cao);
        edt_raca_cao = view.findViewById(R.id.edt_raca_cao);

        layout_idade_cao = view.findViewById(R.id.layout_idade_cao);
        edt_idade_cao = view.findViewById(R.id.edt_idade_cao);

        layout_porte = view.findViewById(R.id.layout_porte_cao);
        edt_porte_cao = view.findViewById(R.id.edt_porte_cao);

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
        btn_enviar = view.findViewById(R.id.btn_cadastrar_cao);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validar_cadastrar_cao();

            }
        });

        return view;
    }

    private void validar_cadastrar_cao() {
        nome_cao = tiet_string(edt_nome_cao);
        desc_cao = tiet_string(edt_desc_cao);

        raca_cao = mact_string(edt_raca_cao);
        idade_cao = mact_string(edt_idade_cao);
        porte = mact_string(edt_porte_cao);

        sexo = rb_macho.isChecked() ? "Macho" : "Fêmea";
        castrado = cb_check(cb_castrado, "Castrado", "Nao Castrado");
        vermifugado = cb_check(cb_vermifugado, "Vermifugado", "Nao Vermifugado");
        vacinado = cb_check(cb_vacinado, "Vacinado", "Nao Vacinado");
        cuidados_esp = cb_check(cb_cuidados_esp, "Precisa De Cuidados Especiais", "Nao Precisa De Cuidados Especiais");
        docil = cb_check(cb_docil, "Docil", "Não Docil");
        calmo = cb_check(cb_calmo, "Calma", "Nao Calma");
        agressivo = cb_check(cb_agressivo, "Agressiva", "Nao Agressivo");
        brincalhao = cb_check(cb_brincalhao, "Brincalhona", "Nao Brincalhona");
        arisco = cb_check(cb_ariso, "Arisca", "Nao Arisca");
        indep = cb_check(cb_indep, "Independente", "Nao Independente");

        if (!nome_cao.isEmpty()) {

            edt_nome_cao.setBackgroundResource(R.drawable.custom_edit_text);
            layout_nome_cao.setHelperText(null);

            if (!raca_cao.isEmpty()) {

                edt_raca_cao.setBackgroundResource(R.drawable.custom_edit_text);
                layout_raca_cao.setHelperText(null);

                if (!idade_cao.isEmpty()) {

                    edt_idade_cao.setBackgroundResource(R.drawable.custom_edit_text);
                    layout_raca_cao.setHelperText(null);

                    if (!porte.isEmpty()) {

                        edt_porte_cao.setBackgroundResource(R.drawable.custom_edit_text);
                        layout_raca_cao.setHelperText(null);

                        if (!sexo.isEmpty()) {
                            if (!desc_cao.isEmpty()) {

                                Pets pets = new Pets(nome_cao, raca_cao, sexo, desc_cao, idade_cao, castrado, vermifugado, porte, vacinado, cuidados_esp, docil, calmo, agressivo, brincalhao, arisco, indep, "Cachorro", Usuarios.getUid());
                                pets.uploadtodatabase();
                                requireActivity().finish();
                                Toast.makeText(getContext(), pets.getEspecie() + " Cadastrado Com Sucesso", Toast.LENGTH_SHORT).show();

                            } else {
                                layout_desc.setHelperText(getString(R.string.error));
                                edt_desc_cao.setBackgroundResource(R.drawable.custom_edit_text_error);
                                Toast.makeText(getContext(), "Fale Um Pouco Sobre Seu Pet", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Defina Um Genero Do Pet", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        layout_porte.setHelperText(getString(R.string.error));
                        edt_porte_cao.setBackgroundResource(R.drawable.custom_edit_text_error);
                        Toast.makeText(getContext(), "Defina o Porte Do Pet", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    layout_idade_cao.setHelperText(getString(R.string.error));
                    edt_idade_cao.setBackgroundResource(R.drawable.custom_edit_text_error);
                    Toast.makeText(getContext(), "Preecha a Idade Do Pet", Toast.LENGTH_SHORT).show();
                }
            } else {
                layout_raca_cao.setHelperText(getString(R.string.error));
                edt_raca_cao.setBackgroundResource(R.drawable.custom_edit_text_error);
                Toast.makeText(getContext(), "Preecha a Raça Do Pet", Toast.LENGTH_SHORT).show();
            }
        } else {
            layout_nome_cao.setHelperText(getString(R.string.error));
            edt_nome_cao.setBackgroundResource(R.drawable.custom_edit_text_error);
            Toast.makeText(getContext(), "Preecha o Nome Do Pet", Toast.LENGTH_SHORT).show();
        }
    }

    private String tiet_string(TextInputEditText edt) {
        String str = edt.getText().toString();
        return str;
    }

    private String mact_string(MaterialAutoCompleteTextView edt) {
        String str = edt.getText().toString();
        return str;
    }

    private String cb_check(CheckBox checkBox, String CaseTrue, String CaseFalse) {
        String str = checkBox.isChecked() ? CaseTrue : CaseFalse;
        return str;
    }

}