package com.example.appnoticia.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnoticia.Activities.MainActivity;
import com.example.appnoticia.Activities.Terceira_Activity;
import com.example.appnoticia.Adapter.Adapter;
import com.example.appnoticia.Models.Configuracao_firebase;
import com.example.appnoticia.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jspecify.annotations.NonNull;

public class Fragment_adote_um_pet extends Fragment{
    FirebaseAuth auth = Configuracao_firebase.getfirebaseauth();
    RecyclerView recycler;
    RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());

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
        recycler.setLayoutManager(manager);
        recycler.setAdapter(new Adapter());
        recycler.setHasFixedSize(true);


    return view;
    }
}