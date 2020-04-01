package com.learn.survieapp.activityfallow;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.learn.survieapp.R;

import java.util.Locale;

public class PropotypeActivity extends AppCompatActivity {

    TextToSpeech toSpeech;
    int result;
    String text;
    EditText editText;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propotype);
        Log.i("takeid","image"+R.drawable.regionforet);//2131099783
        Log.i("takeid","image"+R.drawable.regionmontagne);//2131099784
        Log.i("takeid","image"+R.drawable.regiondesert);//
    }
}
