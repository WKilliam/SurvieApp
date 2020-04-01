package com.learn.survieapp.activityfallow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.learn.survieapp.R;
import com.learn.survieapp.adaptateurRecyclerView.AdaptedGuide;
import com.learn.survieapp.adaptateurRecyclerView.RecyclerViewAdapted1;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity implements  AdaptedGuide.OnNoteListener{

    ArrayList<Integer> mIconCategorie= new ArrayList<>();
    ArrayList<Integer> mRegionIcon= new ArrayList<>();
    ArrayList<String> mSurvieTypeText= new ArrayList<>();
    ArrayList<String> mSurvieTypeTextDetail= new ArrayList<>();
    ArrayList<String> mVisibility= new ArrayList<>();
    AdaptedGuide adaptedGuide;
    RecyclerView recyclerViewguide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        recyclerViewguide = findViewById(R.id.recviewgestionactivity);

        int visible = View.VISIBLE;
        Log.i("visibilite","test"+visible);

        mIconCategorie.add(R.drawable.iconblackplante);
        mRegionIcon.add(R.drawable.regionmontagne);
        mSurvieTypeText.add("toto");
        mSurvieTypeTextDetail.add("tata");
        mVisibility.add("test");

        mIconCategorie.add(R.drawable.iconblackplante);
        mRegionIcon.add(R.drawable.regionforet);
        mSurvieTypeText.add("toto");
        mSurvieTypeTextDetail.add("tata");
        mVisibility.add("test");

        mIconCategorie.add(R.drawable.iconblackplante);
        mRegionIcon.add(R.drawable.regiondesert);
        mSurvieTypeText.add("toto");
        mSurvieTypeTextDetail.add("tata");
        mVisibility.add("test");

        adaptedGuide = new AdaptedGuide(this,
                                            mIconCategorie,
                                            mRegionIcon,
                                            mSurvieTypeText,
                                            mSurvieTypeTextDetail,
                                            mVisibility,
                                            this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerViewguide.setLayoutManager(gridLayoutManager);
        recyclerViewguide.setAdapter(adaptedGuide);
    }

    @Override
    public void onNoteClick(int position) {

    }
}
