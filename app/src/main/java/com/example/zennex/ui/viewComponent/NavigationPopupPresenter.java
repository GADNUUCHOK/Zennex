package com.example.zennex.ui.viewComponent;

import android.app.Activity;
import android.content.Intent;

import com.example.zennex.activity.MapActivity;
import com.example.zennex.activity.ParsingActivity;
import com.example.zennex.activity.ScalingActivity;
import com.example.zennex.MainActivity;

import java.util.List;

public class NavigationPopupPresenter {

    private final List<ItemNavigationPopup> navigationPopupList;
    private final Activity mActivity;

    public NavigationPopupPresenter(List<ItemNavigationPopup> navigationPopupList, Activity activity) {
        this.mActivity = activity;
        this.navigationPopupList = navigationPopupList;
    }

    public void onBindViewAtPosition(int i, NavigationPopupHolder navigationPopupHolder) {
        ItemNavigationPopup itemNavigationPopup = navigationPopupList.get(i);
        navigationPopupHolder.setIcon(itemNavigationPopup.getmIcon());
        navigationPopupHolder.setTitle(itemNavigationPopup.getmTitle());
    }

    public int getCount() {
        return navigationPopupList.size();
    }

    public void onClickItemPosition(int i) {
        switch (i) {
            case 0:
                mActivity.startActivity(new Intent(this.mActivity, MainActivity.class));
                break;
            case 1:
                mActivity.startActivity(new Intent(this.mActivity, ScalingActivity.class));
                break;
            case 2:
                mActivity.startActivity(new Intent(this.mActivity, ParsingActivity.class));
                break;
            case 3:
                mActivity.startActivity(new Intent(this.mActivity, MapActivity.class));
                break;
        }
    }
}
