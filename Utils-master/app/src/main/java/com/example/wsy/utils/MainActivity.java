package com.example.wsy.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.wsy.utils.Utils.PaintActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_sd:
                Intent intent = new Intent(MainActivity.this,SdCardFileActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_app:
                Intent intent1 = new Intent(MainActivity.this,AppDataFileActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_appinfo:
                Intent intent2 = new Intent(MainActivity.this,AppInfoActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_screen_info:
                Intent intent3 = new Intent(MainActivity.this,ScreenUtilActivity.class);
                startActivity(intent3);
                break;
            case R.id.bt_time:
                Intent intent4 = new Intent(MainActivity.this,TimeUtilActivity.class);
                startActivity(intent4);
                break;
            case R.id.bt_net:
                Intent intent5 = new Intent(MainActivity.this,NetUtilActivity.class);
                startActivity(intent5);
                break;
            case R.id.bt_sp:
                Intent intent6 = new Intent(MainActivity.this,SharedPreferencesUtilActivity.class);
                startActivity(intent6);
                break;

            case R.id.bt_getPermissions:
                Intent intent7 = new Intent(MainActivity.this,RuntimePermissionsActivity.class);
                startActivity(intent7);
                break;
            case R.id.bt_camera:
                Intent intent8 = new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent8);
                break;
            case R.id.bt_paint:
                Intent intent9 = new Intent(MainActivity.this, PaintActivity.class);
                startActivity(intent9);
                break;
            case R.id.bt_update:
                Intent intent10 = new Intent(MainActivity.this,UpdateActivity.class);
                startActivity(intent10);
                break;
            case R.id.bt_dialog:
                Intent intent11 = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent11);
                break;
        }
    }
}
