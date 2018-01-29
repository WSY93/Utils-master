package com.example.wsy.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wsy.utils.Utils.CommonUtils;
import com.example.wsy.utils.Utils.FileUtil;

public class AppDataFileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_content;
    private TextView tvShow;
    private String fileName = "AppDataTest.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_data_file);
        setTitle("内部存储文件操作");
        et_content = (EditText) findViewById(R.id.etContent);
        tvShow = (TextView) findViewById(R.id.tvShow);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnWrite:
                boolean isWrite =  FileUtil.writeToDataAppFile(this,fileName,et_content.getText().toString());
                if (isWrite){
                    CommonUtils.ToastShowShort(this,"写入成功");

                }else {
                    CommonUtils.ToastShowShort(this,"写入失败");
                }
                break;
            case R.id.btnRead:
                tvShow.setText("");
                if (FileUtil.readFromDataAppFile(this,fileName).equals("文件不存在")){
                    CommonUtils.ToastShowShort(this,"读取失败，文件不存在");
                }else {
                    tvShow.setText(FileUtil.readFromDataAppFile(this,fileName));
                }
                break;
            case R.id.btnDelete:
                boolean isDelete = FileUtil.deleteDataAppFile(this,fileName);
                if (isDelete){
                    CommonUtils.ToastShowShort(this,"删除成功");
                }else {
                    CommonUtils.ToastShowShort(this,"删除失败");
                }
                break;
        }
    }
}
