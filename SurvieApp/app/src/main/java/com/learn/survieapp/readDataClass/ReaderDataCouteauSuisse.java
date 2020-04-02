package com.learn.survieapp.readDataClass;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CheckedOutputStream;

public class ReaderDataCouteauSuisse extends CkeckoutJsonClass {

    String icon_outil;
    String Data_Text_Type_Icon;

    public String getIcon_outil() {
        return icon_outil;
    }


    public String getData_Text_Type_Icon() {
        return Data_Text_Type_Icon;
    }

    public void setData_Text_Type_Icon(String data_Text_Type_Icon) {
        Data_Text_Type_Icon = data_Text_Type_Icon;
    }

    @Override
    public String toString() {
        return "ReaderDataCouteauSuisse{" +
                "icon_outil=" + icon_outil +
                ", Data_Text_Type_Icon='" + Data_Text_Type_Icon + '\'' +
                '}';
    }
}
