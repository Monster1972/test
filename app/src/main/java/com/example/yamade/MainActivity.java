package com.example.yamade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * as 快捷键
 *
 * 查找1，当前页搜索 【command f】
 * 查找2，类文件查找 【双击 shift】
 * 查找3，路径字段全局查找【command shift f】
 * 生成代码，【alt+insert == command + n】
 * 复制当前，【ctrl d == command d】
 * 现实参数，【ctrl p == command p】
 * 快速修复，【alt+enter == option + enter】
 * 格式化代码，【ctrl+alt+l == command+option+l】
 * 查看类层级结构，【ctrl+h】
 * 查看方法所属结构，【command+shift+h】
 * 查看调用层级结构，【ctrl+option+h】
 * 错误凸显，f2
 * 重命名，shift+f6
 * 前进、后退，option + command + 左、右；
 *
 * mac 快捷键
 *
 * 拖动，删除移动
 * option + 拖动，复制移动
 * command + d，全量复制选择内容
 * Command+Shift+. 可以显示隐藏文件、文件夹，再按一次，恢复隐藏
 *
 * command + shift + 4 ； 快速截图
 *
 */

public class MainActivity extends AppCompatActivity {

    Handler mHandler=new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                Toast.makeText(MainActivity.this,"收到1",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(MainActivity.this,"收到2",Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Message message = mHandler.obtainMessage(100);

        mHandler.sendMessageDelayed(message,2000);

        Toast.makeText(MainActivity.this,"收到0",Toast.LENGTH_LONG).show();
    }
}
