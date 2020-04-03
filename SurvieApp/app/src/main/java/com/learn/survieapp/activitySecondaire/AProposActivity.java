package com.learn.survieapp.activitySecondaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.learn.survieapp.R;

public class AProposActivity extends AppCompatActivity {

    String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_propos);

        Intent srcintent = getIntent();
        ImageView imageapropos = findViewById(R.id.aproposimage);
        TextView texteapropos = findViewById(R.id.texteapropos);

        genre = srcintent.getStringExtra("Genre");

        switch (genre){
            case "Homme":
                imageapropos.setImageResource(R.drawable.homme);
                texteapropos.setText(textHomme());
                break;
            case "Femme":
                imageapropos.setImageResource(R.drawable.femme);
                texteapropos.setText(textHomme());
                break;
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
