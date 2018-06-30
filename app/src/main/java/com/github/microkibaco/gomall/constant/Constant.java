package com.github.microkibaco.gomall.constant;

import android.Manifest;
import android.os.Environment;

/**
 * 全局常量存储
 */

public class Constant {


    /**
     * 权限常量相关
     */
    public static final int WRITE_READ_EXTERNAL_CODE = 0x01;
    public static final int HARD_WEAR_CAMERA_CODE = 0x02;

    public static final String[] HARD_WEAR_CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    public static final String[] WRITE_READ_EXTERNAL_PERMISSION = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    // 整个应用文件下载保存路径
    public static String APP_PHOTO_DIR = Environment
            .getExternalStorageDirectory()
            .getAbsolutePath()
            .concat("/go_mall/photo/");
}
