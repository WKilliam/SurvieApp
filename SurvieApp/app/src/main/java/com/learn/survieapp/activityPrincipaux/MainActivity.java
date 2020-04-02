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
import com.learn.survieapp.activitySecondaire.ActivitySlide;
import com.learn.survieapp.adaptateurRecyclerView.FristActRVAdapted;
import com.learn.survieapp.readDataClass.ReadPrototype;
import com.learn.survieapp.readDataClass.ReaderData;
import com.learn.survieapp.readDataClass.SpeakBot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FristActRVAdapted.OnNoteListener , TextToSpeech.OnInitListener{

    /**
     * Les variables ci-dessous sont utilisé pour le Speakbot
     */
    TextToSpeech toSpeech;
    int result;
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
        bot();
        jSonAction();
        data = findViewById(R.id.gestionrecyclerview);

        // lancement du recyclerview

        mfristActRVAdapted = new FristActRVAdapted(this, mTextRegion,mRegionImageview,mTauxSurvie,mTauxDeshydra,mTauxPlante,this);


        // permet de mettre le recyclerview dans une grille de 1
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        data.setLayoutManager(gridLayoutManager);
        data.setAdapter(mfristActRVAdapted);
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
                        toSpeech.speak(bot.testbot("je m'appel lol"), TextToSpeech.QUEUE_FLUSH, null);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "no mec", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onNoteClick(int position) {

        String region = mLinkData.get(position);
        String dataTake = region+".json";
        Log.i("data", "> ItemImage JSON " + position + "\n" + dataTake );
        Intent intentinfos = new Intent(MainActivity.this,GuideActivity.class);
        intentinfos.putExtra("DataType",dataTake);
        toSpeech.stop();
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
                                toSpeech.stop();
                                startActivity(intentGenre);
                                break;
                            case 1:// Un Aventuriére
                                Log.i("test","femme");
                                intentGenre.putExtra("Genre","Femme");
                                toSpeech.stop();
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
                toSpeech.stop();
                startActivity(intentcouteausuisse);
                Log.i("test","couteau");
                break;
            case R.id.imageViewtutoriel:
                Intent intenttuto = new Intent(MainActivity.this, ActivitySlide.class);
                intenttuto.putExtra("INDEX","TUTO");
                toSpeech.stop();
                startActivity(intenttuto);
                break;
            default:
                break;
        }
    }

    @Override
    public void onInit(int status) {

    }
    protected void onDestroy(){
        super.onDestroy();
        if (toSpeech!=null)
        {
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }
}
