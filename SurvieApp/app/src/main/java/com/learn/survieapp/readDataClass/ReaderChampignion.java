package com.learn.survieapp.readDataClass;

import java.util.Arrays;

public class ReaderChampignion extends CkeckoutJsonClass{

    String []   Champignion_Image;
    String []   Champignion_Nom;
    String []   Champignion_Detail;
    String []   Champignion_Comestible;

    public String[] getChampignion_Image() {
        return Champignion_Image;
    }

    public String[] getChampignion_Nom() {
        return Champignion_Nom;
    }

    public String[] getChampignion_Detail() {
        return Champignion_Detail;
    }

    public String[] getChampignion_Comestible() {
        return Champignion_Comestible;
    }

    @Override
    public String toString() {
        return "ReaderChampignion{" +
                "Champignion_Image=" + Arrays.toString(Champignion_Image) +
                ", Champignion_Nom=" + Arrays.toString(Champignion_Nom) +
                ", Champignion_Detail=" + Arrays.toString(Champignion_Detail) +
                ", Champignion_Comestible=" + Arrays.toString(Champignion_Comestible) +
                '}';
    }
}
