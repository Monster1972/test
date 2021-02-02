package com.example.old.anim;

import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;

public class DisplayUtil {

    /**
     *
     * 一、过去【做的事 + 遇到的难点】
     *
     * 1>单测
     * 使用PowerMock时，初始化Mock对象时，经常会遇到classCastException；
     * java类的生命周期 = 加载 + 链接（验证+准备+解析）+ 初始化 + 使用 + 卸载
     * 原因，声明的类在加载Dex时，进行解析时使用的对应的的Loader。但是Mock的创建流程中，（双亲委派，父类优先）却使用的父类的ClassLoader。
     * 解决方法：Ignore忽略了该类，使声明类不使用自定义Loader，采用系统的。二者保持一致；
     *
     * 附加：出现此异常，比较经典的两种场景：
     * 一种是声明和创建的ClassLoader不一致；
     * 还有一种是实现的接口不在同一个dex，其本质也是因为classLoader不一致；因为插件的classLoader和主Dex的classLoader不一样；
     *
     * 2>QQ提醒，谈适配，两种适配方式；
     *
     * px = dp * density;   //依据设计图修改density；这个代表密度值，1dp有多少个像素；通过 这个 * dp 得到偏移像素；
     * 通过获取DisplayMetrics对象去赋值更改density。
     *
     * //脚本生成
     * smallestWidth,生成一系列values-xx单位文件夹，系统向下匹配文件
     * 文件夹中则以【1-360,对应dp】，映射出360组自定义单位；
     *
     *
     * 3>
     * 小铭牌，谈协议，
     *
     * 0x5eb，处理个人资料相关的协议；
     * 用户弱网或者断网情况，发送端的消息刷新前是构造的本地消息；
     * 故需要通过此协议来对本地当前账户进行大会员挂件信息的同步，一个是push方式，还有一个是登录鉴权方式；
     *
     * App中维护BusinessHandler数组【内存缓存】==》维护了各类Handler；Handler == 相关模块业务类
     *
     * 4>我的购物，谈购物；
     *
     * 视图：RecyclerView的Type、动态化LayoutParams的高度、属性动画ValueAnimator；
     * 请求：VasExtensionHandler处理发送和接受。基于cmd的一个mObserverMap<cmd,observer>；发送时增加，接收时移除；
     *
     * 5>背景逻辑修改；
     *
     * IPC的通信原理，Messenger信使--互相传递给对方handler，使之进行消息发送；
     *
     * 6>资料卡组件化，谈架构；
     *
     * 区域以容器划分，视图以组件展现；工厂生产；生命周期；
     *
     * bug1：diy群资料卡中两个icon未适配；
     * bug2：diy资料卡的等级未展示；
     *
     *
     * 二、现在【成长 + 挑战】
     *
     * //成长
     *
     * 量化的方式还不行；
     *
     * 技术栈，还没有明显的增加；
     *
     * Max进步 = 业务熟悉 && 工具使用；
     * 和一群优秀的人学习到了一些好的思考方式以及学习方式和作息方式；
     *
     * //挑战
     *
     * 更多的存量业务需要梳理，这是一个长期的过程； //扫盲和拓新
     * 时间管理，如何使业务开发和自身成长形成良性循环； //
     *
     *
     * 三、未来【目标 + 展望】
     * Flutter，接触过。希望自己能更加深入的扩展这方面，有一些展示性成果；
     * 视觉动效，也在接触OpenGl，想和大家一起参与这个有意义的活动；
     *
     *
     */
    public static float getScreenRelatedInformation(Context context) {
        int widthPixels=0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            widthPixels= outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            Log.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
                    ",densityDpi = " + densityDpi + "\n" +
                    ",density = " + density + ",scaledDensity = " + scaledDensity);
        }
        return widthPixels;
    }

    @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR1)
    public static void getRealScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            Log.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
                    ",densityDpi = " + densityDpi + "\n" +
                    ",density = " + density + ",scaledDensity = " + scaledDensity);
        }
    }
}