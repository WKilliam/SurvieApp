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

public class CouteauSuisseRCVAdapted extends RecyclerView.Adapter<CouteauSuisseRCVAdapted.ViewHolder1>
{

    /**
     * Les variables ci-dessous servent a récupéré des valeur pour le recylcerview
     */
    ArrayList<Integer> tabSetImageView =new ArrayList<>();
    ArrayList<String> tabSetTextView=new ArrayList<>();
    LayoutInflater inflater;
    OnNoteListener onNoteListener;

    /**
     * Constructeur du recyclerview
     * @param ctx contexte du recyclerview
     *
     * Toutes les variable ci-dessous on le même but :
     * Variable tableau de région pour changé le rendu
     */
    public CouteauSuisseRCVAdapted(Context ctx,
                                   ArrayList<Integer> tabSetImageView,
                                   ArrayList<String> tabSetTextView,
                                   OnNoteListener onNoteListener)
    {
        this.tabSetImageView = tabSetImageView;
        this.tabSetTextView = tabSetTextView;
        this.inflater = LayoutInflater.from(ctx);
        this.onNoteListener=onNoteListener;
    }
    /**
     * Changeur de vue récupére un XML pour l'utilisé comme cellule du recyclerview
     * @return
     */
    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.couteau_suisse_custum,parent,false);
        return new ViewHolder1(view,onNoteListener);
    }
    /**
     * modificateur de vue récupére une valeur pour l'utilisé comme valeur définie pour une cellule du recyclerview
     * @return
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position)
    {
        holder.imagesetbox.setImageResource(tabSetImageView.get(position));
        holder.textsetbox.setText(tabSetTextView.get(position));

    }
    /**
     * Taille du recyclerview
     * @return
     */
    @Override
    public int getItemCount()
    {
        return tabSetTextView.size();
    }
    /**
     * Nouvelle vue elle récupére les valeurs qu'on lui donne pour modifié le rendu
     */
    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        ImageView imagesetbox;
        TextView textsetbox;


        public ViewHolder1(@NonNull View itemView, OnNoteListener onNoteListener)
        {
            super(itemView);
            imagesetbox = itemView.findViewById(R.id.imageViewcardview);
            textsetbox = itemView.findViewById(R.id.textcouteauoutils);
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
