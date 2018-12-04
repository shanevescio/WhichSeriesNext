package com.example.shane.whichserialnext;

import android.os.Parcelable;
import android.os.Parcel;
import android.support.annotation.NonNull;

public class Serial {
    String name;
    int season = 1;
    int episode = 1;

    /*Erstelle neue Serie mit name, season, episode*/
    public Serial(String name, int season, int episode)
    {
        this.name = name;
        this.season = season;
        this.episode = episode;
    }

    /*Ändere den Namen der Serie*/
    public void changeName(String name)
    {
        this.name = name;
    }

    /*Erhöhe season um 1*/
    public void incrementSeason()
    {
        this.season =+ 1;
    }

    /*Erhöhe episode um 1*/
    public void incrementEpisode()
    {
        this.episode =+ 1;
    }

    /*Reduziere season um 1*/
    public void reduceSeason()
    {
        this.season =- 1;
    }

    /*Reduziere episode um 1*/
    public void reduceEpisode()
    {
        this.episode =- 1;
    }

    public String toString()
    {
        return "Name: " + this.name + " Season: " + this.season + " Episode: " + this.episode;
    }

    public String getName() {
        return name;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }
}
