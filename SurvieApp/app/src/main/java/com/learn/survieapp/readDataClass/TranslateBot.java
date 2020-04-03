package com.learn.survieapp.readDataClass;

import android.media.MediaPlayer;

import com.learn.survieapp.R;

import java.util.ArrayList;

public class TranslateBot {

    ArrayList<Integer> mediaPlayer= new ArrayList<>();

    public ArrayList<String> textAdapte(String text){

        String[] src = text.split("");

        ArrayList<String> stringssplit = new ArrayList<>();

        for (int i = 0; i <src.length ; i++) {

            String result = src[i].toLowerCase();
            stringssplit.add(result);
        }

        return stringssplit;
    }

    public ArrayList<Integer> soundCreate(ArrayList<String> text) {

        for (int i = 0; i < text.size(); i++) {

            switch (text.get(i)) {
                case "a":
                    mediaPlayer.add(R.raw.lettrea);
                    break;
                case "b":
                    mediaPlayer.add(R.raw.lettreb);
                    break;
                case "c":
                    mediaPlayer.add(R.raw.lettrec);
                    break;
                case "d":
                    mediaPlayer.add(R.raw.lettred);
                    break;
                case "e":
                    mediaPlayer.add(R.raw.lettree);
                    break;
                case "f":
                    mediaPlayer.add(R.raw.lettref);
                    break;
                case "g":
                    mediaPlayer.add(R.raw.lettreg);
                    break;
                case "h":
                    mediaPlayer.add(R.raw.lettreh);
                    break;
                case "i":
                    mediaPlayer.add(R.raw.lettrei);
                    break;
                case "j":
                    mediaPlayer.add(R.raw.lettrej);
                    break;
                case "k":
                    mediaPlayer.add(R.raw.lettrek);
                    break;
                case "l":
                    mediaPlayer.add(R.raw.lettrel);
                    break;
                case "m":
                    mediaPlayer.add(R.raw.lettrem);
                    break;
                case "n":
                    mediaPlayer.add(R.raw.lettren);
                    break;
                case "o":
                    mediaPlayer.add(R.raw.lettreo);
                    break;
                case "p":
                    mediaPlayer.add(R.raw.lettrep);
                    break;
                case "q":
                    mediaPlayer.add(R.raw.lettreq);
                    break;
                case "r":
                    mediaPlayer.add(R.raw.lettrer);
                    break;
                case "s":
                    mediaPlayer.add(R.raw.lettres);
                    break;
                case "t":
                    mediaPlayer.add(R.raw.lettret);
                    break;
                case "u":
                    mediaPlayer.add(R.raw.lettreu);
                    break;
                case "v":
                    mediaPlayer.add(R.raw.lettrev);
                    break;
                case "w":
                    mediaPlayer.add(R.raw.lettrew);
                    break;
                case "x":
                    mediaPlayer.add(R.raw.lettrex);
                    break;
                case "y":
                    mediaPlayer.add(R.raw.lettrey);
                    break;
                case "z":
                    mediaPlayer.add(R.raw.lettrez);
                    break;
                case "0":
                    mediaPlayer.add(R.raw.chiffrezero);
                    break;
                case "1":
                    mediaPlayer.add(R.raw.chiffreun);
                    break;
                case "2":
                    mediaPlayer.add(R.raw.chiffredeux);
                    break;
                case "3":
                    mediaPlayer.add(R.raw.chiffretrois);
                    break;
                case "4":
                    mediaPlayer.add(R.raw.chiffrequatre);
                    break;
                case "5":
                    mediaPlayer.add(R.raw.chiffrecinq);
                    break;
                case "6":
                    mediaPlayer.add(R.raw.chiffresix);
                    break;
                case "7":
                    mediaPlayer.add(R.raw.chiffresept);
                    break;
                case "8":
                    mediaPlayer.add(R.raw.chiffrehuit);
                    break;
                case "9":
                    mediaPlayer.add(R.raw.chiffreneuf);
                    break;
                case "à":
                    mediaPlayer.add(R.raw.lettrea);
                    break;
                case "é":
                    mediaPlayer.add(R.raw.lettree);
                    break;
                case "è":
                    mediaPlayer.add(R.raw.lettree);
                    break;
                case "ç":
                    mediaPlayer.add(R.raw.lettrec);
                    break;
                case "ê":
                    mediaPlayer.add(R.raw.lettree);
                    break;
                case "û":
                    mediaPlayer.add(R.raw.lettreu);
                    break;
                case "î":
                    mediaPlayer.add(R.raw.lettree);
                    break;
                case " ":
                    mediaPlayer.add(-1);
                    break;
                case "!":
                    mediaPlayer.add(-1);
                    break;
                case ".":
                    mediaPlayer.add(-1);
                    break;
                case "?":
                    mediaPlayer.add(-1);
                    break;
                case ",":
                    mediaPlayer.add(-1);
                    break;
                default:
                    mediaPlayer.add(-1);
                    break;
            }
        }
        for (int i = 0; i <mediaPlayer.size() ; i++) {
            if(mediaPlayer.get(i) == -1){
                mediaPlayer.remove(i);
            }
        }
        return mediaPlayer;
    }
}