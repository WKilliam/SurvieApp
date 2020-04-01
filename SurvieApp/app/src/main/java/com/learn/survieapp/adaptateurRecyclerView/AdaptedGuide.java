package com.learn.survieapp.adaptateurRecyclerView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.survieapp.R;

import java.util.ArrayList;

public class AdaptedGuide extends RecyclerView.Adapter<AdaptedGuide.ViewHolderGuide>{

    /**
     * Les variables ci-dessous servent a récupéré des valeur pour le recylcerview
     */

    ArrayList<Integer> rIconCategorie= new ArrayList<>();
    ArrayList<Integer> rRegionIcon= new ArrayList<>();
    ArrayList<String> rSurvieTypeText= new ArrayList<>();
    ArrayList<String> rSurvieTypeTextDetail= new ArrayList<>();
    ArrayList<String> rVisibility= new ArrayList<>();
    OnNoteListener onNoteListener;
    LayoutInflater inflater;

    public AdaptedGuide(Context ctx,
                        ArrayList<Integer> rIconCategorie,
                        ArrayList<Integer> rRegionIcon,
                        ArrayList<String> rSurvieTypeText,
                        ArrayList<String> rSurvieTypeTextDetail,
                        ArrayList<String> rVisibility,
                        OnNoteListener onNoteListener)
    {
        this.rIconCategorie = rIconCategorie;
        this.rRegionIcon = rRegionIcon;
        this.rSurvieTypeText = rSurvieTypeText;
        this.rSurvieTypeTextDetail = rSurvieTypeTextDetail;
        this.rVisibility = rVisibility;
        this.onNoteListener = onNoteListener;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public AdaptedGuide.ViewHolderGuide onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custum_gestion_guide_activity,parent,false);
        return new ViewHolderGuide(view,onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptedGuide.ViewHolderGuide holder, int position) {
        holder.vIconCategorie.setImageResource(rIconCategorie.get(position));
        holder.vRegionIcon.setImageResource(rRegionIcon.get(position));
        holder.vSurvieTypeText.setText(rSurvieTypeText.get(position));
        holder.vSurvieTypeTextDetail.setText(rSurvieTypeTextDetail.get(position));
        //holder.vIconCategorie.setVisibility(0);
    }

    @Override
    public int getItemCount() {
        return rRegionIcon.size();
    }
    public class ViewHolderGuide extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView vIconCategorie;
        ImageView vRegionIcon;
        TextView vSurvieTypeText;
        TextView vSurvieTypeTextDetail;
        TextView vVisibility;
        OnNoteListener onNoteListener;


        public ViewHolderGuide(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            vIconCategorie = itemView.findViewById(R.id.imageindice);
            vRegionIcon = itemView.findViewById(R.id.imagedelaregionchoisie);
            vSurvieTypeText = itemView.findViewById(R.id.textdelaregionchoisie);
            vSurvieTypeTextDetail = itemView.findViewById(R.id.text2detailregionchoisie);
            //vVisibility = itemView.findViewById(R.id.imageindice);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
