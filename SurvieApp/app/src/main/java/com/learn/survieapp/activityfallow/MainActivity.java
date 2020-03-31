package com.learn.survieapp.activityfallow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.adaptateurRecyclerView.RecyclerViewAdapted1;
import com.learn.survieapp.readDataClass.ReaderData;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        jSonAction();

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

    public void jSonAction(){
        String jsonFileString = ReaderData.getJsonFromAssets(getApplicationContext(), "Data.json");

        Log.i("data", jsonFileString);

        Gson gson = new Gson();

        Type listUserType = new TypeToken<ArrayList<ReaderData>>() { }.getType();

        ArrayList<ReaderData> valeur = gson.fromJson(jsonFileString, listUserType);

        for (int i = 0; i < valeur.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + valeur.get(i));
            valeurRegion.add(valeur.get(i).getValeur_Region_Data());
            valeurRegionImageview.add(valeur.get(i).getValeurRegionImageviewData());
            valeurSurvie.add(valeur.get(i).getValeur_Survie_Data());
            valeurNourriture.add(valeur.get(i).getValeur_Nourriture_Data());
            valeurEau.add(valeur.get(i).getValeur_Eau_Data());
            valeurPlante.add(valeur.get(i).getValeur_Plante_Data());
        }

        data = findViewById(R.id.firstrecyclerview);

        int regiondesert = R.drawable.regiondesert;

        Log.i("test","test pour retrouvé les valeur"+regiondesert);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.carteimage:
                Log.i("test","carte");
                break;
            case R.id.voyageurimage:
                Log.i("test","voyageur");
                break;
            case R.id.outilsutileimage:
                Log.i("test","outils");
                break;
            case R.id.couteausuisse:
                Log.i("test","couteau");
                break;
            default:
                break;
        }
    }
}
