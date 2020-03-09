package com.example.zennex.view;

import com.arellomobile.mvp.MvpView;

public interface ListViewInterface extends MvpView {

    void addNewItem();

    void saveListState();

    void revertItem();

    void hideRelativeAddItem();

    void showRelativeAddItem();

    void changeItem();

}
