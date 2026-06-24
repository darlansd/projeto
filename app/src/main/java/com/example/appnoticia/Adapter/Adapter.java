package com.example.appnoticia.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {

    private final List<Pets> dados_pet;
    private final Context context;

    public Adapter(Context context, List<Pets> dados_pet) {
        this.context = context;
        this.dados_pet = dados_pet;
    }

    //reponsavel por criar os primeiros itens da lista
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View oncreatevh = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_lista_recycle_view, parent, false);

        return new viewholder(oncreatevh);
    }

    //exibe os dados nos itens que o metodo anterior cria
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        //pegar o index de cada item do array
        Pets posicao = dados_pet.get(position);

        //responsavel por pegar o uid do usuario em cada item no nó de pets-feed e buscar no nó de usuarios pra recuperar o endereço de cada usuario individualmente
        Query buscarloc = Configuracao_firebase.getfirebasedatabase().child("USUARIOS").orderByKey().equalTo(String.valueOf(posicao.getuid_user()));

        buscarloc.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot fds : snapshot.getChildren()) {
                        Usuarios val = fds.getValue(Usuarios.class);
                        holder.txt_localizacao.setText(val.getBairro() + ", " + val.getCidade());
                    }
                } else {
                    Log.i("buscarloc", "leitado");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("buscaCancelada", "leitado");
            }
        });

        holder.nome_pet.setText(posicao.getNOME());
        holder.raca_pet.setText(posicao.getRACA());
        holder.sexo_pet.setText(posicao.getSEXO());

        holder.desc_pet.setText(posicao.getDESCRIÇAO());

        holder.idade_pet.setText(String.valueOf(posicao.getIDADE()));

    }

    @Override
    public int getItemCount() {
        return dados_pet.size();
    }

    //responsavel por guardar os dados antes de ser exibido na tela
    public static class viewholder extends RecyclerView.ViewHolder {
        //declarar os tipos de views aqui
        //como se fosse antes do onCreate em uma activity

        TextView nome_pet, raca_pet, sexo_pet, idade_pet, desc_pet, txt_localizacao, fodase;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            //fazer referencia as views dos modelos dos itens da lista
            //como se fosse o onCreate em uma activity
            fodase= itemView.findViewById(R.id.fodase);

            txt_localizacao = itemView.findViewById(R.id.txt_localizacao);
            nome_pet = itemView.findViewById(R.id.txt_nome_pet);
            raca_pet = itemView.findViewById(R.id.txt_raca_pet);
            sexo_pet = itemView.findViewById(R.id.txt_sexo_pet);
            idade_pet = itemView.findViewById(R.id.txt_idade_pet);
            desc_pet = itemView.findViewById(R.id.txt_desc_pet);


        }
    }

}