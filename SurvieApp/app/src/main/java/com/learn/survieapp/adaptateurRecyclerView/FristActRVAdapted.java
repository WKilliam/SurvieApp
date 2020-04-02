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

public class FristActRVAdapted extends RecyclerView.Adapter<FristActRVAdapted.ViewHolder> {

    /**
     * Les variables ci-dessous servent a récupéré des valeur pour le recylcerview
     */
    ArrayList<String> rvTextRegion;
    ArrayList<Integer> rvRegionImageview;
    ArrayList<String> rvSurvie;
    ArrayList<String> valeurEau;
    ArrayList<String> valeurPlante;
    OnNoteListener onNoteListener;
    LayoutInflater inflater;

    public FristActRVAdapted(Context ctx,
                             ArrayList<String> rvTextRegion,
                             ArrayList<Integer> rvRegionImageview,
                             ArrayList<String> rvSurvie,
                             ArrayList<String> valeurEau,
                             ArrayList<String> valeurPlante,
                             OnNoteListener onNoteListener)
    {
        this.rvTextRegion = rvTextRegion;
        this.rvRegionImageview = rvRegionImageview;
        this.rvSurvie = rvSurvie;
        this.valeurEau = valeurEau;
        this.valeurPlante = valeurPlante;
        this.onNoteListener = onNoteListener;
        this.inflater = LayoutInflater.from(ctx);
    }




    /**
     * Changeur de vue récupére un XML pour l'utilisé comme cellule du recyclerview
     * @return
     */
    @NonNull
    @Override
    public FristActRVAdapted.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.style_activity_first,parent,false);
        return new ViewHolder(view,onNoteListener);
    }

    /**
     * modificateur de vue récupére une valeur pour l'utilisé comme valeur définie pour une cellule du recyclerview
     * @return
     */
    @Override
    public void onBindViewHolder(@NonNull FristActRVAdapted.ViewHolder holder, int position) {
        holder.textTitreRegion.setText(rvTextRegion.get(position));
        holder.valeurSurvie.setText(rvSurvie.get(position));
        holder.iconViewRegion.setImageResource(rvRegionImageview.get(position));
        holder.valeurEau.setText(valeurEau.get(position));
        holder.valeurPlante.setText(valeurPlante.get(position));

    }

    /**
     * Taille du recyclerview
     * @return
     */
    @Override
    public int getItemCount() {
        return rvTextRegion.size();
    }



    /**
     * Nouvelle vue elle récupére les valeurs qu'on lui donne pour modifié le rendu
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iconViewRegion;
        TextView textTitreRegion;
        TextView valeurSurvie;
        TextView valeurEau;
        TextView valeurPlante;
        OnNoteListener onNoteListener;


        public ViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            iconViewRegion = itemView.findViewById(R.id.imageregiondesurvie);
            textTitreRegion = itemView.findViewById(R.id.texttitretype);
            valeurSurvie = itemView.findViewById(R.id.tauxsurvietext);
            valeurEau = itemView.findViewById(R.id.tauxdehydratext);
            valeurPlante = itemView.findViewById(R.id.tauxplantetext);
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
