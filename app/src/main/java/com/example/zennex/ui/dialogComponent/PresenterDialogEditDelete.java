package com.example.zennex.ui.dialogComponent;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zennex.MainActivity;
import com.example.zennex.R;
import com.example.zennex.view.ViewDialogWindow;

@InjectViewState
public class PresenterDialogEditDelete extends MvpPresenter<ViewDialogWindow> {

    public void setOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_edit:
                getViewState().ActionFirstButton();
                MainActivity.CHANGE = true;
                break;
            case R.id.btn_delete:
                getViewState().ActionSecondButton();
                break;
        }
    }
}
