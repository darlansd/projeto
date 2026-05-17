package com.example.appnoticia.Config;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

public class TrocarFragment {

    public static void intent_PutExtra(Activity activity, Context context, Class activity_com_intecao, int value){

        Intent intent1 = new Intent(context,activity_com_intecao);
        intent1.putExtra("SHOW_FRAGMENT",value);

        // pra poder funcionar em versoes um pouco mais antigas do android
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        activity.startActivity(intent1);

    }

    public static void trocar (FragmentActivity activity, int layout, Fragment fragment){

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(layout,fragment);
        transaction.commit();

        // Boas práticas do Android para animações e transições
        transaction.setReorderingAllowed(true);

    }


}
