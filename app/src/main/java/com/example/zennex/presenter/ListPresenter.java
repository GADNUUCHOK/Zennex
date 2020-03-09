package com.example.zennex.presenter;

import android.view.View;
import android.widget.EditText;

import androidx.room.Room;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zennex.MainActivity;
import com.example.zennex.R;
import com.example.zennex.data.AppDatabase;
import com.example.zennex.ui.dialogComponent.DialogFragmentEditDelete;
import com.example.zennex.ui.dialogComponent.DialogFragmentSave;
import com.example.zennex.ui.listComponent.ListObject;
import com.example.zennex.view.ListViewInterface;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListViewInterface> {

    public List<ListObject> mListObject = new ArrayList<>();
    public String mTextEditText;
    public int mChangeItemPosition;

    public void addItem(List<ListObject> listObjects, ListObject addObject) {
        listObjects.add(addObject);
        getViewState().addNewItem();
        getViewState().hideRelativeAddItem();
    }

    public void setOnClickButton(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                getViewState().showRelativeAddItem();
                break;
            case R.id.btn_done:
                if (MainActivity.CHANGE) {
                    getViewState().changeItem();
                    MainActivity.CHANGE = false;
                    getViewState().hideRelativeAddItem();
                } else {
                    ListObject listObject = new ListObject(mTextEditText, false);
                    addItem(mListObject, listObject);
                    getViewState().saveListState();
                }
                break;
            case R.id.btn_revert:
                if (MainActivity.CHANGE) {
                    MainActivity.CHANGE = false;
                }
                getViewState().hideRelativeAddItem();
                break;
        }

    }

    public void getEditText(EditText editText) {
        mTextEditText = editText.getText().toString();
    }

    public void getPosition(int mChangeItemPosition) {
        this.mChangeItemPosition = mChangeItemPosition;
    }

    public void backPressed() {

    }
}
