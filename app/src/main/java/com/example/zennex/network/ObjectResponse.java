package com.example.zennex.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObjectResponse {
    @SerializedName("total")
    @Expose
    public String mTotal;

    @SerializedName("last")
    @Expose
    public String mLast;

    @SerializedName("quotes")
    @Expose
    public List<Quotes> mQuotes;

    public String getmTotal() {
        return mTotal;
    }

    public void setmTotal(String mTotal) {
        this.mTotal = mTotal;
    }

    public String getmLast() {
        return mLast;
    }

    public void setmLast(String mLast) {
        this.mLast = mLast;
    }

    public List<Quotes> getmQuotes() {
        return mQuotes;
    }

    public void setmQuotes(List<Quotes> mQuotes) {
        this.mQuotes = mQuotes;
    }
}

