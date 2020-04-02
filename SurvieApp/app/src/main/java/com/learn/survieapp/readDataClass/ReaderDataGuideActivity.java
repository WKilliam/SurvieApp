package com.learn.survieapp.readDataClass;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ReaderDataGuideActivity extends CkeckoutJsonClass {

    String jRegion;
    String jTextTitre;
    String jTexteDetail;
    String[] jtab_24;
    String[] jtab_24_Titre;
    String[] jtab_24_Detail;
    String[] jtab_72;
    String[] jtab_72_Titre;
    String[] jtab_72_Detail;
    String[] jtab_BA;
    String[] jtab_BA_Titre;
    String[] jtab_BA_Detail;

    public String getjRegion() {
        return jRegion;
    }

    public String getjTextTitre() {
        return jTextTitre;
    }

    public String getjTexteDetail() {
        return jTexteDetail;
    }

    public String[] getJtab_24() {
        return jtab_24;
    }

    public String[] getJtab_24_Titre() {
        return jtab_24_Titre;
    }

    public String[] getJtab_24_Detail() {
        return jtab_24_Detail;
    }

    public String[] getJtab_72() {
        return jtab_72;
    }

    public String[] getJtab_72_Titre() {
        return jtab_72_Titre;
    }

    public String[] getJtab_72_Detail() {
        return jtab_72_Detail;
    }

    public String[] getJtab_BA() {
        return jtab_BA;
    }

    public String[] getJtab_BA_Titre() {
        return jtab_BA_Titre;
    }

    public String[] getJtab_BA_Detail() {
        return jtab_BA_Detail;
    }

    @Override
    public String toString() {
        return "ReaderDataGuideActivity{" +
                "jRegion='" + jRegion + '\'' +
                ", jTextTitre='" + jTextTitre + '\'' +
                ", jTexteDetail='" + jTexteDetail + '\'' +
                ", jtab_24=" + Arrays.toString(jtab_24) +
                ", jtab_24_Titre=" + Arrays.toString(jtab_24_Titre) +
                ", jtab_24_Detail=" + Arrays.toString(jtab_24_Detail) +
                ", jtab_72=" + Arrays.toString(jtab_72) +
                ", jtab_72_Titre=" + Arrays.toString(jtab_72_Titre) +
                ", jtab_72_Detail=" + Arrays.toString(jtab_72_Detail) +
                ", jtab_BA=" + Arrays.toString(jtab_BA) +
                ", jtab_BA_Titre=" + Arrays.toString(jtab_BA_Titre) +
                ", jtab_BA_Detail=" + Arrays.toString(jtab_BA_Detail) +
                '}';
    }
}
