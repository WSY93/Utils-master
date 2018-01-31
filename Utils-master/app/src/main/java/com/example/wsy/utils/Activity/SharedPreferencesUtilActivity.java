package com.example.wsy.utils.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wsy.utils.R;
import com.example.wsy.utils.Utils.CommonUtils;

public class SharedPreferencesUtilActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_content;
    private TextView SPInfo_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_util);
        et_content = (EditText) findViewById(R.id.et_content);
        SPInfo_show = (TextView) findViewById(R.id.SPInfo_show);
        setTitle("SharedPreferences");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_sp_save:
                CommonUtils.putSP(this,"test",et_content.getText());
                break;
            case R.id.bt_sp_get:
                SPInfo_show.setText("");
                SPInfo_show.setText(CommonUtils.getSP(this,"test","default").toString());
                break;
            case R.id.bt_sp_clear:
                CommonUtils.clearSP(this);
                break;
        }
    }
}
