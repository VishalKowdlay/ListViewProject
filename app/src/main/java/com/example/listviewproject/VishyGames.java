package com.example.listviewproject;

import android.os.Parcelable;
import android.os.Parcel;

public class VishyGames implements Parcelable {
    private String gameName;
    private String gameDesc;
    private String gameDate;
    private int gameNum;

    public VishyGames(String gameName, String gameDesc, String gameDate, int gameNum)
    {
        this.gameName = gameName;
        this.gameDesc = gameDesc;
        this.gameNum = gameNum;
        this.gameDate = gameDate;

    }

    private VishyGames(Parcel in){
        gameNum = Integer.parseInt(in.readString());
        gameName = in.readString();
        gameDesc = in.readString();
        gameDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(String.valueOf(gameNum));
        dest.writeString(gameName);
        dest.writeString(gameDesc);
        dest.writeString(gameDate);
    }

    public static final Parcelable.Creator<VishyGames> CREATOR = new Parcelable.Creator<VishyGames>(){
        public VishyGames createFromParcel(Parcel in){
            return new VishyGames(in);
        }

        @Override
        public VishyGames[] newArray(int size) {
            return new VishyGames[size];
        }
    };

    public String getName()
    {
        return gameName;
    }

    public String getDate(){
        return gameDate;

    }

    public String getDescription()
    {
        return gameDesc;
    }

    public int getGameNum()
    {
        return gameNum;
    }


    @Override
    public int describeContents() {
        return 0;
    }



}
