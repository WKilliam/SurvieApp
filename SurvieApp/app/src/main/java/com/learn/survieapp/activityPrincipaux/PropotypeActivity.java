package com.learn.survieapp.activityPrincipaux;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import com.learn.survieapp.R;

public class PropotypeActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextToSpeech toSpeech;
    int result;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propotype);
        Log.i("takeid","image"+R.drawable.regionforet);//2131099783
        Log.i("takeid","image"+R.drawable.regionmontagne);//2131099784
        Log.i("takeid","image"+R.drawable.regiondesert);

        setTitle(R.string.app_name);

    }



    @Override
    public void onInit(int status) {

    }
}
