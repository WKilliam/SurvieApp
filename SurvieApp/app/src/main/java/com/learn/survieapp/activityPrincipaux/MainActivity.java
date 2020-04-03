package com.learn.survieapp.activityPrincipaux;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.activitySecondaire.AProposActivity;
import com.learn.survieapp.activitySecondaire.ActivitySlide;
import com.learn.survieapp.adaptateurRecyclerView.FristActRVAdapted;
import com.learn.survieapp.readDataClass.ReaderData;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FristActRVAdapted.OnNoteListener{


    /**
     * Les variables ci-dessous servent à récupéré des valeurs bien presice pour l'affichage du recyclerview
     * @param mTextRegion liste de string pour set les emplacements de TextView
     * @param mTauxSurvie liste de string pour set les emplacements de TextView
     * @param mTauxPlante liste de string pour set les emplacements de TextView
     * @param mTauxDeshydra liste de string pour set les emplacements de TextView
     * @param mRegionImageview liste de integer pour set les emplacements de ImageView
     * @param mLinkData liste de string pour donner des valeurs de data json
     * @param csRCVAdapted Recyclerview adapteur
     * @param csdata Recyclerview Stat
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
        // fonction de gestion json
        jSonAction();
        // récupére l'id du recylerview
        data = findViewById(R.id.gestionrecyclerview);
        // lancement du recyclerview
        mfristActRVAdapted = new FristActRVAdapted(this, mTextRegion,mRegionImageview,mTauxSurvie,mTauxDeshydra,mTauxPlante,this);
        // permet de mettre le recyclerview dans une grille de 1
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        //orientation de la vue
        data.setLayoutManager(gridLayoutManager);
        data.setAdapter(mfristActRVAdapted);
    }

    /**
     * function de récupération de la cellule en position dans le recyclerview
     * @param position  position cellule
     */
    @Override
    public void onNoteClick(int position) {
        //recupere la string dans une liste
        String region = mLinkData.get(position);
        //transforme la string avec un .json pour avoir la data
        String dataTake = region+".json";
        // instance d'information de l'activité transmet des données d'une activité a une autre
        Intent intentinfos = new Intent(MainActivity.this,GuideActivity.class);
        //debut de l'activité plus transmission clé/valeur
        intentinfos.putExtra("DataType",dataTake);
        //debut de l'activité
        startActivity(intentinfos);
    }
    /**
     * traitement du fichier json
     */
    public void jSonAction(){

        //transforme tous le json en String
        String jsonFileString = ReaderData.getJsonFromAssets(getApplicationContext(), "Data.json");
        // création de l'object Gson
        Gson gson = new Gson();
        // Représente un type générique T
        Type listUserType = new TypeToken<ArrayList<ReaderData>>() { }.getType();
        //Liste des objets
        ArrayList<ReaderData> valeur = gson.fromJson(jsonFileString, listUserType);
        //chemin des ressour via objet
        Resources resources = this.getResources();
        //remplace les valeur par les valeur du fichier json
        for (int i = 0; i < valeur.size(); i++) {
            //ajoute une string de type json ex : type.json
            mLinkData.add(valeur.get(i).getdLinkData());
            //ajoute le text au tableau
            mTextRegion.add(valeur.get(i).getDmilieu_Region_Data());
            //récupére la position de l'image en integer
            final int resourceId = resources.getIdentifier(valeur.get(i).getDvaleur_Region_Imageview_Data(), "drawable", this.getPackageName());
            //ajoute l'integer de l'image voulu
            mRegionImageview.add(resourceId);
            //ajoute le text au tableau
            mTauxSurvie.add(valeur.get(i).getDtaux_Survie_Data());
            //ajoute le text au tableau
            mTauxDeshydra.add(valeur.get(i).getDtaux_Eau_Data());
            //ajoute le text au tableau
            mTauxPlante.add(valeur.get(i).getDtaux_Plante_Data());
        }
    }

    /**
     * Paramettre onclick permet de récupéré tous les onclick effectuer sur l'activité présente
     * @param v
     */
    @Override
    public void onClick(View v) {

        //choix en fonction de v.getId()
        switch (v.getId()){
            case R.id.gestionvoyageurimage://a propos page
                // Cree une alertedialog obejt
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // set l'alertedialog obejt
                builder.setTitle("Vous etez ?");
                // cree une liste de choix en string pour les transmettre
                String[] difficult = {"Un Aventurier","Une Aventuriére"};
                // permet de set en fonction de la liste de choix pour afficher en boutton les choix
                builder.setItems(difficult, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // instance d'information de l'activité transmet des données d'une activité a une autre
                        Intent intentGenre = new Intent(MainActivity.this, AProposActivity.class);
                        //choix en fonction du which
                        switch (which)
                        {
                            case 0: // Un Aventurier
                                //debut de l'activité plus transmission clé/valeur
                                intentGenre.putExtra("Genre","Homme");
                                startActivity(intentGenre);
                                break;
                            case 1:// Un Aventuriére
                                //debut de l'activité plus transmission clé/valeur
                                intentGenre.putExtra("Genre","Femme");
                                startActivity(intentGenre);
                                break;
                            default:
                                break;
                        }
                    }
                });
                //ferme l'alerte dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                break;

            case R.id.mstartactivitycouteausuisse://couteaussuise
                // instance d'information de l'activité transmet des données d'une activité a une autre
                Intent intentcouteausuisse = new Intent(MainActivity.this, CouteauSuisseActivity.class);
                //debut de l'activité
                startActivity(intentcouteausuisse);
                break;
            case R.id.imageViewtutoriel:
                Intent intenttuto = new Intent(MainActivity.this, ActivitySlide.class);
                //debut de l'activité plus transmission clé/valeur
                intenttuto.putExtra("JsonFile","Tuto.json");
                //debut de l'activité plus transmission clé/valeur
                intenttuto.putExtra("index","tuto");
                //debut de l'activité
                startActivity(intenttuto);
                break;
            default:
                break;
        }
    }
}
