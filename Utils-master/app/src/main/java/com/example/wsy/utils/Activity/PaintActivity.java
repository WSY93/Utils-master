package com.example.wsy.utils.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wsy.utils.R;
import com.example.wsy.utils.Utils.DialogListener;
import com.example.wsy.utils.Utils.HttpClientUtils;
import com.example.wsy.utils.SelfDefined.WritePadDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 生成签名文件并上传
 *    注意：网络权限
 *  Created by wsy on 2017/10/31
 */

public class PaintActivity extends AppCompatActivity {
    private Bitmap mSignBitmap;
    private String signPath;
    private ImageView ivSign;
    private TextView tvSign;
    private Button up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        setTitle("欢迎使用手写签名");
        ivSign = (ImageView) findViewById(R.id.iv_sign);
        tvSign = (TextView) findViewById(R.id.tv_sign);
        up = (Button) findViewById(R.id.up);
        ivSign.setOnClickListener(signListener);
        tvSign.setOnClickListener(signListener);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开启一个子线程，进行网络操作，等待有返回结果，使用handler通知UI
//                new Thread(networkTask).start();
                // 上传文件 POST 同步方法
                try {
                    Map<String,String> uploadParams = new LinkedHashMap<String, String>();
                    uploadParams.put("userImageContentType", "image");
                    uploadParams.put("userImageFileName", "testaa.png");
                    HttpClientUtils.getInstance().uploadFileImpl(
                            "http://180.104.102.62:7430/Images/SignImages", signPath,
                            "userImage", uploadParams);
                } catch (Exception e) {
                    e.printStackTrace();


            }
        }
        });
    }
    private View.OnClickListener signListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            WritePadDialog writeTabletDialog = new WritePadDialog(
                    PaintActivity.this, new DialogListener() {
                @Override
                public void refreshActivity(Object object) {

                    mSignBitmap = (Bitmap) object;
                    signPath = createFile();
                    ivSign.setImageBitmap(mSignBitmap);
                    tvSign.setVisibility(View.GONE);
                }
            });
            writeTabletDialog.show();
        }
    };

    /**
     * 创建手写签名文件
     *
     * @return
     */
    private String createFile() {
        ByteArrayOutputStream baos = null;
        String _path = null;
        try {
            String sign_dir = Environment.getExternalStorageDirectory() + File.separator;
            _path = sign_dir + System.currentTimeMillis() + ".jpg";
            baos = new ByteArrayOutputStream();
            mSignBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] photoBytes = baos.toByteArray();
            if (photoBytes != null) {
                new FileOutputStream(new File(_path)).write(photoBytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return _path;
    }

    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            // 在这里进行 http request.网络请求相关操作
//            UploadUtil.uploadFile(new File(signPath),"http://192.168.1.78:8080/Upload/UploadShipServlet");


        }
    };

}
