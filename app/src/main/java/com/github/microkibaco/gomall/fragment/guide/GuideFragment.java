package com.github.microkibaco.gomall.fragment.guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GuideFragment extends Fragment {
    private final static String LAYOUT_ID = "layoutid";

    public static GuideFragment newInstance(int layoutId) {

        final GuideFragment pane = new GuideFragment();
        final Bundle args = new Bundle();
        args.putInt(LAYOUT_ID, layoutId);
        pane.setArguments(args);
        return pane;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(getArguments().getInt(LAYOUT_ID, -1), container, false);
    }
}
