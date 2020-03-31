package com.learn.survieapp.activityfallow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.learn.survieapp.R;
import com.learn.survieapp.adaptateurRecyclerView.RecyclerViewAdapted1;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> valeurRegion= new ArrayList<>();
    ArrayList<Integer> valeurRegionImageview= new ArrayList<>();
    ArrayList<String> valeurSurvie= new ArrayList<>();
    ArrayList<String> valeurNourriture= new ArrayList<>();
    ArrayList<String> valeurEau= new ArrayList<>();
    ArrayList<String> valeurPlante = new ArrayList<>();
    RecyclerViewAdapted1 recyclerViewAdapted1;
    RecyclerView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.firstrecyclerview);

        valeurRegion.add("Montagne");
        valeurRegionImageview.add(R.drawable.regionmontagne);
        valeurSurvie.add("1");
        valeurNourriture.add("1");
        valeurEau.add("1");
        valeurPlante.add("1");
        valeurRegion.add("Dessert");
        valeurRegionImageview.add(R.drawable.regionmontagne);
        valeurSurvie.add("1");
        valeurNourriture.add("1");
        valeurEau.add("1");
        valeurPlante.add("1");


        recyclerViewAdapted1 = new RecyclerViewAdapted1(this,  valeurRegion,
                                                                    valeurRegionImageview,
                                                                    valeurSurvie,
                                                                    valeurNourriture,
                                                                    valeurEau,
                                                                    valeurPlante);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        data.setLayoutManager(gridLayoutManager);
        data.setAdapter(recyclerViewAdapted1);
    }
}
