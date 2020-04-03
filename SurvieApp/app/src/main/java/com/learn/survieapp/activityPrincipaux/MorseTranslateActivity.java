package com.learn.survieapp.activityPrincipaux;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
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

            default:
                break;
        }

    }
}
