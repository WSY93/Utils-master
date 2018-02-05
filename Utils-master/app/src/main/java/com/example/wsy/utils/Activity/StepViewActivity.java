package com.example.wsy.utils.Activity;



import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.wsy.utils.Fragment.HorizontalStepViewFragment;
import com.example.wsy.utils.Fragment.MyCardStackFragment;
import com.example.wsy.utils.Fragment.TestCardStackFragment;
import com.example.wsy.utils.Fragment.VerticalStepViewFragment;
import com.example.wsy.utils.R;

import java.util.ArrayList;
import java.util.List;

public class StepViewActivity extends AppCompatActivity {
    private TabLayout tb;
    private ViewPager vp;

    private List<Fragment> tabFragments;
    private List<String> tabIndicators;
    private StepViewFragmentAdapter svfa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_view);
        setTitle("StepView");
        tb = (TabLayout) findViewById(R.id.stepview_tablayout);
        vp = (ViewPager) findViewById(R.id.stepview_vp);

        initContent();
        initTab();
    }

    private void initTab(){
        tb.setTabMode(TabLayout.MODE_FIXED);//设置tab沾满屏幕

        tb.setTabTextColors(ContextCompat.getColor(this, R.color.dodgerblue), ContextCompat.getColor(this, R.color.orange));
        tb.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.orange));

        ViewCompat.setElevation(tb, 10);
        tb.setupWithViewPager(vp);
    }

    private void initContent(){
        tabIndicators = new ArrayList<>();
        tabIndicators.add("HorizontalStepView");
        tabIndicators.add("VerticalStepView");
//        tabIndicators.add("我是谁");

        tabFragments = new ArrayList<>();
        tabFragments.add(new HorizontalStepViewFragment());
        tabFragments.add(new VerticalStepViewFragment());
//        tabFragments.add(new MyCardStackFragment());

        svfa = new StepViewFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(svfa);
    }

    class StepViewFragmentAdapter extends FragmentPagerAdapter {

        public StepViewFragmentAdapter(FragmentManager fm) {
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
