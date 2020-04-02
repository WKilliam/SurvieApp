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

public class RecyclerViewAdapted1 extends RecyclerView.Adapter<RecyclerViewAdapted1.ViewHolder> {

    /**
     * Les variables ci-dessous servent a récupéré des valeur pour le recylcerview
     */
    ArrayList<String> valeurRegion;
    ArrayList<Integer> valeurRegionImageview;
    ArrayList<String> valeurSurvie;
    ArrayList<String> valeurNourriture;
    ArrayList<String> valeurEau;
    ArrayList<String> valeurPlante;
    OnNoteListener onNoteListener;
    LayoutInflater inflater;

    /**
     * Constructeur du recyclerview
     * @param ctx contexte du recyclerview
     *
     * Toutes les variable ci-dessous on le même but :
     * Variable tableau de région pour changé le rendu
     * @param valeurRegion
     * @param valeurRegionImageview
     * @param valeurSurvie
     * @param valeurNourriture
     * @param valeurEau
     * @param valeurPlante
     */
    public RecyclerViewAdapted1(Context ctx,
                                ArrayList<String> valeurRegion,
                                ArrayList<Integer> valeurRegionImageview,
                                ArrayList<String> valeurSurvie,
                                ArrayList<String> valeurNourriture,
                                ArrayList<String> valeurEau,
                                ArrayList<String> valeurPlante,
                                OnNoteListener onNoteListener) {
        this.valeurRegion = valeurRegion;
        this.valeurRegionImageview = valeurRegionImageview;
        this.valeurSurvie = valeurSurvie;
        this.valeurNourriture = valeurNourriture;
        this.valeurEau = valeurEau;
        this.valeurPlante = valeurPlante;
        this.inflater = LayoutInflater.from(ctx);
        this.onNoteListener=onNoteListener;
    }

    /**
     * Changeur de vue récupére un XML pour l'utilisé comme cellule du recyclerview
     * @return
     */
    @NonNull
    @Override
    public RecyclerViewAdapted1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.style_activity_first,parent,false);
        return new ViewHolder(view,onNoteListener);
    }

    /**
     * modificateur de vue récupére une valeur pour l'utilisé comme valeur définie pour une cellule du recyclerview
     * @return
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapted1.ViewHolder holder, int position) {
        holder.textTitreRegion.setText(valeurRegion.get(position));
        holder.valeurSurvie.setText(valeurSurvie.get(position));
        holder.iconViewRegion.setImageResource(valeurRegionImageview.get(position));
        holder.valeurNourriture.setText(valeurNourriture.get(position));
        holder.valeurEau.setText(valeurEau.get(position));
        holder.valeurPlante.setText(valeurPlante.get(position));

    }

    /**
     * Taille du recyclerview
     * @return
     */
    @Override
    public int getItemCount() {
        return valeurRegion.size();
    }



    /**
     * Nouvelle vue elle récupére les valeurs qu'on lui donne pour modifié le rendu
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iconViewRegion;
        TextView textTitreRegion;
        TextView valeurSurvie;
        TextView valeurEau;
        TextView valeurNourriture;
        TextView valeurPlante;
        OnNoteListener onNoteListener;


        public ViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            iconViewRegion = itemView.findViewById(R.id.imageregiondesurvie);
            textTitreRegion = itemView.findViewById(R.id.texttyperegion);
            valeurSurvie = itemView.findViewById(R.id.valeurchancesurvie);
            valeurEau = itemView.findViewById(R.id.valeurchanceeau);
            valeurPlante = itemView.findViewById(R.id.valeurchanceplante);
            valeurNourriture = itemView.findViewById(R.id.valeurchancenourriture);
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
