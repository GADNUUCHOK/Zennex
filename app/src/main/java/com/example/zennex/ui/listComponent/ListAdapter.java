package com.example.zennex.ui.listComponent;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *
 */
public class ListAdapter
        extends RecyclerView.Adapter<ListHolder> {

    private final ListPresenter mListPresenter;

    public ListAdapter(ListPresenter listPresenter) {
        mListPresenter = listPresenter;
    }

    /**
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new ListHolder(layoutInflater, viewGroup, mListPresenter);
    }

    /**
     *
     * @param listHolder
     * @param i
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {
        mListPresenter.onBindViewAtPosition(i, listHolder);
    }

    /**
     * Метод возвращает размер списка
     * @return возвращает размер списка
     */
    @Override
    public int getItemCount() {
        return mListPresenter.getCount();
    }
}
