package com.github.microkibaco.gomall.application;


import android.app.Application;

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
    }

    public static MallApplication getMallApplication() {
        return sMallApplication;
    }
}
