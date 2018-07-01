package com.github.microkibaco.gomall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.microkibaco.gomall.R;
import com.github.microkibaco.gomall.fragment.base.BaseFragment;

public class MineFragment extends BaseFragment {
    private View mContentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_mine_layout, container, false);
        return mContentView;

    }

}
