package com.example.wsy.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wsy.utils.Utils.TimeUtil;

public class TimeUtilActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_TimeShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_util);
        tv_TimeShow = (TextView) findViewById(R.id.tv_TimeShow);
    }

    @Override
    public void onClick(View v) {
        View layout = getLayoutInflater().inflate(R.layout.alertdialog_getdaydiff,
                (ViewGroup) findViewById(R.id.dialog_layout));
        final EditText et_firstDate = (EditText) layout.findViewById(R.id.et_firstDate);
        final EditText et_secondDate = (EditText) layout.findViewById(R.id.et_secondDate);
        switch (v.getId()){
            case R.id.getYear:
                tv_TimeShow.setText("");
                tv_TimeShow.setText(String.valueOf(TimeUtil.getYear(TimeUtil.StringToDate(TimeUtil.getCurDateStr("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"))));
                break;
            case R.id.getMonth:
                tv_TimeShow.setText("");
                tv_TimeShow.setText(String.valueOf(TimeUtil.getMonth(TimeUtil.StringToDate(TimeUtil.getCurDateStr("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"))));
                break;
            case R.id.getDay:
                tv_TimeShow.setText("");
                tv_TimeShow.setText(String.valueOf(TimeUtil.getDay(TimeUtil.StringToDate(TimeUtil.getCurDateStr("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"))));
                break;
            case R.id.getWeek:
                tv_TimeShow.setText("");
                tv_TimeShow.setText(String.valueOf(TimeUtil.getDayOfWeek(TimeUtil.StringToDate(TimeUtil.getCurDateStr("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"))));
                break;
            case R.id.getWeekOfYear:
                tv_TimeShow.setText("");
                tv_TimeShow.setText(String.valueOf(TimeUtil.getWeekOfYear(TimeUtil.StringToDate(TimeUtil.getCurDateStr("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"))));
                break;
            case R.id.getDayOfYear:
                tv_TimeShow.setText("");
                tv_TimeShow.setText(String.valueOf(TimeUtil.getDayOfYear(TimeUtil.StringToDate(TimeUtil.getCurDateStr("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"))));
                break;
            case R.id.getDayDiff:

                new AlertDialog.Builder(this).setTitle("请输入日期,日期格式为2017-08-20").setView(layout).setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_TimeShow.setText("");
                        tv_TimeShow.setText(String.valueOf(TimeUtil.getDayDiff(TimeUtil.StringToDate(et_firstDate.getText().toString(),
                                "yyyy-MM-dd"), TimeUtil.StringToDate(et_secondDate.getText().toString(),"yyyy-MM-dd"))));
                    }
                }).setNegativeButton("取消",null).show();

                break;
            case R.id.isBefor:
                new  AlertDialog.Builder(this).setTitle("请输入日期,日期格式为2017-08-20").setView(layout).setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TimeUtil.isBefore(TimeUtil.StringToDate(et_firstDate.getText().toString(),"yyyy-MM-dd"),
                                TimeUtil.StringToDate(et_secondDate.getText().toString(),"yyyy-MM-dd"))){
                            tv_TimeShow.setText("");
                            tv_TimeShow.setText("是");
                        }else {
                            tv_TimeShow.setText("");
                            tv_TimeShow.setText("不是");
                        }
                    }
                }).setNegativeButton("取消",null).show();



        }
    }
}
