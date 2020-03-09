package com.example.zennex.ui.listComponent;

import android.app.Activity;

import com.example.zennex.MainActivity;
import com.example.zennex.R;
import com.example.zennex.ui.dialogComponent.DialogFragmentEditDelete;

import java.util.List;

public class ListPresenter {

    private Activity mActivity;
    private List<ListObject> objectList;
    private static final String DIALOG_CREATE = "DialogCreate";

    public ListPresenter(List<ListObject> objectList, Activity activity) {
        this.objectList = objectList;
        this.mActivity = activity;
    }

    public void onBindViewAtPosition(int i, ListHolder listHolder) {
        ListObject newsObject = objectList.get(i);
        listHolder.bind(newsObject);
        listHolder.setTitle(newsObject.getmTitle());
        listHolder.setCheck(newsObject.ismCheck());
        if (newsObject.ismCheck()) {
            listHolder.setPicture(R.drawable.image_one);
        } else {
            listHolder.setPicture(R.drawable.image_two);
        }
    }

    public int getCount() {
        return objectList.size();
    }

    public void onClickItemPosition(int i) {

    }

    public void onLongClickItemPosition(int i) {
        DialogFragmentEditDelete dialogFragmentEditDelete = new DialogFragmentEditDelete();
        dialogFragmentEditDelete.pressObject(i);
        dialogFragmentEditDelete.show(mActivity.getFragmentManager(), DIALOG_CREATE);
    }

    public void onCheck(int i) {
        ((MainActivity)mActivity).setCheck(i);
    }
}
