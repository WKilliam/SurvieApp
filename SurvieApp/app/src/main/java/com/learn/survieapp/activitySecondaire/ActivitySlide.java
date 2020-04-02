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
import com.learn.survieapp.readDataClass.ReaderDataGuideActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;


public class ActivitySlide extends AppCompatActivity {


    TextToSpeech toSpeech;
    int result;
    private String sJsonFileData;
    private String sIndexData;

    private ViewPager sSlideview;
    private LinearLayout sLinearLayout;
    private SliderAdapter sSliderAdapter;
    private TextView[] sIndex;

    private String[] sDataValueInputTitle;
    private String[] sDataValueInputTextDetail;
    private int[] sDataValueInputImageview;

    private ArrayList<String> sTakedTitle;
    private ArrayList<String> sTakedDetail;
    private ArrayList<Integer> sTakedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_all_activity_scrollview);

        Intent srcintent = getIntent();
        sJsonFileData = srcintent.getStringExtra("JsonFile");
        sIndexData = srcintent.getStringExtra("index");


        sLinearLayout = findViewById(R.id.linearlayoutid);
        sSlideview = findViewById(R.id.viewpageid);






        jSonAction("Gelee.json","24");

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

        //transforme tous le json en String
        String jsonFileString = ReaderDataGuideActivity.getJsonFromAssets(getApplicationContext(), data);

        //Log.i("data", jsonFileString);

        // création de l'object Gson
        Gson gson = new Gson();

        // Représente un type générique T
        Type listUserType = new TypeToken<ArrayList<ReaderDataGuideActivity>>() {}.getType();

        //Liste des objets
        ArrayList<ReaderDataGuideActivity> valeur = gson.fromJson(jsonFileString, listUserType);

        String s = valeur.get(0).getJtab_24()[0];

        Log.i("data", String.valueOf(s));

        //remplace les valeur par les valeur du fichier json
        moveTab(caract,valeur);
    }

    public void moveTab(String carac,ArrayList<ReaderDataGuideActivity> valeur){

        Resources resources = this.getResources();


        switch (carac){
            case "24":
                sTakedTitle = new ArrayList();
                sTakedDetail = new ArrayList();
                sTakedImage = new ArrayList();
                for (int i = 0; i < valeur.get(0).getJtab_24().length ; i++) {
                    sTakedTitle.add(valeur.get(0).getJtab_24_Titre()[i]);
                    sTakedDetail.add(valeur.get(0).getJtab_24_Detail()[i]);
                    final int resourceId = resources.getIdentifier(valeur.get(0).getJtab_24()[i], "drawable", this.getPackageName());
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
            case "72":
                for (int i = 0; i <valeur.get(1).getJtab_72().length ; i++) {
                    sTakedTitle.add(valeur.get(1).getJtab_72_Titre()[i]);
                    sTakedDetail.add(valeur.get(1).getJtab_72_Detail()[i]);
                    final int resourceId = resources.getIdentifier(valeur.get(1).getJtab_72()[i], "drawable", this.getPackageName());
                    sTakedImage.add(resourceId);
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
            case "BonASavoir":
                for (int i = 0; i <valeur.get(2).getJtab_BA().length ; i++) {
                    sTakedTitle.add(valeur.get(2).getJtab_BA_Titre()[i]);
                    sTakedDetail.add(valeur.get(2).getJtab_BA_Detail()[i]);
                    final int resourceId = resources.getIdentifier(valeur.get(2).getJtab_72()[i], "drawable", this.getPackageName());
                    sTakedImage.add(resourceId);

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
}
