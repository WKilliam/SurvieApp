package com.learn.survieapp.activitySecondaire;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.learn.survieapp.R;
import com.learn.survieapp.readDataClass.TranslateBot;
import java.util.ArrayList;

public class MorseTranslateActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;

    
    ArrayList<MediaPlayer> listMedia =new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.morsetranslate);

        Intent srcintent = getIntent();
        String typage = srcintent.getStringExtra("Typage");

        if(typage.equals("Morse")){

            findViewById(R.id.buttonsos).setVisibility(View.INVISIBLE);

        }else if(typage.equals("SOS")){
            findViewById(R.id.submitmorse).setVisibility(View.INVISIBLE);
            findViewById(R.id.edittextmorse).setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.submitmorse:


                TextView textconvert = findViewById(R.id.edittextmorse);

                String s = textconvert.getText().toString();

                TranslateBot translateBot = new TranslateBot();

                ArrayList<String> strings = translateBot.textAdapte(s);

                ArrayList<Integer> integers = translateBot.soundCreate(strings);

                for (int i = 0; i < integers.size(); i++) {

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), integers.get(i));
                    listMedia.add(mediaPlayer);
                }
                for (int i = 0; i <listMedia.size() ; i++) {

                    try {
                        listMedia.get(i).start();
                        Thread.sleep(listMedia.get(i).getDuration()*2);
                        Log.i("test ","=========== "+i+" =========="+listMedia.get(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                listMedia.clear();
                break;
            case R.id.buttonsos:

                try {

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.fatal);
                    mediaPlayer.start();
                    Thread.sleep(mediaPlayer.getDuration());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }
}
