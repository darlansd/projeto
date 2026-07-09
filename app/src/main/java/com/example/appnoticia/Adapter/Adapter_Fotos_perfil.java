package com.example.appnoticia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appnoticia.Models.Foto_perfis;
import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class Adapter_Fotos_perfil extends RecyclerView.Adapter<Adapter_Fotos_perfil.viewholder> {

    Context context;
    private final List<Foto_perfis> fotos;

    public Adapter_Fotos_perfil(Context context, List<Foto_perfis> foto_perfil) {
        this.context = context;
        this.fotos = foto_perfil;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View oncreatevh = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_lista_imagem_perfil, parent,false);

        return new viewholder(oncreatevh);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        //pegar o index de cada item do array
        Foto_perfis posicao = fotos.get(position);

        Glide.with(holder.foto)
                .load(posicao.getLink())
                .placeholder(R.drawable.placeholder_img_perfil)
                .into(holder.foto);

    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        ShapeableImageView foto;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.foto);
        }
    }

}
