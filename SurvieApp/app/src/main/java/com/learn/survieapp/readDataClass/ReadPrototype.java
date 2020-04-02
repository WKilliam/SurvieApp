package com.learn.survieapp.readDataClass;

import android.content.Context;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadPrototype extends JSONObject {

    String image_name;

    public String getImage_name() {
        return image_name;
    }

    @Override
    public String toString() {
        return "ReadPrototype{" +
                "image_name='" + image_name + '\'' +
                '}';
    }

    public static String getJsonFromAssetsPrototy(Context context, String fileName) {

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
