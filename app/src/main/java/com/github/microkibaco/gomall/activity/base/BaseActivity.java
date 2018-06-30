package com.github.microkibaco.gomall.activity.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 是为我们所有的Activity,提供公共的行为
 */
public abstract class BaseActivity extends AppCompatActivity {

    /*
     * 输出日志所需要的TAG
     */
    public String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


}
