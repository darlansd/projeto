package com.example.appnoticia.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.MenuProvider;
import com.example.appnoticia.R;

public class Fragment_adote_um_pet extends Fragment{
    MenuProvider menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adote_um_pet, container, false);


    return view;
    }
}