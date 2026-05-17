package com.example.appnoticia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {

    private final List<Pets> dados;
    private final Context context;

    public Adapter(Context context, List<Pets> dados){
        this.context = context;
        this.dados = dados;

    }


    //reponsavel por criar os primeiros itens da lista
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View oncreatevh = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_lista_recycle_view,parent,false);

        return new viewholder(oncreatevh);
    }

    //exibe os dados nos itens que o metodo anterior cria
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        //pegar o index de cada item do array
        Pets posicao = dados.get(position);

        holder.nome_pet.setText(posicao.getNOME());
        holder.raca_pet.setText(posicao.getRACA());
        holder.sexo_pet.setText(posicao.getSEXO());
        holder.desc_pet.setText(posicao.getDESCRIÇAO());
        holder.idade_pet.setText(String.valueOf(posicao.getIDADE()) + " Anos");


    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    //responsavel por guardar os dados antes de ser exibido na tela
    public static class viewholder extends RecyclerView.ViewHolder{
        //declarar os tipos de views aqui
        //como se fosse antes do onCreate em uma activity

        TextView nome_pet,raca_pet,sexo_pet,idade_pet,desc_pet;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            //fazer referencia as views dos modelos dos itens da lista
            //como se fosse o onCreate em uma activity
            nome_pet = itemView.findViewById(R.id.txt_nome_pet);
            raca_pet = itemView.findViewById(R.id.txt_raca_pet);
            sexo_pet = itemView.findViewById(R.id.txt_sexo_pet);
            idade_pet = itemView.findViewById(R.id.txt_idade_pet);
            desc_pet = itemView.findViewById(R.id.txt_desc_pet);


        }
    }

}