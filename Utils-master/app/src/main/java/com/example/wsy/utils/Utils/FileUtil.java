package com.example.wsy.utils.Utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by wsy on 2017/8/17.
 *  文件操作工具类
 */

public class FileUtil {

    /**
     *  对/data/data/youPackageName/files下的文件进行写入操作,直接传入文件名即可
     *   对于openFileOutput()方法的第二个参数:
     *       1.MODE_PRIVATE:默认操作模式，代表该文件私有。写入的内容会覆盖原文件之前的内容
     *       2.MODE_APPEND:该模式下会检查文件是否存在，文件存在则在文件内容末尾开始追加内容，不存在则创建文件并写入
     *       3.MODE_WORLD_READABLE:表示该文件可以被其他应用读取
     *       4.MODE_WORLD_WRITEABLE：表示该文件可以被其他应用写入
     */

    public static boolean  writeToDataAppFile(Context context,String fileName,String content) {
        try{
            FileOutputStream fout = context.openFileOutput(fileName, MODE_PRIVATE);
            byte [] bytes = content.getBytes();
            fout.write(bytes);
            fout.close();//及时关闭

            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 对/data/data/youPackageName/files下的文件进行读取操作,直接传入文件名即可
     *  返回值为读取的文件内容
     */

    public static String readFromDataAppFile(Context context,String fileName) {
        FileInputStream inStream = null;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();//输出到内存
        int len = 0;
        String content = "";
        byte[] buffer = new byte[1024];
        if (dataAppFileIsExists(fileName,context)) {
            try {
                inStream = context.openFileInput(fileName);
                while ((len = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] content_byte = outStream.toByteArray();
            //关闭流
            try {
                inStream.close();
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            content = new String(content_byte);

        }else {

            Toast.makeText(context,"文件不存在",Toast.LENGTH_SHORT).show();

        }
        return content;
    }

    /**
     *   删除/data/data/youPackageName/files 下的某文件
     *
     * @param context
     * @param fileName 要删除的文件名
     * @return 删除成功返回true  否则返回false
     */
    public static boolean deleteDataAppFile(Context context,String fileName){
        if (dataAppFileIsExists(fileName,context)){
            context.deleteFile(fileName);
            return true;
        }else {
            return false;
        }

    }





    /**
     * 判断/data/data/youPackageName/files文件是否存在
     * @param fileName
     * @param context
     * @return
     */
    public static boolean dataAppFileIsExists(String fileName,Context context)
    {
        String filePath = context.getFilesDir()+"/"+fileName;
        try
        {
            File file=new File(filePath);
            if(!file.exists())
            {
                return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }





    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     *
     * @return
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡根目录路径
     *
     * @return
     */
    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            sdpath = "不适用";
        }
        return sdpath;

    }

    /**
     *   判断SD卡目录下某文件是否存在
     * @param context
     * @param fileName   文件名字
     * @return
     */

    public static boolean sdCardFileIsExists(Context context,String fileName){
        String filePath = getSdCardPath()+"/"+fileName;
        try
        {
            File file=new File(filePath);
            if(!file.exists())
            {
                return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     *  SD卡文件写入
     *
     *   写入方式： 追加--FileOutputStream（）要有两个参数，第二个参数设置为true
     *              覆盖--FileOutputStream（）一个参数即可
     *
     * @param fileName 文件名
     * @param content
     */
    public static boolean writeToSdCardFile(String fileName,String content){
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                   fileName);
            FileOutputStream fos = new FileOutputStream(file,true);//文件末尾追加写入
            fos.write(content.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    /**
     *  读取SD卡文件内容
     * @param fileName
     * @return  返回读取到的文件内容
     */

    public static String readFromSdCardFile(String fileName,Context context){
        String content = "";
        if (sdCardFileIsExists(context,fileName)) {
            try {
                File file = new File(Environment.getExternalStorageDirectory(),
                        fileName);
                FileInputStream is = new FileInputStream(file);
                byte[] b = new byte[is.available()];
                is.read(b);
                content = new String(b);
                System.out.println("读取成功：" + content);
                is.close();//关闭
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            content = "文件不存在";
        }
        return content;

    }

    /**
     * 删除SD卡上的某文件
     *
     * @param fileName  文件名
     * @return 返回值  true 表示删除成功  否则失败
     */

    public static boolean deleteSdCardFile(String fileName){
        File file = new File(Environment.getExternalStorageDirectory(),fileName);
        if (file.exists()){
            file.delete();
            return true;
        }else {
            return false;
        }
    }

}
