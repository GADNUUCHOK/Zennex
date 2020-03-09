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
import com.example.zennex.ui.listComponent.ListObject;
import com.example.zennex.view.ViewDialogWindow;

public class DialogFragmentEditDelete extends MvpDialogFragment implements View.OnClickListener, ViewDialogWindow {

    @InjectPresenter
    public PresenterDialogEditDelete mPresenterDialogEditDelete;
    private Button mButtonEdit;
    private Button mButtonDelete;
    private int mListObject;

    public void pressObject(int deleteListObject) {
        this.mListObject = deleteListObject;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogWindow = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_component_edit_delete, null);
        mButtonEdit = dialogWindow.findViewById(R.id.btn_edit);
        mButtonEdit.setOnClickListener(this);
        mButtonDelete = dialogWindow.findViewById(R.id.btn_delete);
        mButtonDelete.setOnClickListener(this);
        builder.setView(dialogWindow);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        mPresenterDialogEditDelete.setOnClick(v);
    }

    @Override
    public void ActionFirstButton() {
        getDialog().dismiss();
        ((MainActivity)getActivity()).showRelativeAddItem();
        ((MainActivity)getActivity()).setChangeItem(mListObject);
//        ((MainActivity)getActivity()).changeItem(mListObject);
    }

    @Override
    public void ActionSecondButton() {
        getDialog().dismiss();
        ((MainActivity)getActivity()).deleteItem(mListObject);
    }
}
