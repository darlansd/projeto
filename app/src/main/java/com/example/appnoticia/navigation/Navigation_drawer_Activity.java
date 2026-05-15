package com.example.appnoticia.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.appnoticia.Activities.MainActivity;
import com.example.appnoticia.Fragments.Fragment_adote_um_pet;
import com.example.appnoticia.Fragments.Fragment_cadastrar_pet;
import com.example.appnoticia.Fragments.Fragment_editar_perfil;
import com.example.appnoticia.Config.Configuracao_firebase;
import com.example.appnoticia.Models.Usuarios;
import com.example.appnoticia.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Navigation_drawer_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav_view;
    AppBarConfiguration mappconfig;
    TextView user_name_header,editar_user_header;
    Button cadastrar_pet_header;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_navigation_drawer);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentContainer), (view, windowInsets) -> {

            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());

            view.setPadding(
                    view.getPaddingLeft(),
                    view.getPaddingTop(),
                    view.getPaddingRight(),
                    insets.bottom
            );

            return windowInsets;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        FragmentTransaction adote = getSupportFragmentManager().beginTransaction();
        adote.replace(R.id.fragmentContainer, new Fragment_adote_um_pet());
        adote.commit();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //fazer com que a action bar tenha o toggle (botão) no navigation drawer
        drawer = findViewById(R.id.main);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.kaka,R.string.koko);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(-1);

        //implementar o metodo responsavel por controlar a navegaçao dos menus no navigation drawer
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

        //fazendo referencia as views do header e alterando elas
        View header_views = nav_view.getHeaderView(0);
        user_name_header = header_views.findViewById(R.id.txt_user_name_header);
        cadastrar_pet_header = header_views.findViewById(R.id.btn_cadastrar_pet_header);
        editar_user_header = header_views.findViewById(R.id.txt_editar_perfil_header);

        //fazer com que isso atualize assim que voce alterar o nome no fragment de editar perfil
        Usuarios.getChild_nome().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user_name_header.setText(snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editar_user_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction editar_perfil = getSupportFragmentManager().beginTransaction();
                editar_perfil.replace(R.id.fragmentContainer, new Fragment_editar_perfil());
                editar_perfil.commit();

                drawer.closeDrawer(GravityCompat.START);
            }
        });

        cadastrar_pet_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction cadastrar_pet = getSupportFragmentManager().beginTransaction();
                cadastrar_pet.replace(R.id.fragmentContainer, new Fragment_cadastrar_pet());
                cadastrar_pet.commit();


                drawer.closeDrawer(GravityCompat.START);
            }
        });


    }

    // responsavel por controlar a navegaçao dos itens no navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId() == R.id.menu_sair){

            Configuracao_firebase.getfirebaseauth().signOut();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            Toast.makeText(getApplicationContext(),"Usuario Deslogado",Toast.LENGTH_SHORT);

        }else if(menuItem.getItemId() == R.id.menu_adote){

            FragmentTransaction adote = getSupportFragmentManager().beginTransaction();
            adote.replace(R.id.fragmentContainer, new Fragment_adote_um_pet());
            adote.commit();

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}