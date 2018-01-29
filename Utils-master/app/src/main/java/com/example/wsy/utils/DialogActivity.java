package com.example.wsy.utils;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wsy.utils.Utils.CommonUtils;

import java.util.Timer;
import java.util.TimerTask;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_pt:
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.xiaoren)//设置标题的图片
                        .setTitle("我是对话框")//设置对话框的标题
                        .setMessage("我是对话框的内容")//设置对话框的内容
                        //设置对话框的按钮
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
                break;
            case R.id.bt_lb:
                final String items[] = {"我是Item一", "我是Item二", "我是Item三", "我是Item四"};
                AlertDialog dialog1 = new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.xiaoren)//设置标题的图片
                        .setTitle("列表对话框")//设置对话框的标题
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, items[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog1.show();
                break;
            case R.id.bt_dx:
                final String items1[] = {"我是Item一", "我是Item二", "我是Item三", "我是Item四"};
                AlertDialog dialog2 = new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.xiaoren)//设置标题的图片
                        .setTitle("单选列表对话框")//设置对话框的标题
                        .setSingleChoiceItems(items1, 1, new DialogInterface.OnClickListener() {//第一个参数:设置单选的资源数组;第二个参数:设置默认选中哪一项。
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DialogActivity.this, items1[which], Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog2.show();
                break;
            case R.id.bt_duox:
                final String items2[] = {"我是Item一", "我是Item二", "我是Item三", "我是Item四"};
                final boolean checkedItems[] = {true, false, true, false};
                AlertDialog dialog3 = new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.xiaoren)//设置标题的图片
                        .setTitle("多选对话框")//设置对话框的标题
                        .setMultiChoiceItems(items2, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {//第一个参数:设置单选的资源;第二个参数:设置默认选中哪几项（数组）
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkedItems[which] = isChecked;
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < checkedItems.length; i++) {
                                    if (checkedItems[i]) {
                                        Toast.makeText(DialogActivity.this, "选中了" + i, Toast.LENGTH_SHORT).show();
                                    }
                                }
                                dialog.dismiss();
                            }
                        }).create();
                dialog3.show();
                break;
            case R.id.bt_hzdy:
                final EditText editText = new EditText(this);
                AlertDialog dialog4 = new AlertDialog.Builder(this)
                        .setIcon(R.mipmap.xiaoren)//设置标题的图片
                        .setTitle("半自定义对话框")//设置对话框的标题
                        .setView(editText)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String content = editText.getText().toString();
                                Toast.makeText(DialogActivity.this, content, Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).create();
                dialog4.show();
                break;
            case R.id.bt_zdyyxjdt:
                final CommonUtils.Loading_view loading_view = new  CommonUtils.Loading_view(this,R.style.CustomDialog);
                loading_view.show();
                new Handler().postDelayed(new Runnable() {//定义延时任务模仿网络请求
                    @Override
                    public void run() {
                        loading_view.dismiss();//3秒后调用关闭加载的方法
                    }
                }, 3000);

                break;
            case R.id.bt_yxjdt:
                ProgressDialog dialog5 = new ProgressDialog(this);
                dialog5.setMessage("正在加载中");
                dialog5.show();
                break;
            case R.id.bt_spjdt:
                final ProgressDialog dialog6 = new ProgressDialog(this);
                dialog6.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog6.setMessage("正在加载中");
                dialog6.setMax(100);
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    int progress = 0;

                    @Override
                    public void run() {
                        dialog6.setProgress(progress += 5);
                        if (progress == 100) {
                            timer.cancel();
                        }
                    }
                }, 0, 1000);
                dialog6.show();
                break;
            case R.id.bt_zdy:
                final CommonUtils.SelfDialog selfDialog = new CommonUtils.SelfDialog(this);
                selfDialog.setTitle("提示");
                selfDialog.setMessage("确定退出应用?");
                selfDialog.setYesOnclickListener("确定", new CommonUtils.SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Toast.makeText(getBaseContext(),"点击了--确定--按钮",Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.setNoOnclickListener("取消", new CommonUtils.SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(getBaseContext(),"点击了--取消--按钮",Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
                break;
        }
    }
}
