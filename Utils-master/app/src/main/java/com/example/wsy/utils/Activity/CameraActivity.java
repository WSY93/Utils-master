package com.example.wsy.utils.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.wsy.utils.R;
import com.example.wsy.utils.Utils.CameraUtil;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.PZ:
                CameraUtil.openCamera(this);
                break;
            case R.id.LX:
                CameraUtil.startToVideo(this);
                break;
            case R.id.openAlbum:
                CameraUtil.openAlbum(this);
                break;
        }
    }
}
