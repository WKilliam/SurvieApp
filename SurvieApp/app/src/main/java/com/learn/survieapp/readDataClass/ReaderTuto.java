package com.learn.survieapp.readDataClass;

public class ReaderTuto extends CkeckoutJsonClass{

    String rtImageIcon;
    String rtTitre;
    String rtTextInfo;

    public String getRtImageIcon() {
        return rtImageIcon;
    }

    public String getRtTitre() {
        return rtTitre;
    }

    public String getRtTextInfo() {
        return rtTextInfo;
    }

    @Override
    public String toString() {
        return "ReaderTuto{" +
                "rtImageIcon='" + rtImageIcon + '\'' +
                ", rtTitre='" + rtTitre + '\'' +
                ", rtTextInfo='" + rtTextInfo + '\'' +
                '}';
    }
}
