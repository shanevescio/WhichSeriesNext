package com.example.shane.whichserialnext;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SerialList {
    ArrayList<Serial> serialList;
    Serial serial;

    /*Erstelle neue Serienliste*/
    public SerialList()
    {
        serialList = new ArrayList<Serial>();
    }

    /*Erstelle neue Serie und füge dies der Serienliste hinzu*/
    public void addSerial(Serial serial)
    {
        serialList.add(serial);
    }

    public void deleteSerial(int index){
        serialList.remove(index);
    }

    /*Gib die komplette Serienliste aus*/
    public ArrayList getSerialList()
    {
        return serialList;
    }

    public int size(){
        return serialList.size();
    }
}

