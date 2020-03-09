package com.example.zennex;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.zennex.data.Entity;
import com.example.zennex.di.AppModule;
import com.example.zennex.di.DaggerAppComponent;
import com.example.zennex.di.RoomModule;
import com.example.zennex.data.AppDatabase;
import com.example.zennex.presenter.ListPresenter;
import com.example.zennex.ui.dialogComponent.DialogFragmentSave;
import com.example.zennex.ui.listComponent.ListAdapter;
import com.example.zennex.ui.listComponent.ListObject;
import com.example.zennex.view.ListViewInterface;
import com.example.zennex.view.ViewDialogWindow;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends MvpAppCompatActivity implements ListViewInterface, View.OnClickListener {
    @Inject
    public AppDatabase mAppDatabase;
    private RelativeLayout mEnterRelativeLayout;
    public EditText mEditText;
    private Button mAddButton;
    private Button mRevertButton;
    private Button mDoneButton;
    private RecyclerView mRecyclerView;
    private ListAdapter mListAdapter;
    @InjectPresenter
    public ListPresenter mListPresenter;
    Activity mActivity;
    private long mID;
    public static boolean CHANGE = false;
    private int mChangeItemPosition;

    private static final String TAG = "Tag";
    private static final String DIALOG_CREATE = "DialogCreate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mActivity = this;
        Log.d(TAG, "onCreate: ");

        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .roomModule(new RoomModule(getApplication()))
                .build()
                .inject(this);

        getAppDatabase().getEntityDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<List<Entity>>() {
                    @Override
                    public void onSuccess(List<Entity> entities) {
                        Log.d(TAG, "onSuccessssssssssssssssssssssssssssssssssss: ");
                        List<ListObject> objectList = new ArrayList<>();
                        for (int i = 0; i < entities.size(); i++) {
                            Entity entity = entities.get(i);
                            ListObject listObject = new ListObject(entity.name, entity.check);
                            listObject.setmId(entity.id);
                            objectList.add(listObject);
                        }
                        mListPresenter.mListObject = objectList;
                        mListAdapter = new ListAdapter(new com.example.zennex.ui.listComponent.ListPresenter(mListPresenter.mListObject, mActivity));
                        Log.d(TAG, "onSuccess: " + mListPresenter.mListObject.size());
                        mRecyclerView.setAdapter(mListAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mListAdapter = new ListAdapter(new com.example.zennex.ui.listComponent.ListPresenter(mListPresenter.mListObject, mActivity));
                        Log.d(TAG, "onComplete: " + mListPresenter.mListObject.size());
                        mRecyclerView.setAdapter(mListAdapter);
                    }
                });

        mEnterRelativeLayout = findViewById(R.id.rl_enter);
        mRecyclerView = findViewById(R.id.rv_list);
        mEditText = findViewById(R.id.et_enter_title);
        mAddButton = findViewById(R.id.btn_add);
        mAddButton.setOnClickListener(this);
        mDoneButton = findViewById(R.id.btn_done);
        mDoneButton.setOnClickListener(this);
        mRevertButton = findViewById(R.id.btn_revert);
        mRevertButton.setOnClickListener(this);
        mListAdapter = new ListAdapter(new com.example.zennex.ui.listComponent.ListPresenter(mListPresenter.mListObject, this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mListAdapter);
    }

    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_done) {
            mListPresenter.getEditText(mEditText);
            if (CHANGE) mListPresenter.getPosition(mChangeItemPosition);
        }
        mListPresenter.setOnClickButton(v);
        mEditText.setText("");
    }

    public void showRelativeAddItem() {
        mEnterRelativeLayout.setVisibility(View.VISIBLE);
    }

    public void hideRelativeAddItem() {
        mEnterRelativeLayout.setVisibility(View.GONE);
        mEditText.setText("");
    }

    @Override
    public void addNewItem() {
        Entity entity = new Entity();
        int i = mListPresenter.mListObject.size();
        Log.d(TAG, "addNewItem:SIZE " + i);
        long id = 0;
        if (i - 1 != 0) {
            Log.d(TAG, "addNewItem:ID " + getAppDatabase().getEntityDao().getAll().blockingGet().get(i-2).id);
            id = getAppDatabase().getEntityDao().getAll().blockingGet().get(i-2).id;
        }
        mID = id + 1;
        entity.id = id + 1;
        entity.name = mEditText.getText().toString();
        entity.check = false;
        getAppDatabase().getEntityDao().insert(entity);
        mListAdapter.notifyDataSetChanged();
        setId();
        Log.d(TAG, "addNewItem:Position " +(i));
        Log.d(TAG, "addNewItem:GET ID " + mListPresenter.mListObject.get(i -1).getmId());
    }

    @Override
    public void saveListState() {

    }

    @Override
    public void revertItem() {

    }

    public void changeItem() {
        String editText = mEditText.getText().toString();
        mListPresenter.mListObject.get(mChangeItemPosition).setmTitle(editText);
        Log.d(TAG, "changeItem:mChangeItemPosition " + mChangeItemPosition);
        mListAdapter.notifyDataSetChanged();
        long id = mListPresenter.mListObject.get(mChangeItemPosition).getmId();
        Log.d(TAG, "changeItem:ID " + id);
        Entity entity = getAppDatabase().getEntityDao().getById(id).blockingGet();
        entity.name = editText;
        getAppDatabase().getEntityDao().update(entity);
        mEditText.setText("");
    }

    public void deleteItem(int listObject) {
        Log.d(TAG, "deleteItem:position " + listObject);
        long id = mListPresenter.mListObject.get(listObject).getmId();
        mListPresenter.mListObject.remove(listObject);
        mListAdapter.notifyDataSetChanged();
        Log.d(TAG, "deleteItem:ID " + id);
        Entity entity = getAppDatabase().getEntityDao().getById(id).blockingGet();
        getAppDatabase().getEntityDao().delete(entity);
    }

    public void saveChangeData(long id, String name, boolean check) {
        Entity entity = getAppDatabase().getEntityDao().getById(id).blockingGet();
        entity.check = check;
        entity.name = name;
        getAppDatabase().getEntityDao().update(entity);
    }

    public void setId() {
        int size = mListPresenter.mListObject.size();
        mListPresenter.mListObject.get(size - 1).setmId(mID);
    }

    public void setChangeItem(int mListObject) {
        mChangeItemPosition = mListObject;
        Log.d(TAG, "setChangeItem:mListObject " + mListObject);
        Log.d(TAG, "setChangeItem:mChangeItemPosition " + mChangeItemPosition);
    }

    public void setCheck(int position) {
        Log.d(TAG, "setCheck:position " + mListPresenter.mListObject.get(position));
        Log.d(TAG, "setCheck:check " + mListPresenter.mListObject.get(position).ismCheck());
        long id = mListPresenter.mListObject.get(position).getmId();
        Entity entity = getAppDatabase().getEntityDao().getById(id).blockingGet();
        entity.check = mListPresenter.mListObject.get(position).ismCheck();
        getAppDatabase().getEntityDao().update(entity);
    }

    @Override
    public void onBackPressed() {
        if (CHANGE && mEnterRelativeLayout.getVisibility() == View.VISIBLE) {
//            mListPresenter.backPressed();
            DialogFragmentSave dialogFragmentSave = new DialogFragmentSave();
            dialogFragmentSave.pressObject(mChangeItemPosition);
            dialogFragmentSave.show(this.getFragmentManager(), DIALOG_CREATE);
        }
//        super.onBackPressed();
    }
}
