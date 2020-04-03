package com.learn.survieapp.activitySecondaire;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
import java.util.Objects;


public class ActivitySlide extends AppCompatActivity {


    private ViewPager sSlideview;
    private LinearLayout sLinearLayout;
    private SliderAdapter sSliderAdapter;
    private TextView[] sIndex;
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

        Intent srcintent = getIntent();
        sJsonFileData = srcintent.getStringExtra("JsonFile");
        sIndexData = srcintent.getStringExtra("index");

        Log.i("value input ","============"+sJsonFileData+"==============="+sIndexData+"=========================");
        sLinearLayout = findViewById(R.id.linearlayoutid);
        sSlideview = findViewById(R.id.viewpageid);

        jSonAction(sJsonFileData,sIndexData);

        sSliderAdapter = new SliderAdapter(this, sDataValueInputImageview, sDataValueInputTitle, sDataValueInputTextDetail);

        sSlideview.setAdapter(sSliderAdapter);
        indicateurposition();

    }

    public void indicateurposition() {


        sIndex = new TextView[3];

        for (int i = 0; i < sIndex.length; i++) {

            sIndex[i] = new TextView(this);
            sIndex[i].setText(Html.fromHtml("#1B4A5B"));
            sIndex[i].setTextSize(35);
            sIndex[i].setTextColor(getResources().getColor(R.color.colorAccent));
            sLinearLayout.addView(sIndex[i]);

        }
    }

    public void jSonAction(String data,String caract) {

        if(data.equals("ChampignionData.json")){
            //transforme tous le json en String
            String jsonFileString = ReaderChampignion.getJsonFromAssets(getApplicationContext(), data);

            //Log.i("data", jsonFileString);

            // création de l'object Gson
            Gson gson = new Gson();

            // Représente un type générique T
            Type listUserType = new TypeToken<ArrayList<ReaderChampignion>>() {}.getType();

            //Liste des objets
            ArrayList<ReaderChampignion> valeurC = gson.fromJson(jsonFileString, listUserType);

            Log.i("value input ","============"+caract+"==============="+data+"============= champi ===");

            moveTab(caract,null,valeurC,null);

        }else if(data.equals("Tuto.json")){

            //transforme tous le json en String
            String jsonFileString = ReaderTuto.getJsonFromAssets(getApplicationContext(), data);

            //Log.i("data", jsonFileString);

            // création de l'object Gson
            Gson gson = new Gson();

            // Représente un type générique T
            Type listUserType = new TypeToken<ArrayList<ReaderTuto>>() {}.getType();

            //Liste des objets
            ArrayList<ReaderTuto> valeurT = gson.fromJson(jsonFileString, listUserType);

            Log.i("value input ","============"+caract+"==============="+data+"============= tuto ===");

            moveTab(caract,null,null,valeurT);

        }else {
            //transforme tous le json en String
            String jsonFileString = ReaderDataGuideActivity.getJsonFromAssets(getApplicationContext(), data);

            Log.i("data", jsonFileString);

            // création de l'object Gson
            Gson gson = new Gson();

            // Représente un type générique T
            Type listUserType = new TypeToken<ArrayList<ReaderDataGuideActivity>>() {}.getType();

            //Liste des objets
            ArrayList<ReaderDataGuideActivity> valeurA = gson.fromJson(jsonFileString, listUserType);

            Log.i("value input ","============"+valeurA+"==============="+data+"============= All ===");

            moveTab(caract,valeurA,null,null);
        }
        //remplace les valeur par les valeur du fichier json

    }


    public void moveTab(String carac,ArrayList<ReaderDataGuideActivity> valeur,ArrayList<ReaderChampignion> valeur2,ArrayList<ReaderTuto> valeur3){

        Resources resources = this.getResources();
        sTakedTitle = new ArrayList();
        sTakedDetail = new ArrayList();
        sTakedImage = new ArrayList();

        switch (carac){
            case "24":

                functionFor24(valeur,sTakedTitle,sTakedImage,sTakedDetail,resources);
                break;
            case "72":
                functionFor72(valeur,sTakedTitle,sTakedImage,sTakedDetail,resources);
                break;
            case "BonASavoir":
                functionForBA(valeur,sTakedTitle,sTakedImage,sTakedDetail,resources);
                break;
            case "Champinion":
                functionForChampi(valeur2,sTakedTitle,sTakedImage,sTakedDetail,resources);
                break;
            case"tuto":
                for (int i = 0; i < valeur3.get(0).getTuto_Nom().length ; i++) {
                    sTakedTitle.add(valeur3.get(0).getTuto_Nom()[i]);
                    sTakedDetail.add(valeur3.get(0).getTuto_Detail()[i]);
                    final int resourceId = resources.getIdentifier(valeur3.get(0).getTuto_Image()[i], "drawable", this.getPackageName());
                    sTakedImage.add(resourceId);
                    Log.i("tableau ","Ressourcecheck = "+sTakedImage);
                }
                sDataValueInputTitle = new String[sTakedTitle.size()];
                sDataValueInputTextDetail= new String[sTakedDetail.size()];
                sDataValueInputImageview = new int[sTakedImage.size()];
                for (int i = 0; i <sTakedImage.size() ; i++) {
                    sDataValueInputTitle[i]= sTakedTitle.get(i);
                    sDataValueInputTextDetail[i]= sTakedDetail.get(i);;
                    sDataValueInputImageview[i]=sTakedImage.get(i);
                }
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


    public void functionForTuto(ArrayList<ReaderTuto> valeur,
                                ArrayList<String> titre,
                                ArrayList<Integer> image,
                                ArrayList<String> detail,
                                Resources resources){

        for (int i = 0; i < valeur.get(0).getTuto_Nom().length ; i++) {
           // Log.i("value input ","============"+valeur.get(0)+"==============="+ Arrays.toString(valeur.get(0).getJtab_24()) +"============= All ===");
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
}
