package com.learn.survieapp.activityPrincipaux;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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
import com.learn.survieapp.activitySecondaire.AProposActivity;
import com.learn.survieapp.activitySecondaire.ActivitySlide;
import com.learn.survieapp.adaptateurRecyclerView.FristActRVAdapted;
import com.learn.survieapp.readDataClass.ReaderData;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FristActRVAdapted.OnNoteListener , TextToSpeech.OnInitListener{


    /**
     * Les variables ci-dessous servent à récupéré des valeurs bien presice pour l'affichage du recyclerview
     */

    ArrayList<Integer> mRegionImageview = new ArrayList<>();
    ArrayList<String> mTextRegion = new ArrayList<>();
    ArrayList<String> mTauxSurvie = new ArrayList<>();
    ArrayList<String> mTauxDeshydra = new ArrayList<>();
    ArrayList<String> mTauxPlante = new ArrayList<>();
    ArrayList<String> mLinkData=new ArrayList<>();
    FristActRVAdapted mfristActRVAdapted;
    RecyclerView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bot();
        jSonAction();
        data = findViewById(R.id.gestionrecyclerview);

        // lancement du recyclerview

        mfristActRVAdapted = new FristActRVAdapted(this, mTextRegion,mRegionImageview,mTauxSurvie,mTauxDeshydra,mTauxPlante,this);


        // permet de mettre le recyclerview dans une grille de 1
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        data.setLayoutManager(gridLayoutManager);
        data.setAdapter(mfristActRVAdapted);
    }

    @Override
    public void onNoteClick(int position) {

        String region = mLinkData.get(position);
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

        Resources resources = this.getResources();

        // get resource id by image name

        //remplace les valeur par les valeur du fichier json
        for (int i = 0; i < valeur.size(); i++) {

            //Log.i("data", "> Item " + i + "\n" + valeur.get(i));
            mLinkData.add(valeur.get(i).getdLinkData());

            mTextRegion.add(valeur.get(i).getDmilieu_Region_Data());
            final int resourceId = resources.getIdentifier(valeur.get(i).getDvaleur_Region_Imageview_Data(), "drawable", this.getPackageName());
           // Log.i("data", "> ItemImage " + i + "\n" + valeur.get(i).getdLinkData());
            mRegionImageview.add(resourceId);
            mTauxSurvie.add(valeur.get(i).getDtaux_Survie_Data());
            mTauxDeshydra.add(valeur.get(i).getDtaux_Eau_Data());
            mTauxPlante.add(valeur.get(i).getDtaux_Plante_Data());
        }
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
                Intent intenttuto = new Intent(MainActivity.this, ActivitySlide.class);
                intenttuto.putExtra("JsonFile","Tuto.json");
                intenttuto.putExtra("index","tuto");

                startActivity(intenttuto);
                break;
            default:
                break;
        }
    }

    @Override
    public void onInit(int status) {

    }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }
}
