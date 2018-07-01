package com.github.microkibaco.gomall.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.microkibaco.gomall.R;
import com.github.microkibaco.gomall.fragment.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    private View mContentView;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        mActivity = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        return mContentView;

    }
}
