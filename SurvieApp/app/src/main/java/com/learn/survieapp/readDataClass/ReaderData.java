package com.learn.survieapp.readDataClass;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class ReaderData {

    String valeur_Region_Data;
    Integer valeur_Region_Imageview_Data;
    String valeur_Survie_Data;
    String valeur_Nourriture_Data;
    String valeur_Eau_Data;
    String valeur_Plante_Data;

    public String getValeur_Region_Data() {
        return valeur_Region_Data;
    }

    public void setValeur_Region_Data(String valeur_Region_Data) {
        this.valeur_Region_Data = valeur_Region_Data;
    }

    public Integer getValeurRegionImageviewData() {
        return valeur_Region_Imageview_Data;
    }

    public void setValeurRegionImageviewData(Integer valeurRegionImageviewData) {
        this.valeur_Region_Imageview_Data = valeurRegionImageviewData;
    }

    public String getValeur_Survie_Data() {
        return valeur_Survie_Data;
    }

    public void setValeur_Survie_Data(String valeur_Survie_Data) {
        this.valeur_Survie_Data = valeur_Survie_Data;
    }

    public String getValeur_Nourriture_Data() {
        return valeur_Nourriture_Data;
    }

    public void setValeur_Nourriture_Data(String valeur_Nourriture_Data) {
        this.valeur_Nourriture_Data = valeur_Nourriture_Data;
    }

    public String getValeur_Eau_Data() {
        return valeur_Eau_Data;
    }

    public void setValeur_Eau_Data(String valeur_Eau_Data) {
        this.valeur_Eau_Data = valeur_Eau_Data;
    }

    public String getValeur_Plante_Data() {
        return valeur_Plante_Data;
    }

    public void setValeur_Plante_Data(String valeur_Plante_Data) {
        this.valeur_Plante_Data = valeur_Plante_Data;
    }

    @Override
    public String toString() {
        return "ReaderData{" +
                "valeurRegionData='" + valeur_Region_Data + '\'' +
                ", valeurRegionImageviewData=" + valeur_Region_Imageview_Data +
                ", valeurSurvieData='" + valeur_Survie_Data + '\'' +
                ", valeurNourritureData='" + valeur_Nourriture_Data + '\'' +
                ", valeurEauData='" + valeur_Eau_Data + '\'' +
                ", valeurPlanteData='" + valeur_Plante_Data + '\'' +
                '}';
    }

    public static String getJsonFromAssets(Context context, String fileName) {

        String jsonString;
        try {

            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

}
