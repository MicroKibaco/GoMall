package com.github.microkibaco.gomall.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.github.microkibaco.gomall.R;
import com.github.microkibaco.gomall.activity.base.BaseActivity;
import com.github.microkibaco.gomall.fragment.HomeFragment;
import com.github.microkibaco.gomall.fragment.MessageFragment;
import com.github.microkibaco.gomall.fragment.MineFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建所有首页的fragment,以及fragment
 */

public class HomeActivity extends BaseActivity {

    @Bind(R.id.home_image_view)
    AppCompatTextView mHomeImageView;
    @Bind(R.id.fish_image_view)
    AppCompatTextView mFishImageView;
    @Bind(R.id.message_image_view)
    AppCompatTextView mMessageImageView;
    @Bind(R.id.mine_image_view)
    AppCompatTextView mMineImageView;


    private Fragment mCurrent, mCommonFragmentOne;
    private FragmentManager fm;

    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeStatusBarColor(R.color.color_fed952);
        setContentView(R.layout.activity_home_layout);

        ButterKnife.bind(this);
        initFragment();
        startAllService();
    }

    /**
     * 启动后台更新服务
     */
    private void startAllService() {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initFragment() {
        mHomeImageView.setBackgroundResource(R.drawable.comui_tab_home_selected);
        mHomeFragment = new HomeFragment();
        fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout, mHomeFragment);
        fragmentTransaction.commit();
    }


    @OnClick({R.id.home_layout_view, R.id.message_layout_view, R.id.mine_layout_view})
    public void onViewClicked(View view) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.home_layout_view:

                changeStatusBarColor(R.color.color_fed952);
                mHomeImageView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mFishImageView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageImageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineImageView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                hideFragment(mMineFragment, fragmentTransaction);


                if (mHomeFragment == null) {

                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout, mHomeFragment);

                } else {

                    mCurrent = mHomeFragment;
                    fragmentTransaction.show(mHomeFragment);

                }
                break;
            case R.id.message_layout_view:

                changeStatusBarColor(R.color.color_e3e3e3);
                mMessageImageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mHomeImageView.setBackgroundResource(R.drawable.comui_tab_home);
                mFishImageView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMineImageView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);
                hideFragment(mMineFragment, fragmentTransaction);

                if (mMessageFragment == null) {

                    mMessageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.content_layout, mMessageFragment);

                } else {

                    mCurrent = mMessageFragment;
                    fragmentTransaction.show(mMessageFragment);

                }
                break;
            case R.id.mine_layout_view:

                changeStatusBarColor(R.color.white);
                mMineImageView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                mHomeImageView.setBackgroundResource(R.drawable.comui_tab_home);
                mFishImageView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageImageView.setBackgroundResource(R.drawable.comui_tab_message);

                hideFragment(mCommonFragmentOne, fragmentTransaction);
                hideFragment(mMessageFragment, fragmentTransaction);
                hideFragment(mHomeFragment, fragmentTransaction);

                if (mMineFragment == null) {

                    mMineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.content_layout, mMineFragment);

                } else {

                    mCurrent = mMineFragment;
                    fragmentTransaction.show(mMineFragment);

                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(Fragment fragment, FragmentTransaction ft) {
        if (fragment != null) {
            ft.hide(fragment);
        }
    }
}
