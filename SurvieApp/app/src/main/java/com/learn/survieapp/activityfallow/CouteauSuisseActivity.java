package com.learn.survieapp.activityfallow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
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
        setContentView(R.layout.activity_couteau_suisse);

        data = findViewById(R.id.gestionrecyclerview);

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
        String jsonFileString = ReaderDataCouteauSuisse.getJsonFromAssetsCouteauS(getApplicationContext(), "DataCouteauSuisse.json");

        Log.i("data", jsonFileString);

        // création de l'object Gson
        Gson gson = new Gson();

        // Représente un type générique T
        Type listUserType = new TypeToken<ArrayList<ReaderDataCouteauSuisse>>() { }.getType();

        //Liste des objets
        ArrayList<ReaderDataCouteauSuisse> valeur = gson.fromJson(jsonFileString, listUserType);

        //remplace les valeur par les valeur du fichier json
        for (int i = 0; i < valeur.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + valeur.get(i));
            valeurImageview.add(valeur.get(i).getIcon_outil());
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
            case R.id.gestioncarteimage:
                break;
            case R.id.tutoimage:
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
        Intent intent = new Intent(CouteauSuisseActivity.this, AProposActivity.class);
        activityStartRecyclerviewClick(position,intent);
        Log.i("Type", "Type name : " + position);
    }
}
