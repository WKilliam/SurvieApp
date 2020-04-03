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
import com.learn.survieapp.activitySecondaire.CompassActivity;
import com.learn.survieapp.activitySecondaire.MorseTranslateActivity;
import com.learn.survieapp.adaptateurRecyclerView.CouteauSuisseRCVAdapted;
import com.learn.survieapp.readDataClass.ReaderDataCouteauSuisse;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CouteauSuisseActivity extends AppCompatActivity implements CouteauSuisseRCVAdapted.OnNoteListener
{


    /**
     * @param csTextBox liste de string pour set les emplacements de TextView
     * @param csImageview liste de integer pour set les emplacements de ImageView
     * @param csRCVAdapted Recyclerview adapteur
     * @param csdata Recyclerview Stat
     */
    ArrayList<String> csTextBox = new ArrayList<>();
    ArrayList<Integer> csImageview = new ArrayList<>();
    CouteauSuisseRCVAdapted csRCVAdapted;
    RecyclerView csdata;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_recycler_view);

        // récupére l'id du recylerview
        csdata = findViewById(R.id.recviewgestionactivity);
        // fonction de gestion json
        jSonAction();
        // lancement du recyclerview
        csRCVAdapted = new CouteauSuisseRCVAdapted(this, csImageview, csTextBox,this);
        // permet de mettre le recyclerview dans une grille de 1
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        //orientation de la vue
        csdata.setLayoutManager(gridLayoutManager);
        csdata.setAdapter(csRCVAdapted);

    }

    /**
     * traitement du fichier json
     */
    public void jSonAction(){

        //transforme tous le json en String
        String jsonFileString = ReaderDataCouteauSuisse.getJsonFromAssets(getApplicationContext(), "DataCouteauSuisse.json");
        // création de l'object Gson
        Gson gson = new Gson();
        // Représente un type générique T
        Type listUserType = new TypeToken<ArrayList<ReaderDataCouteauSuisse>>() { }.getType();
        //Liste des objets
        ArrayList<ReaderDataCouteauSuisse> valeur = gson.fromJson(jsonFileString, listUserType);
        //chemin des ressour via objet
        Resources resources = this.getResources();
        //remplace les valeur par les valeur du fichier json
        for (int i = 0; i < valeur.size(); i++) {
            //position image dans les ressources
            final int resourceId = resources.getIdentifier(valeur.get(i).getIcon_outil(), "drawable", this.getPackageName());
            //récupére la position de l'image en integer
            csImageview.add(resourceId);
            //récupére la position du text
            csTextBox.add(valeur.get(i).getData_Text_Type_Icon());
        }
    }

    /**
     * function de récupération de la cellule en position dans le recyclerview
     * @param position  position cellule
     */
    @Override
    public void onNoteClick(int position)
    {
        // instance d'information de l'activité transmet des données d'une activité a une autre
        Intent intentcommun = new Intent(CouteauSuisseActivity.this, ActivitySlide.class);
        Intent intenttranslate = new Intent(CouteauSuisseActivity.this, MorseTranslateActivity.class);

        //choix en fonction de position de la recyclerview
        switch (position){
            case 0://boussole
                // instance d'information de l'activité transmet des données d'une activité a une autre
                Intent intentcompas = new Intent(CouteauSuisseActivity.this, CompassActivity.class);
                //debut de l'activité
                startActivity(intentcompas);
                break;
            case 1://champignion
                //debut de l'activité plus transmission clé/valeur
                intentcommun.putExtra("JsonFile","ChampignionData.json");
                intentcommun.putExtra("index","Champinion");
                startActivity(intentcommun);
                break;
            case 2://translate
                //debut de l'activité plus transmission clé/valeur
                intenttranslate.putExtra("Typage","Morse");
                startActivity(intenttranslate);
                break;
            case 3://sos
                //debut de l'activité plus transmission clé/valeur
                intenttranslate.putExtra("Typage","SOS");
                startActivity(intenttranslate);
                break;
            default:
                break;
        }
    }
}
