package com.learn.survieapp.activityPrincipaux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.activitySecondaire.ActivitySlide;
import com.learn.survieapp.adaptateurRecyclerView.AdaptedGuide;
import com.learn.survieapp.readDataClass.ReaderDataGuideActivity;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity implements  AdaptedGuide.OnNoteListener{

    /**
     * @param mSurvieTypeText liste de string pour set les emplacements de TextView
     * @param mSurvieTypeTextDetail liste de string pour set les emplacements de TextView
     * @param mRegionIcon liste de integer pour set les emplacements de ImageView
     * @param csRCVAdapted Recyclerview adapteur
     * @param datatype
     * @param csdata Recyclerview Stat
     */
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
        //recupére le type de data json
        datatype = srcintent.getStringExtra("DataType");
        // fonction de gestion json
        jSonAction(datatype);
        // récupére l'id du recylerview
        recyclerViewguide = findViewById(R.id.recviewgestionactivity);
        // lancement du recyclerview
        adaptedGuide = new AdaptedGuide(this,mRegionIcon,mSurvieTypeText,mSurvieTypeTextDetail,this);
        // permet de mettre le recyclerview dans une grille de 1
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        //orientation de la vue
        recyclerViewguide.setLayoutManager(gridLayoutManager);
        recyclerViewguide.setAdapter(adaptedGuide);
    }

    /**
     * function de récupération de la cellule en position dans le recyclerview
     * @param position  position cellule
     */
    @Override
    public void onNoteClick(int position) {
        //function de gestion position
        startactivityselected(position);
    }
    /**
     * traitement du fichier json
     */
    public void jSonAction(String data){

        //transforme tous le json en String
        String jsonFileString = ReaderDataGuideActivity.getJsonFromAssets(getApplicationContext(), data);
        // création de l'object Gson
        Gson gson = new Gson();
        // Représente un type générique T
        Type listUserType = new TypeToken<ArrayList<ReaderDataGuideActivity>>() { }.getType();
        //Liste des objets
        ArrayList<ReaderDataGuideActivity> valeur = gson.fromJson(jsonFileString, listUserType);
        //chemin des ressour via objet
        Resources resources = this.getResources();
        //remplace les valeur par les valeur du fichier json
        for (int i = 0; i < valeur.size(); i++) {
            //position image dans les ressources
            final int resourceId = resources.getIdentifier(valeur.get(i).getjRegion(), "drawable", this.getPackageName());
            //récupére la position de l'image en integer
            mRegionIcon.add(resourceId);
            //récupére la position du text
            mSurvieTypeText.add(valeur.get(i).getjTextTitre());
            //récupére la position du text
            mSurvieTypeTextDetail.add(valeur.get(i).getjTexteDetail());
            }
    }

    public void startactivityselected(int stat){

        // instance d'information de l'activité transmet des données d'une activité a une autre
        Intent intent = new Intent(GuideActivity.this, ActivitySlide.class);
        //choix en fonction de position de la recyclerview
        switch (stat){
            case 0 :
                //debut de l'activité plus transmission clé/valeur
                intent.putExtra("JsonFile",datatype);
                //debut de l'activité plus transmission clé/valeur
                intent.putExtra("index","24");
                //debut de l'activité
                startActivity(intent);
                break;
            case 1 :
                //debut de l'activité plus transmission clé/valeur
                intent.putExtra("JsonFile",datatype);
                //debut de l'activité plus transmission clé/valeur
                intent.putExtra("index","72");
                //debut de l'activité
                startActivity(intent);
                break;
            case 2 :
                //debut de l'activité plus transmission clé/valeur
                intent.putExtra("JsonFile",datatype);
                //debut de l'activité plus transmission clé/valeur
                intent.putExtra("index","BonASavoir");
                //debut de l'activité
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
