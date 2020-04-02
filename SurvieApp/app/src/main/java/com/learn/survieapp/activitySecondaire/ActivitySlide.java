package com.learn.survieapp.activitySecondaire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.survieapp.R;
import com.learn.survieapp.adaptateurRecyclerView.SliderAdapter;
import com.learn.survieapp.readDataClass.SpeakBot;

import java.util.Locale;

public class ActivitySlide extends AppCompatActivity {

    /**
     * Les variables ci-dessous sont utilisé pour le Speakbot
     */
    TextToSpeech toSpeech;
    int result;
    String sIndex;

    private ViewPager m24Slideview;
    private LinearLayout m24linearLayout;
    private SliderAdapter m24sliderAdapter;
    private TextView[] m24Index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_all_activity_scrollview);

        Intent srcintent = getIntent();
        sIndex = srcintent.getStringExtra("INDEX");





        m24linearLayout = findViewById(R.id.linearlayoutid);
        m24Slideview = findViewById(R.id.viewpageid);



        int[] slide_image  ={

                R.drawable.regiondesert,
                R.drawable.regionmontagne,
                R.drawable.regionforet,
        };
        String[] slide_title={

                "toto",
                "toto",
                "toto"
        };
        String[] slide_descrip ={

                "toto",
                "toto",
                "toto"
        };

        m24sliderAdapter = new SliderAdapter(this,slide_image,slide_title,slide_descrip);

        m24Slideview.setAdapter(m24sliderAdapter);


        indicateurposition();

    }

    public void indicateurposition(){


        m24Index = new TextView[3];

        for (int i = 0; i < m24Index.length ; i++) {

            m24Index[i] = new TextView(this);
            m24Index[i].setText(Html.fromHtml("#1B4A5B"));
            m24Index[i].setTextSize(35);
            m24Index[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            m24linearLayout.addView(m24Index[i]);

        }
    }
}
