package com.learn.survieapp.activityPrincipaux;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learn.survieapp.R;
import com.learn.survieapp.readDataClass.ReadPrototype;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PropotypeActivity extends AppCompatActivity {

    ArrayList<String> string;
    int tab[];
    ArrayList test = new ArrayList();
    int getchecktabvalue;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        //Log.i("takeid","image"+R.drawable.regionforet);//2131099783
        //Log.i("takeid","image"+R.drawable.regionmontagne);//2131099784
        //Log.i("takeid","image"+R.drawable.regiondesert);

       jSonAction();

        Log.i("test","test data ====== >"+ test);

    }















    public void jSonAction(){

        Resources resources = this.getResources();

        //transforme tous le json en String
        String jsonFileString = ReadPrototype.getJsonFromAssetsPrototy(getApplicationContext(), "prototypeData.json");

        //Log.i("data", jsonFileString);

        // création de l'object Gson
        Gson gson = new Gson();

        // Représente un type générique T
        Type listUserType = new TypeToken<ArrayList<ReadPrototype>>() { }.getType();

        //Liste des objets
        ArrayList<ReadPrototype> valeur = gson.fromJson(jsonFileString, listUserType);




        // get resource id by image name
        final int resourceId = resources.getIdentifier(valeur.get(0).getImage_name(), "drawable", this.getPackageName());

        Log.i("image","=================== "+resourceId);
        // get drawable by resource id
        //Drawable drawable = resources.getDrawable(resourceId);
        //Log.i("drawable","test drawable = "+drawable);

        // get bitmap by resource id
        //Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), resourceId);

        //Log.i("bipmap","test bip map = "+bitmap);








        //remplace les valeur par les valeur du fichier json
        //for (int i = 0; i < valeur.size(); i++) {
          //  Log.i("data", "> Item " + i + "\n" + valeur.get(i));
            //for (int j = 0; j <valeur.get(i).getTab_image().length ; j++) {
             //   Log.i("data", "> valeur " + j + "\n" + valeur.get(i).getTab_image()[j]);
            //    test.add(valeur.get(i).getTab_image()[j]);
            //}
       // }
    }
}
