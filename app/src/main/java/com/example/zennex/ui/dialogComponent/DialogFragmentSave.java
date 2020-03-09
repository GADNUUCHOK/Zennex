package com.example.zennex.ui.dialogComponent;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.arellomobile.mvp.MvpDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.zennex.MainActivity;
import com.example.zennex.R;
import com.example.zennex.view.ViewDialogWindow;

public class DialogFragmentSave extends MvpDialogFragment implements View.OnClickListener, ViewDialogWindow {

    @InjectPresenter
    public PresenterDialogSave mPresenterDialogSave;
    private Button mButtonYes;
    private Button mButtonNo;
    private int mPositionObject;

    public void pressObject(int positionObject) {
        this.mPositionObject = positionObject;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogWindow = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_component_save, null);
        mButtonYes = dialogWindow.findViewById(R.id.btn_yes);
        mButtonYes.setOnClickListener(this);
        mButtonNo = dialogWindow.findViewById(R.id.btn_no);
        mButtonNo.setOnClickListener(this);
        builder.setView(dialogWindow);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        mPresenterDialogSave.setOnClick(v);
    }

    @Override
    public void ActionFirstButton() {
        getDialog().dismiss();
        ((MainActivity)getActivity()).setChangeItem(mPositionObject);
        ((MainActivity)getActivity()).changeItem();
        ((MainActivity)getActivity()).hideRelativeAddItem();
    }

    @Override
    public void ActionSecondButton() {
        getDialog().dismiss();
        ((MainActivity)getActivity()).hideRelativeAddItem();
    }
}
