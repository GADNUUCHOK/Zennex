package com.example.zennex.ui.listComponent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.zennex.R;

import static com.example.zennex.activity.ScalingActivity.TAG;

/**
 *
 */
public class ListHolder
        extends RecyclerView.ViewHolder
        implements RowView, View.OnClickListener, View.OnLongClickListener, CheckBox.OnCheckedChangeListener {

    private TextView mTitle;
    private ImageView mPicture;
    private CheckBox mCheckBox;
    private ListObject mListObject;
    private ListPresenter mListPresenter;

    /**
     * Конструктор для отображения элемента
     * @param inflater область элемента
     * @param parent родительский элемент
     */
    ListHolder(LayoutInflater inflater, ViewGroup parent, ListPresenter listPresenter) {
        super(inflater.inflate(R.layout.item_list, parent, false));
        mListPresenter = listPresenter;
        mPicture = itemView.findViewById(R.id.iv_icon);
        mTitle = itemView.findViewById(R.id.tv_title);
        mCheckBox = itemView.findViewById(R.id.cb_checkbox);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        mCheckBox.setOnCheckedChangeListener(this);

    }

    /**
     *
     * @param listObject
     */
    public void bind(ListObject listObject) {
        mListObject = listObject;
    }

    @Override
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public void setCheck(boolean bool) {
        Log.d(TAG, "setCheck: ");
        mCheckBox.setChecked(bool);
        if (mCheckBox.isChecked()) {
            mPicture.setImageResource(R.drawable.image_one);
        } else {
            mPicture.setImageResource(R.drawable.image_two);
        }
        mListPresenter.onCheck(this.getAdapterPosition());
    }

    @Override
    public void saveList() {

    }

    @Override
    public void onClick(View v) {
        if (mListPresenter != null) {
            mListPresenter.onClickItemPosition(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        mListPresenter.onLongClickItemPosition(getAdapterPosition());
        return false;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(TAG, "onCheckedChanged: ");
        mCheckBox.setChecked(isChecked);
        mListObject.setmCheck(isChecked);
        if (mCheckBox.isChecked()) {
            mPicture.setImageResource(R.drawable.image_one);
        } else {
            mPicture.setImageResource(R.drawable.image_two);
        }
        mListPresenter.onCheck(this.getAdapterPosition());
    }

    public void setPicture(int drawable) {
        mPicture.setImageResource(drawable);
    }
}
