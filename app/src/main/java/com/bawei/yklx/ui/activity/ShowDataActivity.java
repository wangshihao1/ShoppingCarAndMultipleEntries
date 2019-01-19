package com.bawei.yklx.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bawei.yklx.R;
import com.bawei.yklx.ui.Adapter.FragPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowDataActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    private FragPagerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        tablayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);
        adapter = new FragPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }
}
