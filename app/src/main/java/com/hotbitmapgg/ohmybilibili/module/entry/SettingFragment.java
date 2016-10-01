package com.hotbitmapgg.ohmybilibili.module.entry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.hotbitmapgg.ohmybilibili.R;
import com.hotbitmapgg.ohmybilibili.base.RxLazyFragment;
import com.hotbitmapgg.ohmybilibili.module.common.AppIntroduceActivity;
import com.hotbitmapgg.ohmybilibili.module.common.HotBitmapGGInfoActivity;
import com.hotbitmapgg.ohmybilibili.module.common.LoginActivity;
import com.hotbitmapgg.ohmybilibili.module.common.MainActivity;
import com.hotbitmapgg.ohmybilibili.utils.PreferenceUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by hcc on 16/8/7 14:12
 * 100332338@qq.com
 * <p/>
 * 设置与帮助
 */
public class SettingFragment extends RxLazyFragment
{


    @Bind(R.id.app_version_code)
    TextView mVersionCode;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    public static SettingFragment newInstance()
    {

        return new SettingFragment();
    }

    @Override
    public int getLayoutResId()
    {

        return R.layout.fragment_setting;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void finishCreateView(Bundle state)
    {

        mToolbar.setTitle("设置与帮助");
        mToolbar.setNavigationIcon(R.drawable.ic_navigation_drawer);
        mToolbar.setNavigationOnClickListener(v -> {

            Activity activity1 = getActivity();
            if (activity1 instanceof MainActivity)
                ((MainActivity) activity1).toggleDrawer();
        });

        mVersionCode.setText("v" + getVersionCode());
    }


    @OnClick(R.id.layout_about_me)
    void startAboutMeActivity()
    {
        //关于我
        startActivity(new Intent(getActivity(), HotBitmapGGInfoActivity.class));
    }

    @OnClick(R.id.layout_about_app)
    void startAboutBiliBiliActivity()
    {
        //关于哔哩哔哩
        startActivity(new Intent(getActivity(), AppIntroduceActivity.class));
    }

    @OnClick(R.id.btn_logout)
    void logout()
    {
        //退出登录  清空本地保存的头像跟登录flag
        PreferenceUtils.remove("login");
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }


    public String getVersionCode()
    {

        PackageInfo packageInfo = null;
        try
        {
            packageInfo = getActivity().getPackageManager()
                    .getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        assert packageInfo != null;
        return packageInfo.versionName;
    }


    @Override
    protected void lazyLoad()
    {

    }
}
