package com.learn.survieapp.activityPrincipaux;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.activitySecondaire.BookGuideActivity;
import com.learn.survieapp.adaptateurRecyclerView.RecyclerViewAdapted1;
import com.learn.survieapp.readDataClass.ReaderData;
import com.learn.survieapp.readDataClass.SpeakBot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewAdapted1.OnNoteListener , TextToSpeech.OnInitListener{

    /**
     * Les variables ci-dessous sont utilisé pour le Speakbot
     */
    TextToSpeech toSpeech;
    int result;
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
        bot();
        jSonAction();
        data = findViewById(R.id.gestionrecyclerview);

        // lancement du recyclerview
        recyclerViewAdapted1 = new RecyclerViewAdapted1(this,  valeurRegion,valeurRegionImageview,valeurSurvie,
                                                                    valeurNourriture, valeurEau, valeurPlante, this);

        // permet de mettre le recyclerview dans une grille de 1
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        data.setLayoutManager(gridLayoutManager);
        data.setAdapter(recyclerViewAdapted1);
    }

    public void bot(){

        toSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = toSpeech.setLanguage(Locale.FRANCE);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(getApplicationContext(),"probléme",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        SpeakBot bot = new SpeakBot();
                        toSpeech.speak(bot.tutoriel(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "no mec", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onNoteClick(int position) {

        String region = valeurRegion.get(position);
        String dataTake = region+".json";
        Intent intentinfos = new Intent(MainActivity.this,GuideActivity.class);
        intentinfos.putExtra("DataType",dataTake);
        startActivity(intentinfos);
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
            valeurRegionImageview.add(valeur.get(i).getValeurRegionImageviewData());
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
            case R.id.gestionvoyageurimage:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Vous etez ?");
                String[] difficult = {"Un Aventurier","Une Aventuriére"};
                builder.setItems(difficult, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intentGenre = new Intent(MainActivity.this, AProposActivity.class);

                        switch (which)
                        {

                            case 0: // Un Aventurier
                                Log.i("test","homme");
                                intentGenre.putExtra("Genre","Homme");
                                startActivity(intentGenre);
                                break;
                            case 1:// Un Aventuriére
                                Log.i("test","femme");
                                intentGenre.putExtra("Genre","Femme");
                                startActivity(intentGenre);
                                break;
                            default:
                                break;
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            case R.id.gestionreturn:
                Intent intentcouteausuisse = new Intent(MainActivity.this, CouteauSuisseActivity.class);
                startActivity(intentcouteausuisse);
                Log.i("test","couteau");
                break;
            case R.id.imageViewtutoriel:
                Intent intenttuto = new Intent(MainActivity.this, BookGuideActivity.class);
                startActivity(intenttuto);
                break;
            default:
                break;
        }
    }

    @Override
    public void onInit(int status) {

    }
}
