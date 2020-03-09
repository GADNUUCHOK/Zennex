package com.example.zennex.view;

import com.arellomobile.mvp.MvpView;

public interface ParsingViewInterface extends MvpView {

    void showProgressBar();

    void hideProgressBar();

    void getParsingTextAPI(StringBuilder stringBuilder);

    void setParsingTextView();
}
