package com.learn.survieapp.activityfallow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.learn.survieapp.R;

public class PropotypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propotype);
        Log.i("takeid","image"+R.drawable.iconpetitcompas);//2131099760
        Log.i("takeid","image"+R.drawable.iconpetitecarte);//2131099761
        Log.i("takeid","image"+R.drawable.iconechampigion);//2131099757
    }
}
