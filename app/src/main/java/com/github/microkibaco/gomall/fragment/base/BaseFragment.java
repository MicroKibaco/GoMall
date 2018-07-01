package com.github.microkibaco.gomall.fragment.base;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.github.microkibaco.gomall.constant.Constant;


/**
 * 主要为我们所有的fragment提供公共的行为或事件
 */

public abstract class BaseFragment extends Fragment {

    protected Activity mActivity;


    public void requestPermission(int code, String... permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions, code);
        }
    }


    public boolean hasPermission(String... permissions) {

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.HARD_WEAR_CAMERA_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doOpenCamera();
                }
                break;
            case Constant.WRITE_READ_EXTERNAL_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doWriteSDCard();
                }
                break;
        }
    }

    private void doOpenCamera() {

    }

    private void doWriteSDCard() {

    }
}
