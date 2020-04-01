package com.learn.survieapp.activityfallow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.adaptateurRecyclerView.RecyclerViewAdapted1;
import com.learn.survieapp.readDataClass.ReaderData;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewAdapted1.OnNoteListener {

    /**
     * Les variables ci-dessous servent à récupéré des valeurs bien presice pour l'affichage du recyclerview
     */
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
        data = findViewById(R.id.gestionrecyclerview);

        // lancement du recyclerview
        recyclerViewAdapted1 = new RecyclerViewAdapted1(this,  valeurRegion,
                                                                    valeurRegionImageview,
                                                                    valeurSurvie,
                                                                    valeurNourriture,
                                                                    valeurEau,
                                                                    valeurPlante,
                                                        this);

        // permet de mettre le recyclerview dans une grille de 1
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        data.setLayoutManager(gridLayoutManager);
        data.setAdapter(recyclerViewAdapted1);
    }

    /**
     * traitement du fichier json
     */
    public void jSonAction(){

        //transforme tous le json en String
        String jsonFileString = ReaderData.getJsonFromAssets(getApplicationContext(), "Data.json");

        //Log.i("data", jsonFileString);

        // création de l'object Gson
        Gson gson = new Gson();

        // Représente un type générique T
        Type listUserType = new TypeToken<ArrayList<ReaderData>>() { }.getType();

        //Liste des objets
        ArrayList<ReaderData> valeur = gson.fromJson(jsonFileString, listUserType);

        //remplace les valeur par les valeur du fichier json
        for (int i = 0; i < valeur.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + valeur.get(i));
            valeurRegion.add(valeur.get(i).getValeur_Region_Data());
            valeurRegionImageview.add(R.drawable.regiondesert);
            valeurSurvie.add(valeur.get(i).getValeur_Survie_Data());
            valeurNourriture.add(valeur.get(i).getValeur_Nourriture_Data());
            valeurEau.add(valeur.get(i).getValeur_Eau_Data());
            valeurPlante.add(valeur.get(i).getValeur_Plante_Data());
        }



        int regiondesert = R.drawable.regiondesert;

        Log.i("test","test pour retrouvé les valeur"+regiondesert);
    }

    /**
     * Paramettre onclick permet de récupéré tous les onclick effectuer sur l'activité présente
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.gestioncarteimage:
                Log.i("test","carte");
                break;
            case R.id.gestionvoyageurimage:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Vous etez ?");
                String[] difficult = {"Un Aventurier","Une Aventuriére"};
                builder.setItems(difficult, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MainActivity.this, AProposActivity.class);

                        switch (which)
                        {

                            case 0: // Un Aventurier
                                Log.i("test","homme");
                                intent.putExtra("Genre","Homme");
                                startActivity(intent);
                                break;
                            case 1:// Un Aventuriére
                                Log.i("test","femme");
                                intent.putExtra("Genre","Femme");
                                startActivity(intent);
                                break;
                            default:
                                break;
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.gestionoutilsutileimage:
                Log.i("test","outils");
                break;
            case R.id.gestionreturn:
                Log.i("test","couteau");
                break;
            default:
                break;
        }
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(MainActivity.this, AProposActivity.class);
        intent.putExtra("Genre","Homme");
        startActivity(intent);
    }
}
