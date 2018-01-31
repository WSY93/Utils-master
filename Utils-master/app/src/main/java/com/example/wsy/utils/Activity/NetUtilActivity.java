package com.example.wsy.utils.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wsy.utils.R;
import com.example.wsy.utils.Utils.CommonUtils;

/**
 *  千万不要忘记权限 <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 */

public class NetUtilActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_netInfoShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_util);
        tv_netInfoShow = (TextView) findViewById(R.id.netInfo_show);
        setTitle("网络连接");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_isNetContented:
                tv_netInfoShow.setText("");
                if (CommonUtils.isNetContented(getApplicationContext())){
                    tv_netInfoShow.setText("网络已连接");
                }else {
                    tv_netInfoShow.setText("网络未连接");
                }
                break;
            case R.id.bt_isWIFI:
                tv_netInfoShow.setText("");
                if (CommonUtils.isNetContented(getApplicationContext())) {
                    if (CommonUtils.isWifi(getApplicationContext())) {
                        tv_netInfoShow.setText("WIFI已连接");
                    } else {
                        tv_netInfoShow.setText("WIFI未连接");
                    }
                }else {
                    tv_netInfoShow.setText("网络未连接");
                }
                break;

            case R.id.bt_openNetSetting:
                CommonUtils.openNetSetting(this);
                break;
        }

    }
}
