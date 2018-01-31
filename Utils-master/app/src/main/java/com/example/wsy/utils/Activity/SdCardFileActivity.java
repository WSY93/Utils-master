package com.example.wsy.utils.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wsy.utils.R;
import com.example.wsy.utils.Utils.CommonUtils;
import com.example.wsy.utils.Utils.FileUtil;

public class SdCardFileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_content;
    private TextView tvShow;
    private String fileName = "SDTest.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_card_file);
        setTitle("SD卡文件操作");
        et_content = (EditText) findViewById(R.id.etContent);
        tvShow = (TextView) findViewById(R.id.tvShow);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnWrite:
                boolean isWrite = FileUtil.writeToSdCardFile(fileName,et_content.getText().toString());
                if (isWrite){
                    CommonUtils.ToastShowShort(this,"写入成功");
                }else {
                    CommonUtils.ToastShowShort(this,"写入失败");
                }
                break;
            case R.id.btnRead:
                tvShow.setText("");
                if (FileUtil.readFromSdCardFile(fileName,this).equals("文件不存在")){
                    CommonUtils.ToastShowShort(this,"文件不存在，请先写入");
                }else {
                    tvShow.setText(FileUtil.readFromSdCardFile(fileName,this));
                }

                break;
            case R.id.btnDelete:
                boolean isDelete = FileUtil.deleteSdCardFile(fileName);
                if (isDelete){
                    CommonUtils.ToastShowShort(this,"删除成功");
                }else {
                    CommonUtils.ToastShowShort(this,"删除失败或文件不存在");
                }
                break;
        }
    }
}
