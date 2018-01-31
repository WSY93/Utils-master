package com.example.wsy.utils.Activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.wsy.utils.R;
import com.example.wsy.utils.SelfDefined.LoveLayout;

public class PeriscopeActivity extends AppCompatActivity {
    private LoveLayout love;
    private ImageButton bt_love;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periscope);
        love = (LoveLayout) findViewById(R.id.love);
        bt_love = (ImageButton) findViewById(R.id.bt_love);
        bt_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                love.addLoveView();
            }
        });
    }
}
