package com.learn.survieapp.activityPrincipaux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.activitySecondaire.ActivitySlide;
import com.learn.survieapp.activitySecondaire.CompassActivity;
import com.learn.survieapp.adaptateurRecyclerView.RecyclerViewAdapted2;
import com.learn.survieapp.readDataClass.ReaderDataCouteauSuisse;
import com.learn.survieapp.readDataClass.SpeakBot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

public class CouteauSuisseActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewAdapted2.OnNoteListener
{

    /**
     * Les variables ci-dessous sont utilisé pour le Speakbot
     */
    TextToSpeech toSpeech;
    int result;
    String genre;

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

        bot();
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
        switch (position){
            case 0:
                Intent intentcompas = new Intent(CouteauSuisseActivity.this, CompassActivity.class);
                startActivity(intentcompas);
                break;
            case 1:
                Log.i("Type", "Type name : " + position);
                break;
            case 2:
                Intent intent = new Intent(CouteauSuisseActivity.this, ActivitySlide.class);
                startActivity(intent);
                Log.i("Type", "Type name : " + position);
                break;
            default:
                break;
        }
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
                        toSpeech.speak(bot.couteauSuisse(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "no mec", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
