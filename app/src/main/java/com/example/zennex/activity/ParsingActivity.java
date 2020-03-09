package com.example.zennex.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.zennex.view.ParsingViewInterface;
import com.example.zennex.presenter.ParsingPresenter;
import com.example.zennex.R;

public class ParsingActivity extends MvpAppCompatActivity implements ParsingViewInterface {

    @InjectPresenter
    ParsingPresenter mParsingPresenter;

    public static final String TAG = "Tag";
    public TextView mTextParsing;
    public ProgressBar mProgressBar;
//    private StringBuilder mResponseString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcing);
        mTextParsing = findViewById(R.id.tv_info_parsing);
        mProgressBar = findViewById(R.id.pb_progress_parsing);
//        parsingText();
//        mTextParsing.setText(mResponseString);
//        ParsingAsyncTask parsingAsyncTask = new ParsingAsyncTask(this);
//        parsingAsyncTask.execute();
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void getParsingTextAPI(StringBuilder stringBuilder) {
        mTextParsing.setText(stringBuilder);
    }

    @Override
    public void setParsingTextView() {

    }
}
