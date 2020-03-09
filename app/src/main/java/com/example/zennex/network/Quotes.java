package com.example.zennex.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quotes {

    @SerializedName("id")
    @Expose
    public String mID;

    @SerializedName("description")
    @Expose
    public String mDescription;

    @SerializedName("xxx")
    @Expose
    public String mXXX;

    @SerializedName("time")
    @Expose
    public String mTime;

    @SerializedName("rating")
    @Expose
    public String mRating;

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmXXX() {
        return mXXX;
    }

    public void setmXXX(String mXXX) {
        this.mXXX = mXXX;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }
}
