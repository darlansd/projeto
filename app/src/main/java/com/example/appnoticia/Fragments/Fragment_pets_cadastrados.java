package com.example.appnoticia.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.appnoticia.Adapter.Adapter;
import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Config.TrocarFragment;
import com.example.appnoticia.Models.Pets;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;
import com.example.appnoticia.RecyclerViewOnClickListener.OnClick_Recycler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Fragment_pets_cadastrados extends Fragment {
    AppCompatButton btn_encaminhar;
    DatabaseReference pets_por_users = FirebaseDatabase.getInstance().getReference("PETS-POR-USUARIO").child(Usuarios.getUid());
    DatabaseReference pets_feed = FirebaseDatabase.getInstance().getReference("PETS-FEED");
    RecyclerView recycler;
    List<Pets> dados;
    Context context;
    Adapter adapterPetsFeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pets_cadastrados, container, false);

        dados = new ArrayList<>();

        btn_encaminhar = view.findViewById(R.id.btn_encaminhar_frag_cadastrar_pet);

        //se for comentar ou descomentar lembra do uploadtodatabase na classe Pets e delete_ppu no OnLongItemClick la em baixo
        Query q_pets_ppu = pets_feed.orderByChild("UID_USER").equalTo(Usuarios.getUid());

        q_pets_ppu.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Log.i("snap pets cadastrados",snapshot.getValue().toString());
                    dados.clear();

                    for (DataSnapshot kaka : snapshot.getChildren()) {
                        Pets valores = kaka.getValue(Pets.class);
                        dados.add(valores);
                    }
                    adapterPetsFeed.notifyDataSetChanged();

                }else{
                    btn_encaminhar.setVisibility(View.VISIBLE);
                    btn_encaminhar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           //todo: fazer fragment de adotar pet aparecer
                        }
                    });
                    Toast.makeText(getContext(),"voce ainda nao cadastrou pets",Toast.LENGTH_SHORT).show();}
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        //todo: talvez esse esquema de criar um no novo so para os pets do usuario pode servir de alguma coisa

        /* //se for comentar ou descomentar lembra do uploadtodatabase na classe Pets e delete_ppu no OnLongItemClick la em baixo
        pets_por_users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    dados.clear();
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Pets valores = data.getValue(Pets.class);
                        dados.add(valores);
                    }
                    adapterPetsFeed.notifyDataSetChanged();

                }else {Toast.makeText(getContext(),"voce ainda nao cadastrou pets",Toast.LENGTH_SHORT).show();}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */


        //definindo o adapter e o layoutManager
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recycler = view.findViewById(R.id.recyclerview);
        recycler.setLayoutManager(manager);
        adapterPetsFeed = new Adapter(context, dados);
        recycler.setAdapter(adapterPetsFeed);
        recycler.setHasFixedSize(true);

        //evento de click
        recycler.addOnItemTouchListener(new OnClick_Recycler(getContext(), recycler, new OnClick_Recycler.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(getContext(), R.string.kaka, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onLongItemClick(View view, int position) {

                String nome = dados.get(position).getNOME();

                // configurando o alert dialog
                AlertDialog.Builder alerta = new AlertDialog.Builder(requireContext());
                alerta.setTitle("Deseja Apagar o Post de " + nome + "?");
                alerta.setMessage("fazendo isso voce vai apagar o Post Desse Pet.");
                alerta.setCancelable(false);

                alerta.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Pets.delete_ppu("nome",nome);
                        Pets.delete_pf(getContext(),"nome",nome);

                    }
                });
                // gerando e exibindo o alert dialog
                alerta.create().show();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));


        return view;
    }
}