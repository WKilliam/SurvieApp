package com.learn.survieapp.activityPrincipaux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.activitySecondaire.ActivitySlide;
import com.learn.survieapp.activitySecondaire.CompassActivity;
import com.learn.survieapp.activitySecondaire.MorseTranslateActivity;
import com.learn.survieapp.adaptateurRecyclerView.RecyclerViewAdapted2;
import com.learn.survieapp.readDataClass.ReaderDataCouteauSuisse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CouteauSuisseActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewAdapted2.OnNoteListener
{


    ArrayList<String> valeurTextBox= new ArrayList<>();
    ArrayList<Integer> valeurImageview= new ArrayList<>();
    RecyclerViewAdapted2 recyclerViewAdapted2;
    RecyclerView data;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_recycler_view);

        data = findViewById(R.id.recviewgestionactivity);


        jSonAction();


        // lancement du recyclerview
        recyclerViewAdapted2 = new RecyclerViewAdapted2(this, valeurImageview,valeurTextBox,this);

        // permet de mettre le recyclerview dans une grille de 1
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        data.setLayoutManager(gridLayoutManager);
        data.setAdapter(recyclerViewAdapted2);

    }
    public void activityStartRecyclerviewClick(int position,Intent intent){

        switch (position)
        {
            case 0:
                intent.putExtra("TypePage","Boussole");
                break;
            case 1:
                intent.putExtra("TypePage","Carte");
                break;
            case 2:
                intent.putExtra("TypePage","Champignion");
                break;
            default:
                break;
        }
    }
    /**
     * traitement du fichier json
     */
    public void jSonAction(){

        //transforme tous le json en String
        String jsonFileString = ReaderDataCouteauSuisse.getJsonFromAssets(getApplicationContext(), "DataCouteauSuisse.json");

        Log.i("data", jsonFileString);

        // création de l'object Gson
        Gson gson = new Gson();

        // Représente un type générique T
        Type listUserType = new TypeToken<ArrayList<ReaderDataCouteauSuisse>>() { }.getType();

        //Liste des objets
        ArrayList<ReaderDataCouteauSuisse> valeur = gson.fromJson(jsonFileString, listUserType);


        Resources resources = this.getResources();


        //remplace les valeur par les valeur du fichier json
        for (int i = 0; i < valeur.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + valeur.get(i));
            final int resourceId = resources.getIdentifier(valeur.get(i).getIcon_outil(), "drawable", this.getPackageName());
            valeurImageview.add(resourceId);
            valeurTextBox.add(valeur.get(i).getData_Text_Type_Icon());
        }



        int regiondesert = R.drawable.regiondesert;

        Log.i("test","test pour retrouvé les valeur"+regiondesert);
    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.gestionvoyageurimage:
                break;
            case R.id.gestionreturn:
                break;
            default:
                break;
        }
    }

    @Override
    public void onNoteClick(int position)
    {
        Intent intentcommun = new Intent(CouteauSuisseActivity.this, ActivitySlide.class);
        Intent intenttranslate = new Intent(CouteauSuisseActivity.this, MorseTranslateActivity.class);
        switch (position){
            case 0://boussole
                Intent intentcompas = new Intent(CouteauSuisseActivity.this, CompassActivity.class);
                startActivity(intentcompas);
                break;
            case 1://champignion
                intentcommun.putExtra("JsonFile","ChampignionData.json");
                intentcommun.putExtra("index","Champinion");
                Log.i("Type", "Type name : " + intentcommun.getStringExtra("JsonFile"));
                Log.i("Type", "Type name : " + intentcommun.getStringExtra("index"));
                startActivity(intentcommun);

                break;
            case 2://translate
                intenttranslate.putExtra("Typage","Morse");
                startActivity(intenttranslate);
                break;
            case 3://sos
                intenttranslate.putExtra("Typage","SOS");
                startActivity(intenttranslate);
                break;
            default:
                break;
        }
    }
}
