package com.example.appnoticia.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appnoticia.Adapter.Adapter;
import com.example.appnoticia.Adapter.Adapter_Fotos_perfil;
import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Models.Foto_perfis;
import com.example.appnoticia.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class placeholder_imagens_perfil extends Fragment {
    RecyclerView recycler;
    Adapter_Fotos_perfil adapter;
    List<Foto_perfis> fotos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.placeholder_imagens_perfil, container, false);

        Configuracao_firebase.getfirebasedatabase().child("IMAGENS PERFIL").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Log.i("snaphot_fotos","conectou");
                    fotos.clear();
                    for(DataSnapshot fds:snapshot.getChildren()){
                        Foto_perfis val = fds.getValue(Foto_perfis.class);
                        fotos.add(val);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("snaphot_fotos","leitado");
            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(manager);
        adapter= new Adapter_Fotos_perfil(getContext(),fotos);
        recycler.setAdapter(adapter);
        recycler.setHasFixedSize(true);

        return view;
    }

}