package com.bawei.yklx.ui.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawei.yklx.ui.fragment.FragmentHome;
import com.bawei.yklx.ui.fragment.FragmentMine;

public class FragPagerAdapter extends FragmentPagerAdapter {

    private String[] names = new String[]{
            "首页","我的"
    };
    public FragPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new FragmentHome();
            case 1:
                return new FragmentMine();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];

    }
}
