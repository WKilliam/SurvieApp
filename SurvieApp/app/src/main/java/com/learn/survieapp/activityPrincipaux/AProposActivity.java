package com.learn.survieapp.activityPrincipaux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.survieapp.R;
import com.learn.survieapp.readDataClass.SpeakBot;

import java.util.Locale;

public class AProposActivity extends AppCompatActivity {

    /**
     * Les variables ci-dessous sont utilisé pour le Speakbot
     */
    TextToSpeech toSpeech;
    int result;
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
                bot();
                break;
            case "Femme":
                imageapropos.setImageResource(R.drawable.femme);
                texteapropos.setText(textHomme());
                bot();
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
    public void bot(){

        toSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = toSpeech.setLanguage(Locale.FRANCE);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Toast.makeText(getApplicationContext(),"probléme",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        SpeakBot bot = new SpeakBot();
                        if(genre.equals("Homme"))
                        {
                            toSpeech.speak(bot.aPHomme(), TextToSpeech.QUEUE_FLUSH, null);

                        }if(genre.equals("Femme")){
                        toSpeech.speak(bot.aPFemme(), TextToSpeech.QUEUE_FLUSH, null);
                    }

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "no mec", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
