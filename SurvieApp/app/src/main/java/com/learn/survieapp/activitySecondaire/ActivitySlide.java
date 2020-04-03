package com.learn.survieapp.activitySecondaire;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.adaptateurRecyclerView.SliderAdapter;
import com.learn.survieapp.readDataClass.ReaderChampignion;
import com.learn.survieapp.readDataClass.ReaderDataGuideActivity;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;


public class ActivitySlide extends AppCompatActivity {

    /**
     * variable de gestion de la modification des slid
     */
    private ViewPager sSlideview;
    private LinearLayout sLinearLayout;
    private SliderAdapter sSliderAdapter;
    String sJsonFileData;
    String sIndexData;
    private String[] sDataValueInputTitle;
    private String[] sDataValueInputTextDetail;
    private int[] sDataValueInputImageview;
    private ArrayList<String> sTakedTitle= new ArrayList();
    private ArrayList<String> sTakedDetail= new ArrayList();
    private ArrayList<Integer> sTakedImage= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_all_activity_scrollview);

        //donnee recu d'une autre activité
        Intent srcintent = getIntent();
        //donnee recu d'une autre activité en format clé/valeur
        sJsonFileData = srcintent.getStringExtra("JsonFile");
        //donnee recu d'une autre activité en format clé/valeur
        sIndexData = srcintent.getStringExtra("index");
        // récupére l'id du slide
        sLinearLayout = findViewById(R.id.linearlayoutid);
        // récupére l'id du slide
        sSlideview = findViewById(R.id.viewpageid);
        //fonction gestion json
        jSonAction(sJsonFileData,sIndexData);
        //slide activateur
        sSliderAdapter = new SliderAdapter(this, sDataValueInputImageview, sDataValueInputTitle, sDataValueInputTextDetail);
        sSlideview.setAdapter(sSliderAdapter);
    }

    /**
     * gestion lecture fichier json
     * @param data nom du fichier
     * @param caract type de clé status
     */
    public void jSonAction(String data,String caract) {

        if(data.equals("ChampignionData.json")){
            //transforme tous le json en String
            String jsonFileString = ReaderChampignion.getJsonFromAssets(getApplicationContext(), data);
            // création de l'object Gson
            Gson gson = new Gson();
            // Représente un type générique T
            Type listUserType = new TypeToken<ArrayList<ReaderChampignion>>() {}.getType();
            //Liste des objets
            ArrayList<ReaderChampignion> valeurC = gson.fromJson(jsonFileString, listUserType);
            //fonction de catégorisation de lecture slide
            moveTab(caract,null,valeurC,null);

        }else if(data.equals("Tuto.json")){

            //transforme tous le json en String
            String jsonFileString = ReaderTuto.getJsonFromAssets(getApplicationContext(), data);
            // création de l'object Gson
            Gson gson = new Gson();
            // Représente un type générique T
            Type listUserType = new TypeToken<ArrayList<ReaderTuto>>() {}.getType();
            //Liste des objets
            ArrayList<ReaderTuto> valeurT = gson.fromJson(jsonFileString, listUserType);
            //fonction de catégorisation de lecture slide
            moveTab(caract,null,null,valeurT);

        }else {
            //transforme tous le json en String
            String jsonFileString = ReaderDataGuideActivity.getJsonFromAssets(getApplicationContext(), data);
            // création de l'object Gson
            Gson gson = new Gson();
            // Représente un type générique T
            Type listUserType = new TypeToken<ArrayList<ReaderDataGuideActivity>>() {}.getType();
            //Liste des objets
            ArrayList<ReaderDataGuideActivity> valeurA = gson.fromJson(jsonFileString, listUserType);
            //fonction de catégorisation de lecture slide
            moveTab(caract,valeurA,null,null);
        }
    }


    public void moveTab(String carac,ArrayList<ReaderDataGuideActivity> valeur,ArrayList<ReaderChampignion> valeur2,ArrayList<ReaderTuto> valeur3){

        Resources resources = this.getResources();
        //list de stocage de donnée
        sTakedTitle = new ArrayList();
        sTakedDetail = new ArrayList();
        sTakedImage = new ArrayList();

        //catécorisation des données
        switch (carac){
            case "24":
                //function de catécorisation des données
                functionFor24(valeur,sTakedTitle,sTakedImage,sTakedDetail,resources);
                break;
            case "72":
                //function de catécorisation des données
                functionFor72(valeur,sTakedTitle,sTakedImage,sTakedDetail,resources);
                break;
            case "BonASavoir":
                //function de catécorisation des données
                functionForBA(valeur,sTakedTitle,sTakedImage,sTakedDetail,resources);
                break;
            case "Champinion":
                //function de catécorisation des données
                functionForChampi(valeur2,sTakedTitle,sTakedImage,sTakedDetail,resources);
                break;
            case"tuto":
                //function de catécorisation des données
                functionForTuto(valeur3,sTakedTitle,sTakedImage,sTakedDetail,resources);
                break;
            default:
                break;
        }
    }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }

    /**
     * Les 5 fonctions suivante font tous les mêmes action avec un stat différent
     */

    /**
     *
     * @param valeur La valeur represente la lecture json en fonction du Reader
     * @param titre recupére les titres en string
     * @param image recupére les titres en integer
     * @param detail recupére les titres en string
     * @param resources recupére l'objet ressource
     */
    public void functionFor24(ArrayList<ReaderDataGuideActivity> valeur,
                              ArrayList<String> titre,
                              ArrayList<Integer> image,
                              ArrayList<String> detail,
                              Resources resources){

        for (int i = 0; i < valeur.get(0).getJtab_24().length ; i++) {
            Log.i("value input ","============"+valeur.get(0)+"==============="+ Arrays.toString(valeur.get(0).getJtab_24()) +"============= All ===");
            titre.add(valeur.get(0).getJtab_24_Titre()[i]);
            detail.add(valeur.get(0).getJtab_24_Detail()[i]);
            final int resourceId = resources.getIdentifier(valeur.get(0).getJtab_24()[i], "drawable", this.getPackageName());
            image.add(resourceId);
        }
        sDataValueInputTitle = new String[titre.size()];
        sDataValueInputTextDetail= new String[detail.size()];
        sDataValueInputImageview = new int[image.size()];
        for (int i = 0; i <sTakedImage.size() ; i++) {
            sDataValueInputTitle[i]= titre.get(i);
            sDataValueInputTextDetail[i]= detail.get(i);;
            sDataValueInputImageview[i]=image.get(i);
        }
    }

    public void functionFor72(ArrayList<ReaderDataGuideActivity> valeur,
                                ArrayList<String> titre,
                                ArrayList<Integer> image,
                                ArrayList<String> detail,
                                Resources resources){

        for (int i = 0; i < valeur.get(1).getJtab_72().length ; i++) {
            titre.add(valeur.get(1).getJtab_72_Titre()[i]);
            detail.add(valeur.get(1).getJtab_72_Detail()[i]);
            final int resourceId = resources.getIdentifier(valeur.get(1).getJtab_72()[i], "drawable", this.getPackageName());
            image.add(resourceId);
        }
        sDataValueInputTitle = new String[titre.size()];
        sDataValueInputTextDetail= new String[detail.size()];
        sDataValueInputImageview = new int[image.size()];
        for (int i = 0; i <sTakedImage.size() ; i++) {
            sDataValueInputTitle[i]= titre.get(i);
            sDataValueInputTextDetail[i]= detail.get(i);;
            sDataValueInputImageview[i]=image.get(i);
        }
    }

    public void functionForBA(ArrayList<ReaderDataGuideActivity> valeur,
                              ArrayList<String> titre,
                              ArrayList<Integer> image,
                              ArrayList<String> detail,
                              Resources resources){

        for (int i = 0; i < valeur.get(2).getJtab_BA().length ; i++) {
            titre.add(valeur.get(2).getJtab_BA_Titre()[i]);
            detail.add(valeur.get(2).getJtab_BA_Detail()[i]);
            final int resourceId = resources.getIdentifier(valeur.get(2).getJtab_BA()[i], "drawable", this.getPackageName());
            image.add(resourceId);
        }
        sDataValueInputTitle = new String[titre.size()];
        sDataValueInputTextDetail= new String[detail.size()];
        sDataValueInputImageview = new int[image.size()];
        for (int i = 0; i <sTakedImage.size() ; i++) {
            sDataValueInputTitle[i]= titre.get(i);
            sDataValueInputTextDetail[i]= detail.get(i);;
            sDataValueInputImageview[i]=image.get(i);
        }
    }


    public void functionForChampi(ArrayList<ReaderChampignion> valeur,
                              ArrayList<String> titre,
                              ArrayList<Integer> image,
                              ArrayList<String> detail,
                              Resources resources){

        for (int i = 0; i < valeur.get(0).getChampignion_Nom().length ; i++) {
            titre.add(valeur.get(0).getChampignion_Nom()[i]);
            detail.add(valeur.get(0).getChampignion_Detail()[i]);
            final int resourceId = resources.getIdentifier(valeur.get(0).getChampignion_Image()[i], "drawable", this.getPackageName());
            image.add(resourceId);
        }
        sDataValueInputTitle = new String[titre.size()];
        sDataValueInputTextDetail= new String[detail.size()];
        sDataValueInputImageview = new int[image.size()];
        for (int i = 0; i <sTakedImage.size() ; i++) {
            sDataValueInputTitle[i]= titre.get(i);
            sDataValueInputTextDetail[i]= detail.get(i);;
            sDataValueInputImageview[i]=image.get(i);
        }
    }

    public void functionForTuto(ArrayList<ReaderTuto> valeur,
                                ArrayList<String> titre,
                                ArrayList<Integer> image,
                                ArrayList<String> detail,
                                Resources resources){

        for (int i = 0; i < valeur.get(0).getTuto_Nom().length ; i++) {
            titre.add(valeur.get(0).getTuto_Nom()[i]);
            detail.add(valeur.get(0).getTuto_Detail()[i]);
            final int resourceId = resources.getIdentifier(valeur.get(0).getTuto_Image()[i], "drawable", this.getPackageName());
            image.add(resourceId);
        }
        sDataValueInputTitle = new String[titre.size()];
        sDataValueInputTextDetail= new String[detail.size()];
        sDataValueInputImageview = new int[image.size()];
        for (int i = 0; i <sTakedImage.size() ; i++) {
            sDataValueInputTitle[i]= titre.get(i);
            sDataValueInputTextDetail[i]= detail.get(i);;
            sDataValueInputImageview[i]=image.get(i);
        }
    }
}
