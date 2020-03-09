package com.example.zennex.ui.listComponent;

/**
 *
 */
public class ListObject {

    private String mTitle;
    private boolean mCheck;
    private long mId;

    public ListObject(String title, boolean check) {
        this.mTitle = title;
        this.mCheck = check;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean ismCheck() {
        return mCheck;
    }

    public void setmCheck(boolean mCheck) {
        this.mCheck = mCheck;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }
}
