package com.github.microkibaco.gomall.application;


import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * 1. 他是整个程序的入口
 * 2. 为整个应用的其他模块提供上下文
 * 3. 初始化工作
 */
public class MallApplication extends Application {

    private static MallApplication sMallApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sMallApplication = this;
        initJPush();
    }

    public static MallApplication getMallApplication() {
        return sMallApplication;
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

}
