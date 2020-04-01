package com.learn.survieapp.activitySecondaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.learn.survieapp.R;
import com.learn.survieapp.readDataClass.SpeakBot;

import java.util.Locale;

public class BookGuideActivity extends AppCompatActivity {

    /**
     * Les variables ci-dessous sont utilisé pour le Speakbot
     */
    TextToSpeech toSpeech;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_guide);
        bot();

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
                        toSpeech.speak(bot.tutoriel(), TextToSpeech.QUEUE_FLUSH, null);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "no mec", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
