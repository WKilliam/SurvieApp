package com.learn.survieapp.activityfallow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.survieapp.R;

public class AProposActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_propos);

        Intent srcintent = getIntent();
        ImageView imageapropos = findViewById(R.id.aproposimage);
        TextView texteapropos = findViewById(R.id.texteapropos);

        String genre = srcintent.getStringExtra("Genre");

        if(genre.equals("Homme"))
        {
            imageapropos.setImageResource(R.drawable.homme);
            texteapropos.setText(textHomme());

        }if(genre.equals("Femme")){
            imageapropos.setImageResource(R.drawable.femme);
            texteapropos.setText(textHomme());
        }
    }

    public String textHomme(){
        return "Bonjour aventurier "+"\n"+
                "ton périple arrive à ca fin"+"\n"+
                " ou du moins te voici sur la page à propos "+"\n"+
                "Teddy";
    }
    public String textFemme(){
        return "Bonjour aventurière "+"\n"+
                "ton périple arrive à ca fin"+"\n"+
                " ou du moins te voici sur la page à propos "+"\n"+
                "Teddy";
    }
}
