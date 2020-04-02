package com.learn.survieapp.activitySecondaire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.learn.survieapp.R;
import com.learn.survieapp.adaptateurRecyclerView.SliderAdapter;

public class Activity24heure extends AppCompatActivity {

    private ViewPager mSlideview;
    private LinearLayout mlinearLayout;
    private SliderAdapter msliderAdapter;
    private TextView[] mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_all_activity_scrollview);

        mlinearLayout = findViewById(R.id.linearlayoutid);
        mSlideview = findViewById(R.id.viewpageid);

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

        msliderAdapter = new SliderAdapter(this,slide_image,slide_title,slide_descrip);

        mSlideview.setAdapter(msliderAdapter);

        indicateurposition();

    }

    public void indicateurposition(){

        mIndex = new TextView[3];

        for (int i = 0; i <mIndex.length ; i++) {

            mIndex[i] = new TextView(this);
            mIndex[i].setText(Html.fromHtml("&#8226"));
            mIndex[i].setTextSize(35);
            mIndex[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            mlinearLayout.addView(mIndex[i]);

        }
    }
}
