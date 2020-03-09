package com.example.zennex.ui.dialogComponent;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zennex.MainActivity;
import com.example.zennex.R;
import com.example.zennex.view.ViewDialogWindow;

@InjectViewState
public class PresenterDialogSave extends MvpPresenter<ViewDialogWindow> {

    public void setOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yes:
                getViewState().ActionFirstButton();
//                MainActivity.CHANGE = true;
//                getViewState().
                break;
            case R.id.btn_no:
                getViewState().ActionSecondButton();
                MainActivity.CHANGE = false;
                break;
        }
    }
}
