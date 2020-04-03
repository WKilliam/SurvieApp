package com.learn.survieapp.activitySecondaire;

import com.learn.survieapp.readDataClass.CkeckoutJsonClass;

import java.util.Arrays;

class ReaderTuto extends CkeckoutJsonClass {

    String [] tuto_Detail;
    String [] tuto_Nom;
    String [] tuto_Image;

    public String[] getTuto_Detail() {
        return tuto_Detail;
    }

    public String[] getTuto_Nom() {
        return tuto_Nom;
    }

    public String[] getTuto_Image() {
        return tuto_Image;
    }

    @Override
    public String toString() {
        return "ReaderTuto{" +
                "tuto_Detail=" + Arrays.toString(tuto_Detail) +
                ", tuto_Nom=" + Arrays.toString(tuto_Nom) +
                ", tuto_Image=" + Arrays.toString(tuto_Image) +
                '}';
    }
}
