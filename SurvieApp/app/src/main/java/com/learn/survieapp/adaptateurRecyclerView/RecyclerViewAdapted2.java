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

public class RecyclerViewAdapted2 extends RecyclerView.Adapter<RecyclerViewAdapted2.ViewHolder1>
{

    /**
     * Les variables ci-dessous servent a récupéré des valeur pour le recylcerview
     */
    ArrayList<Integer> tabSetImageView =new ArrayList<>();
    ArrayList<String> tabSetTextView=new ArrayList<>();
    LayoutInflater inflater;

    /**
     * Constructeur du recyclerview
     * @param ctx contexte du recyclerview
     *
     * Toutes les variable ci-dessous on le même but :
     * Variable tableau de région pour changé le rendu
     */
    public RecyclerViewAdapted2(Context ctx,
                                ArrayList<Integer> tabSetImageView,
                                ArrayList<String> tabSetTextView)
    {
        this.tabSetImageView = tabSetImageView;
        this.tabSetTextView = tabSetTextView;
        this.inflater = LayoutInflater.from(ctx);
    }
    /**
     * Changeur de vue récupére un XML pour l'utilisé comme cellule du recyclerview
     * @return
     */
    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.firstactivitycustum,parent,false);
        return new ViewHolder1(view);
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
        return 1;
    }
    /**
     * Nouvelle vue elle récupére les valeurs qu'on lui donne pour modifié le rendu
     */
    public class ViewHolder1 extends RecyclerView.ViewHolder
    {

        ImageView imagesetbox;
        TextView textsetbox;

        public ViewHolder1(@NonNull View itemView)
        {
            super(itemView);
            imagesetbox = itemView.findViewById(R.id.imageboxicon);
            textsetbox = itemView.findViewById(R.id.textcouteauoutils);
        }
    }
}
