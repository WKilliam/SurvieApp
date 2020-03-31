package com.learn.survieapp.adaptateurRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learn.survieapp.R;

import java.util.ArrayList;

public class RecyclerViewAdapted1 extends RecyclerView.Adapter<RecyclerViewAdapted1.ViewHolder> {

    ArrayList<String> valeurRegion;
    ArrayList<Integer> valeurRegionImageview;
    ArrayList<String> valeurSurvie;
    ArrayList<String> valeurNourriture;
    ArrayList<String> valeurEau;
    ArrayList<String> valeurPlante;
    LayoutInflater inflater;

    public RecyclerViewAdapted1(Context ctx,
                                ArrayList<String> valeurRegion,
                                ArrayList<Integer> valeurRegionImageview,
                                ArrayList<String> valeurSurvie,
                                ArrayList<String> valeurNourriture,
                                ArrayList<String> valeurEau,
                                ArrayList<String> valeurPlante) {
        this.valeurRegion = valeurRegion;
        this.valeurRegionImageview = valeurRegionImageview;
        this.valeurSurvie = valeurSurvie;
        this.valeurNourriture = valeurNourriture;
        this.valeurEau = valeurEau;
        this.valeurPlante = valeurPlante;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public RecyclerViewAdapted1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.firstactivitycustum,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapted1.ViewHolder holder, int position) {
        holder.textTitreRegion.setText(valeurRegion.get(position));
        holder.valeurSurvie.setText(valeurSurvie.get(position));
        holder.iconViewRegion.setImageResource(valeurRegionImageview.get(position));

        //holder.valeurSurvie.setText(valeurSurvie.get(position));
        holder.valeurNourriture.setText(valeurNourriture.get(position));
        holder.valeurEau.setText(valeurEau.get(position));
        holder.valeurPlante.setText(valeurPlante.get(position));
    }

    @Override
    public int getItemCount() {
        return valeurRegion.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iconViewRegion;
        TextView textTitreRegion;
        TextView valeurSurvie;
        TextView valeurEau;
        TextView valeurNourriture;
        TextView valeurPlante;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconViewRegion = itemView.findViewById(R.id.imageregiondesurvie);
            textTitreRegion = itemView.findViewById(R.id.texttyperegion);
            valeurSurvie = itemView.findViewById(R.id.valeurchancesurvie);
            valeurEau = itemView.findViewById(R.id.valeurchanceeau);
            valeurPlante = itemView.findViewById(R.id.valeurchanceplante);
            valeurNourriture = itemView.findViewById(R.id.valeurchancenourriture);
        }
    }
}
