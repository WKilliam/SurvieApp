package com.learn.survieapp.activityfallow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.survieapp.R;
import com.learn.survieapp.adaptateurRecyclerView.RecyclerViewAdapted2;

import java.util.ArrayList;

public class CouteauSuisseActivity extends AppCompatActivity implements View.OnClickListener
{

    ArrayList<String> valeurTextBox;
    ArrayList<Integer> valeurImageview;
    RecyclerViewAdapted2 recyclerViewAdapted2;
    RecyclerView data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couteau_suisse);

        data = findViewById(R.id.gestionrecyclerview);

        valeurTextBox = new ArrayList<>();
        valeurImageview = new ArrayList<>();

        valeurImageview.add(R.drawable.femme);
        valeurTextBox.add("Couteau");

        int femme = R.drawable.femme;

        Log.i("test","test "+ valeurImageview);



        // lancement du recyclerview
        recyclerViewAdapted2 = new RecyclerViewAdapted2(this, valeurImageview,valeurTextBox);

        // permet de mettre le recyclerview dans une grille de 1
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        data.setLayoutManager(gridLayoutManager);
        data.setAdapter(recyclerViewAdapted2);

    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.gestionvoyageurimage:
                break;
            case R.id.gestioncarteimage:
                break;
            case R.id.gestionoutilsutileimage:
                break;
            case R.id.gestionreturn:
                break;
            default:
                break;
        }
    }
}
