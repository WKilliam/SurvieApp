package com.learn.survieapp.readDataClass;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class ReaderData {


    String dvaleur_Region_Imageview_Data;
    String dmilieu_Region_Data;
    String dtaux_Survie_Data;
    String dtaux_Eau_Data;
    String dtaux_Plante_Data;
    String dLinkData;

    public String getdLinkData() {
        return dLinkData;
    }

    public String getDvaleur_Region_Imageview_Data() {
        return dvaleur_Region_Imageview_Data;
    }

    public String getDmilieu_Region_Data() {
        return dmilieu_Region_Data;
    }

    public String getDtaux_Survie_Data() {
        return dtaux_Survie_Data;
    }

    public String getDtaux_Eau_Data() {
        return dtaux_Eau_Data;
    }

    public String getDtaux_Plante_Data() {
        return dtaux_Plante_Data;
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
