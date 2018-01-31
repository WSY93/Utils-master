package com.example.wsy.utils.Activity;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.wsy.utils.Fragment.MyCardStackFragment;
import com.example.wsy.utils.Fragment.TestCardStackFragment;
import com.example.wsy.utils.R;


import java.util.ArrayList;
import java.util.List;

public class CardStackViewActivity extends AppCompatActivity  {

    private TabLayout mTabTl;
    private ViewPager mContentVp;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_stack_view);

        mTabTl = (TabLayout) findViewById(R.id.tabLayout);
        mContentVp = (ViewPager) findViewById(R.id.viewPager);

        initContent();
        initTab();
    }
    private void initTab(){
        mTabTl.setTabMode(TabLayout.MODE_SCROLLABLE);

        mTabTl.setTabTextColors(ContextCompat.getColor(this, R.color.dodgerblue), ContextCompat.getColor(this, R.color.orange));
        mTabTl.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.orange));
        ViewCompat.setElevation(mTabTl, 10);
        mTabTl.setupWithViewPager(mContentVp);
    }

    private void initContent(){
        tabIndicators = new ArrayList<>();
        tabIndicators.add("示例");
        tabIndicators.add("我的");
//        tabIndicators.add("我是谁");

        tabFragments = new ArrayList<>();
        tabFragments.add(new TestCardStackFragment());
        tabFragments.add(new MyCardStackFragment());
//        tabFragments.add(new MyCardStackFragment());

        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentAdapter);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }




}
