package com.learn.survieapp.readDataClass;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class ReaderDataGuideActivity {

    String jRegion;
    String jTextTitre;
    String jTexteDetail;

    public String getjRegion() {
        return jRegion;
    }

    public String getjTextTitre() {
        return jTextTitre;
    }

    public String getjTexteDetail() {
        return jTexteDetail;
    }

    @Override
    public String toString() {
        return "ReaderDataGuideActivity{" +
                "jRegion=" + jRegion +
                ", jTextTitre='" + jTextTitre + '\'' +
                ", jTexteDetail='" + jTexteDetail + '\'' +
                '}';
    }

    public static String getJsonFromAssetsGuideA(Context context, String fileName) {

        String jsonString;
        try {

            Log.i("testfile","filenametest = "+fileName);
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
