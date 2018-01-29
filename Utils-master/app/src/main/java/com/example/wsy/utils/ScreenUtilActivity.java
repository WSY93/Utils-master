package com.example.wsy.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wsy.utils.Utils.CommonUtils;

public class ScreenUtilActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView screenInfo_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_util);
        screenInfo_show = (TextView) findViewById(R.id.screenInfo_show);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getSW:
                screenInfo_show.setText("");
                screenInfo_show.setText(String.valueOf(CommonUtils.getScreenWidth(this)));
                break;
            case R.id.getSH:
                screenInfo_show.setText("");
                screenInfo_show.setText(String.valueOf(CommonUtils.getScreenHeight(this)));
                break;
            case R.id.getSBH:
                screenInfo_show.setText("");
                screenInfo_show.setText(String.valueOf(CommonUtils.getStatusBarHeight(this)));
                break;
            case R.id.getTBH:
                screenInfo_show.setText("");
                screenInfo_show.setText(String.valueOf(CommonUtils.getTitleBarHeight(this)));
                break;
            case R.id.setFS:
                CommonUtils.setFullScreen(this);
                break;
            case R.id.cancelFS:
                CommonUtils.cancelFullScreen(this);
                break;
        }
    }
}
