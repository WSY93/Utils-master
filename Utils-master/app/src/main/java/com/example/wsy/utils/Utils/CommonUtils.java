package com.example.wsy.utils.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wsy.utils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsy on 2017/8/17.
 */

public class CommonUtils {
    /**
     * SharedPreferences 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";
    /**
     * 短时间显示Toast
     * @param context
     * @param content
     */
    public static void ToastShowShort(Context context,String content){
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }


    /**
     * 长时间显示Toast
     * @param context
     * @param content
     */
    public static void ToastShowLong(Context context,String content){
        Toast.makeText(context,content,Toast.LENGTH_LONG).show();

    }

    /**
     * 获取应用版本名称
     * @param context
     * @return 返回值为获取到的应用程序名称
     */
    public static String getVersionName(Context context){

        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            String  versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用版本号
     * @param context
     * @return 返回值为版本号
     */

    public static int getVersionCode(Context context){

        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取app的名字
     * @param context
     * @return  返回值为app的名字
     */
    public static String getAppName(Context context){
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            int labelRes = applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  获取屏幕宽度
     * @param activity
     * @return 返回值即为屏幕宽度
     */
    public static int getScreenWidth(Activity activity){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;

    }

    /**
     *  获取屏幕高度
     * @param activity
     * @return 返回值即为屏幕高度
     */

    public static int getScreenHeight(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 设置全屏
     * @param activity
     */
    public static void setFullScreen(Activity activity){
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     *  取消全屏
     * @param activity
     */
    public static void cancelFullScreen(Activity activity){
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.getWindow().setAttributes(attrs);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    }

    /**
     *  获取状态栏的高度
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity){
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;

    }

    /**
     *  获取标题栏的高度
     * @param activity
     * @return
     */
    public static int getTitleBarHeight(Activity activity){
        int cotentTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return cotentTop - getStatusBarHeight(activity);
    }

    /**
     * 判断网络是否连接
     * @param context
     * @return
     */

    public static boolean isNetContented(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager){
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != networkInfo && networkInfo.isConnected()){
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     * @param context
     * @return
     */

    public static boolean isWifi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null){
            return false;
        }
        return connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 打开网络设置界面
     * @param
     */

    public static void openNetSetting(Context context){
        //安卓版本3.0以上
        if (Build.VERSION.SDK_INT>10){
            context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
        }else {
            Intent intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
            context.startActivity(intent);
        }


    }

    /**
     * SharedPreferences 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     * @param context
     * @param key
     * @param object
     */


    public static void putSP(Context context, String key, Object object){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (object instanceof String){
            editor.putString(key,(String) object);
        }else if (object instanceof Integer){
            editor.putInt(key,(Integer) object);
        }else if (object instanceof Boolean){
            editor.putBoolean(key, (Boolean) object);
        }else if (object instanceof Float){
            editor.putFloat(key, (Float) object);
        }else if (object instanceof Long){
            editor.putLong(key, (Long) object);
        }else {
            editor.putString(key,object.toString());
        }
        editor.apply();
    }

    /**
     * SharedPreferences  得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getSP(Context context, String key, Object defaultObject){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        if (defaultObject instanceof String){
            return sp.getString(key, (String) defaultObject);
        }else if (defaultObject instanceof Integer){
            return sp.getInt(key, (Integer) defaultObject);
        }else if (defaultObject instanceof Boolean){
            return sp.getBoolean(key, (Boolean) defaultObject);
        }else if (defaultObject instanceof Float){
            return sp.getFloat(key, (Float) defaultObject);
        }else if (defaultObject instanceof Long){
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 清除SharedPreferences保存的所有数据
     * @param context
     */

    public static void clearSP(Context context){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * SharedPreferences 移除某个key值已经对应的值
     * @param context
     * @param key
     */

    public static void removeSPKey(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 打开某app设置界面
     * @param context
     */
    public static void openAppSetting(Context context){
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }
    /**
     * 自定义圆形进度条对话框
     */
    public static class Loading_view extends ProgressDialog {
        public Loading_view(Context context) {
            super(context);
        }
        public Loading_view(Context context, int theme) {
            super(context, theme);
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            init(getContext());
        }
        private void init(Context context) {
            setCancelable(true);
            setCanceledOnTouchOutside(false);
            setContentView(R.layout.loading);//loading的xml文件
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getWindow().setAttributes(params);
        }
        @Override
        public void show() {//开启
            super.show();
        }
        @Override
        public void dismiss() {//关闭
            super.dismiss();
        }
    }


    /**
     * 创建自定义的dialog，主要学习其实现原理
     * Created by chengguo on 2016/3/22.
     */
    public static class SelfDialog extends Dialog {

        private Button yes;//确定按钮
        private Button no;//取消按钮
        private TextView titleTv;//消息标题文本
        private TextView messageTv;//消息提示文本
        private String titleStr;//从外界设置的title文本
        private String messageStr;//从外界设置的消息文本
        //确定文本和取消文本的显示内容
        private String yesStr, noStr;

        private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
        private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器

        /**
         * 设置取消按钮的显示内容和监听
         *
         * @param str
         * @param onNoOnclickListener
         */
        public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
            if (str != null) {
                noStr = str;
            }
            this.noOnclickListener = onNoOnclickListener;
        }

        /**
         * 设置确定按钮的显示内容和监听
         *
         * @param str
         * @param onYesOnclickListener
         */
        public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
            if (str != null) {
                yesStr = str;
            }
            this.yesOnclickListener = onYesOnclickListener;
        }

        public SelfDialog(Context context) {
            super(context, R.style.MyDialog);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.free_exercise_sure_dialog_layout);
            //按空白处不能取消动画
            setCanceledOnTouchOutside(false);

            //初始化界面控件
            initView();
            //初始化界面数据
            initData();
            //初始化界面控件的事件
            initEvent();

        }

        /**
         * 初始化界面的确定和取消监听器
         */
        private void initEvent() {
            //设置确定按钮被点击后，向外界提供监听
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (yesOnclickListener != null) {
                        yesOnclickListener.onYesClick();
                    }
                }
            });
            //设置取消按钮被点击后，向外界提供监听
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (noOnclickListener != null) {
                        noOnclickListener.onNoClick();
                    }
                }
            });
        }

        /**
         * 初始化界面控件的显示数据
         */
        private void initData() {
            //如果用户自定了title和message
            if (titleStr != null) {
                titleTv.setText(titleStr);
            }
            if (messageStr != null) {
                messageTv.setText(messageStr);
            }
            //如果设置按钮的文字
            if (yesStr != null) {
                yes.setText(yesStr);
            }
            if (noStr != null) {
                no.setText(noStr);
            }
        }

        /**
         * 初始化界面控件
         */
        private void initView() {
            yes = (Button) findViewById(R.id.yes);
            no = (Button) findViewById(R.id.no);
            titleTv = (TextView) findViewById(R.id.title);
            messageTv = (TextView) findViewById(R.id.message);
        }

        /**
         * 从外界Activity为Dialog设置标题
         *
         * @param title
         */
        public void setTitle(String title) {
            titleStr = title;
        }

        /**
         * 从外界Activity为Dialog设置dialog的message
         *
         * @param message
         */
        public void setMessage(String message) {
            messageStr = message;
        }

        /**
         * 设置确定按钮和取消被点击的接口
         */
        public interface onYesOnclickListener {
            public void onYesClick();
        }

        public interface onNoOnclickListener {
            public void onNoClick();
        }
    }

    /**
     * 需要权限:android.permission.GET_TASKS
     *  判断应用程序是否在后台
     *
     * @param context
     * @return
     */
    public static boolean isApplicationBroughtToBackground(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (tasks != null && !tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;

            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断服务是否开启
     *
     * @return
     */
    public static boolean isServiceRunning(Context context, String ServiceName) {
        if (("").equals(ServiceName) || ServiceName == null)
            return false;
        ActivityManager myManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                .getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString()
                    .equals(ServiceName)) {
                return true;
            }
        }
        return false;
    }

}
