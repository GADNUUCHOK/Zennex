package com.example.zennex.ui.viewComponent;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NavigationPopupAdapter extends RecyclerView.Adapter<NavigationPopupHolder> {

    private final NavigationPopupPresenter mNavigationPopupPresenter;

    public NavigationPopupAdapter(NavigationPopupPresenter navigationPopupPresenter) {
        mNavigationPopupPresenter = navigationPopupPresenter;
    }

    /**
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public NavigationPopupHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new NavigationPopupHolder(layoutInflater, viewGroup, mNavigationPopupPresenter);
    }

    /**
     *
     * @param navigationPopupHolder
     * @param i
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NavigationPopupHolder navigationPopupHolder, int i) {
        mNavigationPopupPresenter.onBindViewAtPosition(i, navigationPopupHolder);
    }

    /**
     * Метод возвращает размер списка
     * @return возвращает размер списка
     */
    @Override
    public int getItemCount() {
        return mNavigationPopupPresenter.getCount();
    }
}
