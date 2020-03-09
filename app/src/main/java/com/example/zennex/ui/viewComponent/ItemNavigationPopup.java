package com.example.zennex.ui.viewComponent;

public class ItemNavigationPopup {

    private int mIcon;
    private String mTitle;

    public ItemNavigationPopup(int mIcon, String mTitle) {
        this.mIcon = mIcon;
        this.mTitle = mTitle;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
