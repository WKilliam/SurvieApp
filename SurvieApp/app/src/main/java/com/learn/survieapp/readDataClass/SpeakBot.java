package com.learn.survieapp.readDataClass;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.speech.tts.TextToSpeech;
import android.view.Display;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class SpeakBot {

    public String aPHomme(){
        return "Vous êtes sur la page à propos, jeune Aventurier." +
                " By Teddy";
    }
    public String aPFemme(){
        return "Vous êtes sur la page à propos, jeune Aventuriére." +
                " By Teddy";
    }

    public String tutoriel(){

        return "bienvenu sur cette application de survie,"+"\n"+
                //" grâce à elle vous allait pouvoir survivre plus longtemps."+"\n"+
                //"Elle possède des méthodes,guide alimentaire, et outils ( comme une boussole )," +"\n"+
                //" mais également des conseils bien précis pour les premières heures dans un milieu hostile."+"\n"+
                //" Pour commencer je vais vous présenter lapplication " +"\n"+
                //"les premières icônes en haut de lécran représente " +"\n"+
                //"la page à propos , tutoriel , et outils du campeur." +"\n"+
                " Veuillez sil vous plait commencé par le tutoriel,"+"\n"+
                "il s'agit de l'icône centrale";
    }

    public String couteauSuisse() {
        return "vous êtes dans le, menu couteau suisse.";
    }

    public String testbot(String string){
        return string;
    }
}
