package com.example.new_.encryption;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Environment;
import com.example.new_.util.TLOG;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ShaderUtil {


    /**
     * 获得文件编码
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String codeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        bin.close();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }

        return code;
    }


    public static String loadFromAssetsFile(String fname, Resources r) {
        String result = null;
        try {
            InputStream in = r.getAssets().open(fname);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((ch = in.read()) != -1) {
                baos.write(ch);
            }
            byte[] buff = baos.toByteArray();
            baos.close();
            in.close();
            if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
                result = new String(buff, StandardCharsets.UTF_8);
            }
            result = result.replaceAll("\\r\\n", "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void copyFileFromAssets(Context context, String oldPath, String newPath) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = context.getAssets().open(oldPath);
            fos = new FileOutputStream(new File(newPath));
            byte[] buffer = new byte[1024];
            int byteCount = 0;
            while ((byteCount = is.read(buffer)) != -1) { //循环从输入流读取 buffer字节
                fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
            }
            fos.flush();//刷新缓冲区
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int[] head = {1, 2, 3, 4};



    public static String loadFromAssetsFile2(String fname, Resources r) {
        String result = null;
        try {
            InputStream in = r.getAssets().open(fname);
            int ch = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((ch = in.read()) != -1) {
                baos.write(ch+1);
            }
            byte[] buff = baos.toByteArray();
            baos.close();
            in.close();
            if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
                result = new String(buff, StandardCharsets.UTF_8);
            }
            result = result.replaceAll("\\r\\n", "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    static String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();




    public static String loadFromAssetsFile21(String fname, Resources r) {
        String result = null;
//        File file = new File(SDCARD_ROOT + "/Tencen/Mobile/test.xa");
//        if (!file.exists()){
//            file.mkdirs();
//        }
//        try {
//            InputStream in = r.getAssets().open(file.getAbsolutePath());
//            int ch = 0;
//            FileOutputStream baos = new FileOutputStream(file);
//            while ((ch = .read()) != -1) {
//                baos.write(ch+1);
//            }
//            baos.close();
//            in.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            TLOG.d(EncryptionActivity.TAG, "写入异常："+e.getMessage()+" ; "+e.getCause());
//        }
        return result;
    }

    public static String loadFromAssetsFile22() {

        //建立链接
        FileInputStream fileInputStream = null;
        StringBuffer sBuffer = new StringBuffer();

        try {
            fileInputStream = new FileInputStream(new File(SDCARD_ROOT+"/Tencen/Mobile/test.xa"));


        int n = 0;


        while (n != -1) { //当n不等于-1,则代表未到末尾

            n = fileInputStream.read();//读取文件的一个字节(8个二进制位),并将其由二进制转成十进制的整数返回

            char by = (char) n; //转成字符

            sBuffer.append(by);

        }
        } catch (Exception e) {
            e.printStackTrace();
            TLOG.d(EncryptionActivity.TAG, "读出异常："+e.getMessage()+" ; "+e.getCause());
        }
        return sBuffer.toString();
    }

}