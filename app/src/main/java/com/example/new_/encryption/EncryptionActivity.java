package com.example.new_.encryption;

import android.os.Bundle;
import android.util.Base64;
import androidx.appcompat.app.AppCompatActivity;
import com.example.new_.util.TLOG;
import com.example.old.yamade.R;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionActivity extends AppCompatActivity {

    public final static String TAG = "Encryption_LOG >>>>>>> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        base64Test();

        md5Test();

//      fileTest();

    }


    /**
     * 基于LottieLoader：网络url，下载了数据文件（创建路径文件，并二进制写入）；拼接还原zip文件路径，创建File，执行ZipUtils.unZipFile
     *
     * 0、文件读写操作
     * 1、带文件格式的读写：https://developer.android.com/reference/java/io/DataInputStream.html
     * 2、压缩与解压：
     *
     */
    private void fileTest() {

        // assets文件加密

        String test1 = ShaderUtil.loadFromAssetsFile21("test.json", getResources());
        String test2 = ShaderUtil.loadFromAssetsFile22();
        TLOG.d(TAG, "文件加密：" + test1);
        TLOG.d(TAG, "文件加密：" + test2);

        // assets文件解密


    }



    private void md5Test() {

        // Q1：哈希的实现原理

        String s1 = "ABCD";

        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(s1.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        // 一般转换出32位16进制
        String md5code = new BigInteger(1, secretBytes).toString(16);
        // 不足32位则高位补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        TLOG.d(TAG + "MD5,encode：", new String(md5code));
    }

    /**
     * 核心实现：
     * Base64的process接口。
     * output[op++] = alphabet[(v >> 18) & 0x3f]
     *
     */
    private void base64Test() {

        // Q1：编码的参数代表的意思
        // Q2：编码的实现原理

//        String s1 = "姓名";
//        String s2 = "汤姆斯特";
        String s1 = "AB";
        String s2 = "CD";

        // 编码内容，编码模式
        byte[] encode = Base64.encode((s1 + s2).getBytes(), Base64.DEFAULT);
        TLOG.d(TAG + "Base64,encode：", new String(encode));
        byte[] decode = Base64.decode(encode, Base64.DEFAULT);
        TLOG.d(TAG + "Base64,decode：", new String(decode));

    }





}