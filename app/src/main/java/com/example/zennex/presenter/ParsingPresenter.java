package com.example.zennex.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.zennex.network.ParsingAPI;
import com.example.zennex.view.ParsingViewInterface;
import com.example.zennex.network.ObjectResponse;
import com.example.zennex.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class ParsingPresenter extends MvpPresenter<ParsingViewInterface> {

    public static final String TAG = "Tag";
    private StringBuilder mResponseString;

    public ParsingPresenter() {
        getDataParsing();
    }

    public void getDataParsing() {
        getViewState().showProgressBar();
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        ParsingAPI parsingAPI = retrofitBuilder.getAPI();
        Call<ObjectResponse> listObject = parsingAPI.getData("time");
        listObject.enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                assert response.body() != null;
                String total = response.body().getmTotal();
                Log.d(TAG, "TOTAL " + total);
                String last = response.body().getmLast();
                Log.d(TAG, "Last " + last);
                mResponseString = new StringBuilder(total + "\n" + last + "\n");
                for (int i = 0; i < response.body().getmQuotes().size(); i++) {
                    String id = response.body().getmQuotes().get(i).getmID();
                    mResponseString.append(id).append("\n");
                    String description = response.body().getmQuotes().get(i).getmDescription();
                    mResponseString.append(description).append("\n");
                    String xxx = response.body().getmQuotes().get(i).getmXXX();
                    mResponseString.append(xxx).append("\n");
                    String time = response.body().getmQuotes().get(i).getmTime();
                    mResponseString.append(time).append("\n");
                    String rating = response.body().getmQuotes().get(i).getmRating();
                    mResponseString.append(rating).append("\n");
                }
                Log.d(TAG, "ResponseString " + mResponseString);
                returnParsingText(mResponseString);
                getViewState().hideProgressBar();
            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                Log.d(TAG, "onFailure " + t);
                getViewState().hideProgressBar();
            }
        });
    }

    public void returnParsingText(StringBuilder stringBuilder) {
        getViewState().getParsingTextAPI(stringBuilder);
    }
}
