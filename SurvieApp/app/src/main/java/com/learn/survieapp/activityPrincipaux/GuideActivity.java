package com.learn.survieapp.activityPrincipaux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.activitySecondaire.ActivitySlide;
import com.learn.survieapp.adaptateurRecyclerView.AdaptedGuide;
import com.learn.survieapp.readDataClass.ReaderDataGuideActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity implements  AdaptedGuide.OnNoteListener{


    ArrayList<Integer> mRegionIcon= new ArrayList<>();
    ArrayList<String> mSurvieTypeText= new ArrayList<>();
    ArrayList<String> mSurvieTypeTextDetail= new ArrayList<>();
    String datatype;
    AdaptedGuide adaptedGuide;
    RecyclerView recyclerViewguide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_recycler_view);

        Intent srcintent = getIntent();
        datatype = srcintent.getStringExtra("DataType");
        jSonAction(datatype);
        recyclerViewguide = findViewById(R.id.recviewgestionactivity);
        adaptedGuide = new AdaptedGuide(this,mRegionIcon,mSurvieTypeText,mSurvieTypeTextDetail,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerViewguide.setLayoutManager(gridLayoutManager);
        recyclerViewguide.setAdapter(adaptedGuide);
    }

    @Override
    public void onNoteClick(int position) {

        startactivityselected(position);
    }
    /**
     * traitement du fichier json
     */
    public void jSonAction(String data){


        //transforme tous le json en String
        String jsonFileString = ReaderDataGuideActivity.getJsonFromAssets(getApplicationContext(), data);

        Log.i("data", jsonFileString);

        // création de l'object Gson
        Gson gson = new Gson();

        // Représente un type générique T
        Type listUserType = new TypeToken<ArrayList<ReaderDataGuideActivity>>() { }.getType();


        //Liste des objets
        ArrayList<ReaderDataGuideActivity> valeur = gson.fromJson(jsonFileString, listUserType);

        Resources resources = this.getResources();

        //remplace les valeur par les valeur du fichier json
        for (int i = 0; i < valeur.size(); i++) {
            Log.i("test","=========== "+" ========= "+valeur.get(i).getjRegion());
            final int resourceId = resources.getIdentifier(valeur.get(i).getjRegion(), "drawable", this.getPackageName());
            mRegionIcon.add(resourceId);
            mSurvieTypeText.add(valeur.get(i).getjTextTitre());
            mSurvieTypeTextDetail.add(valeur.get(i).getjTexteDetail());
            }


    }

    public void startactivityselected(int stat){

        Intent intent = new Intent(GuideActivity.this, ActivitySlide.class);
        switch (stat){
            case 0 :
                intent.putExtra("JsonFile",datatype);
                intent.putExtra("index","24");
                startActivity(intent);
                break;
            case 1 :
                intent.putExtra("JsonFile",datatype);
                intent.putExtra("index","72");
                startActivity(intent);
                break;
            case 2 :
                intent.putExtra("JsonFile",datatype);
                intent.putExtra("index","BonASavoir");
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
