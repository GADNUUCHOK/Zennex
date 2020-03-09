package com.example.zennex.ui.viewComponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zennex.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerComponent extends Fragment {

    private RecyclerView mRecyclerViewPopup;
    private NavigationPopupAdapter mNavigationPopupAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewPopupNavigation = inflater.inflate(R.layout.navigation_fragment, container, false);
        mRecyclerViewPopup = viewPopupNavigation.findViewById(R.id.rv_navigation_list);
        List<ItemNavigationPopup> navigationPopupList = new ArrayList<>();
        ItemNavigationPopup itemNavigationPopupList = new ItemNavigationPopup(R.drawable.clipboard_list, "List");
        ItemNavigationPopup itemNavigationPopupScaling = new ItemNavigationPopup(R.drawable.camera, "Scaling");
        ItemNavigationPopup itemNavigationPopupParsing = new ItemNavigationPopup(R.drawable.file_download, "Parsing");
        ItemNavigationPopup itemNavigationPopupMap = new ItemNavigationPopup(R.drawable.google_maps, "Map");
        navigationPopupList.add(itemNavigationPopupList);
        navigationPopupList.add(itemNavigationPopupScaling);
        navigationPopupList.add(itemNavigationPopupParsing);
        navigationPopupList.add(itemNavigationPopupMap);
        mNavigationPopupAdapter = new NavigationPopupAdapter(new NavigationPopupPresenter(navigationPopupList, getActivity()));
        mRecyclerViewPopup.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerViewPopup.setAdapter(mNavigationPopupAdapter);
        return viewPopupNavigation;
    }
}
