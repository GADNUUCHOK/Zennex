package com.example.zennex.ui.viewComponent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.zennex.R;

public class NavigationPopupHolder extends RecyclerView.ViewHolder
        implements RowNavigationPopupView, View.OnClickListener {

    private TextView mTitle;
    private ImageView mIcon;
    private NavigationPopupPresenter mNavigationPopupPresenter;

    /**
     * Конструктор для отображения элемента
     * @param inflater область элемента
     * @param parent родительский элемент
     */
    NavigationPopupHolder(LayoutInflater inflater, ViewGroup parent, NavigationPopupPresenter navigationPopupPresenter) {
        super(inflater.inflate(R.layout.item_navigation_list, parent, false));
        mNavigationPopupPresenter = navigationPopupPresenter;
        mIcon = itemView.findViewById(R.id.iv_icon);
        mTitle = itemView.findViewById(R.id.tv_title);
        itemView.setOnClickListener(this);

    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setIcon(int image) {
        mIcon.setImageResource(image);
    }

    @Override
    public void onClick(View v) {
        if (mNavigationPopupPresenter != null) {
            mNavigationPopupPresenter.onClickItemPosition(getAdapterPosition());
        }
    }
}
