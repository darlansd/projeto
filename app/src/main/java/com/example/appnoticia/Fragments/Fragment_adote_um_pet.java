package com.example.appnoticia.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnoticia.Adapter.Adapter;
import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_adote_um_pet extends Fragment {
    DatabaseReference pets_Feed = FirebaseDatabase.getInstance().getReference("PETS-FEED");
    RecyclerView recycler;
    List<Pets> dados;
    Context context;
    Adapter adapterPetsFeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EdgeToEdge.enable(requireActivity());
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_adote_um_pet, container, false);

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        recycler = view.findViewById(R.id.recyclerview);

        dados = new ArrayList<>();

        pets_Feed.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dados.clear();
                for (DataSnapshot data : snapshot.getChildren()) {

                    Pets valores = data.getValue(Pets.class);

                    dados.add(valores);
                }
                adapterPetsFeed.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(manager);
        adapterPetsFeed = new Adapter(context, dados);
        recycler.setAdapter(adapterPetsFeed);
        recycler.setHasFixedSize(true);


        return view;
    }
}