package com.example.wsy.utils.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wsy.utils.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_zhedie:
                Intent intent = new Intent(Main2Activity.this,MyScrollingActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_shibie:
                Intent intent1 = new Intent(Main2Activity.this,RecognitionActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
