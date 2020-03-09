package com.example.zennex.view;

import android.content.Intent;

import com.arellomobile.mvp.MvpView;

public interface ScalingViewInterface extends MvpView {

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
