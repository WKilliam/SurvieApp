package com.learn.survieapp.adaptateurRecyclerView;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.learn.survieapp.R;


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    int[] slide_image;
    String[] slide_title;
    String[] slide_descrip;


    public SliderAdapter(Context context,
                         int[] slide_image,
                         String[] slide_title,
                         String[] slide_descrip) {
        this.context = context;
        this.slide_image = slide_image;
        this.slide_title = slide_title;
        this.slide_descrip = slide_descrip;
    }

    @Override
    public int getCount() {
        return slide_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view== (RelativeLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.linary_layout_page_view,container,false);

        ImageView slideImage = view.findViewById(R.id.imageviewsliderad);
        TextView textetitre = view.findViewById(R.id.texteslidertitre);
        TextView textdetaill = view.findViewById(R.id.texteslider);


        slideImage.setImageResource(slide_image[position]);
        textetitre.setText(slide_title[position]);
        textdetaill.setText(slide_descrip[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
