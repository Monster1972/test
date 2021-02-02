package com.example.old.yamade;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.Layout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.new_.activity.ViewActivity;
import com.example.new_.design.DesignActivity;
import com.example.new_.encryption.EncryptionActivity;
import com.example.old.anim.Main2Activity;
import com.example.old.yamade.aidl.MyService;
import com.example.old.yamade.aidl.ProcessManager;
import com.example.old.yamade.aidl.ProcessManager.Stub;
import com.example.old.yamade.aidl.ProcessUtil;
import com.example.old.yamade.test_opengl.OpenGlEsActivity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
 */

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        aidlTest();

        mContext=this;

        test1();

        testOpenGL();

        testPrice();

        testAnim();

        testService();

        testNewEntry();
    }

    private void testNewEntry() {
        findViewById(R.id.jiaoqing).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DesignActivity.class);
                startActivity(intent);
//                DesignActivity.startActivity(MainActivity.this);
            }
        });
    }

    private void testService() {
        List<String> test=null;
//        WeakReference<Activity> xx=new WeakReference<>()
        ArrayList<Object> objects = new ArrayList<>();
//      objects.addAll(test);

        findViewById(R.id.ouji).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGlEsActivity.startActivity(mContext);
            }
        });

        bindService(new Intent(this, MyService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },BIND_AUTO_CREATE);
    }

    Handler getGetmHandler= new Handler(){

    };

   Handler getmHandler= new Handler(){
       @Override
       public void handleMessage(@NonNull Message msg) {
           super.handleMessage(msg);
       }
   };

    private void aidlTest() {
        Log.d("进程名1`：", ProcessUtil.getCurProcessName(this));
        ServiceConnection conn= new ServiceConnection(){

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //3、获取服务端的Binder的代理对象
                ProcessManager processManager = Stub.asInterface(service);
                try {
                    Log.d("进程名2`：",processManager.getPid());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        /**
         * 2、客户端绑定并获取Binder
         */
        bindService(new Intent(this, MyService.class),conn,Context.BIND_AUTO_CREATE);

    }

    private void testAnim() {
        findViewById(R.id.anim).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Main2Activity.startActivity(mContext);
            }
        });
    }

    private void testPrice() {
        transformPrice(-100);
        transformPrice(2);
        transformPrice(20334);
        transformPrice(12131233);
        transformPrice(999988888);
        transformPrice(100000000);
        transformPrice(999999999);
    }

    /**
     * MD5:摘要算法。
     * 不可逆，验证信息的一致性（发送方身份）和完整性；
     *
     * AES:加密算法。
     * 可逆；密钥、填充、模式；加密轮数，有128、192、256
     *
     */
    public String transformPrice(int price) {

        StringBuilder builder = new StringBuilder();
        builder.append("¥");

        String unit = "";

        BigDecimal lastPrice = new BigDecimal(price);

        if (price >= 10000000) {     //十万级
            lastPrice = lastPrice.divide(new BigDecimal(1000000));
            unit = "万";
        } else if (price >= 10000000000l) {    //亿级
            lastPrice = lastPrice.divide(new BigDecimal(10000000000l));
            unit = "亿";
        } else {
            lastPrice = lastPrice.divide(new BigDecimal(100));
        }

        String result = new BigDecimal(lastPrice.toPlainString()).setScale(2, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        String s = builder.append(result).append(unit).toString();
        Log.e("金额测试：", s);
        return s;

    }

    private void testOpenGL() {

        findViewById(R.id.btn_jump).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewActivity.class));
//                startActivity(new Intent(MainActivity.this, EncryptionActivity.class));
            }
        });
    }


    private void test1() {
        Message message = mHandler.obtainMessage(100);

        mHandler.sendMessageDelayed(message, 2000);
        int a = 10;

        if (a == 1) {

        } else {

        }

        view = findViewById(R.id.tv_test);


        view.setTag("文件1");
        view.setTag(R.id.tv_test, "文件2");

        Drawable drawable1 = getResources().getDrawable(R.mipmap.qq_profilecard_pretty_gray);
        drawable1.setBounds(0,0,20,20);
        view.setCompoundDrawablesWithIntrinsicBounds(drawable1,null,null,null);
        Drawable drawable2 = getResources().getDrawable(R.mipmap.qq_profilecard_pretty_hight);
        drawable2.setBounds(25,20,45,20);
        view.setCompoundDrawablesWithIntrinsicBounds(drawable2,null,null,null);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                String test = "";
                if (i % 2 == 0) {
                    test = (String) view.getTag();
                } else {
                    test = (String) view.getTag(R.id.tv_test);
                }
                view.setText(test);
                view.setTag(null);
                view.getTag();

                Toast.makeText(MainActivity.this, "收到" + 1024, Toast.LENGTH_LONG).show();
            }
        });
    }


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                Toast.makeText(MainActivity.this, "收到1", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "收到2", Toast.LENGTH_LONG).show();
            }
        }
    };

    int i = 0;

    TextView view;


}
