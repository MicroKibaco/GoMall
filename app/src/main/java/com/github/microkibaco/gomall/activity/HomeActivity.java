package com.github.microkibaco.gomall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.microkibaco.gomall.R;
import com.github.microkibaco.gomall.activity.base.BaseActivity;

/**
 * 创建所有首页的fragment,以及fragment
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor(R.color.color_fed952);
        setContentView(R.layout.activity_home_layout);
    }

    /**
     * fragment相关
     */
    @Override
    public void onClick(View v) {

    }
}
