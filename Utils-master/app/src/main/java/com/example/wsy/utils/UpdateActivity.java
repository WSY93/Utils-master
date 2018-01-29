package com.example.wsy.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wsy.utils.Utils.DownloadUtils;
import com.example.wsy.utils.Utils.PermissionUtils;

import static com.example.wsy.utils.R.id.bt_update;
/*
    兼容android7.0   对于部分机型系统下载管理器被禁止也做了应对

 */

public class UpdateActivity extends AppCompatActivity {
    private DownloadUtils downloadUtils;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        bt = (Button) findViewById(bt_update);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtils.requestPermission(getParent(),PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE,null);
                downloadUtils = new DownloadUtils(UpdateActivity.this);
                downloadUtils.download("http://gdown.baidu.com/data/wisegame/fd84b7f6746f0b18/baiduyinyue_4802.apk","测试.apk");
            }
        });
    }
}
