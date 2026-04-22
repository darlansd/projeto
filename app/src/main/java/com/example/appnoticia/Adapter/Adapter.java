package com.example.appnoticia.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {


    //reponsavel por crirar os primeiros itens da lista
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    //exibe os dados nos itens que o metodo anterior cria
    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //responsavel por guardar os dados antes de ser exibido na tela
    public class viewholder extends RecyclerView.ViewHolder{
        //entao preciso passar os dados aqui

        public viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
