package com.example.wsy.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wsy.utils.Utils.CommonUtils;

public class AppInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView appinfo_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        setTitle("APP信息");
        appinfo_show = (TextView) findViewById(R.id.appinfo_show);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_app_name:
                appinfo_show.setText("");
                appinfo_show.setText(CommonUtils.getAppName(this));
                break;
            case R.id.get_ver_name:
                appinfo_show.setText("");
                appinfo_show.setText(CommonUtils.getVersionName(this));
                break;
            case R.id.get_ver_code:
                appinfo_show.setText("");
                appinfo_show.setText(String.valueOf(CommonUtils.getVersionCode(this)));
                break;
        }

    }
}
