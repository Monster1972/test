package com.example.new_.animal;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.dynamicanimation.animation.SpringAnimation;
import java.util.TreeSet;

/**
 * 功能分解：为Object提供动画元素
 *
 * 输入：
 *
 * 输出：
 *
 * 接口：
 * 初始化、启动、暂停、继续、停止、回收
 *
 */
public class GhostAnimal {

    // 透明度：动画过程随缩放进行心跳；
    // 缩放：产生心跳的感觉；
    // 平移：定义一个Rect缓慢移动区域；

    View mAnimalObj;
    int width;
    int height;


    public static int SCREEN_WIDTH = 320;
    public static int SCREEN_HIGHT = 480;


    public GhostAnimal(Context context){
        initDf();
        initScreenParams(context);
    }

    private void initScreenParams(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (display != null) {
            SCREEN_WIDTH = display.getWidth();
            SCREEN_HIGHT = display.getHeight();
        }
    }

    public void setAnimalObj(View mAnimalObj) {
        this.mAnimalObj = mAnimalObj;
    }

    public void launch(int startSpeed){
        buildAnimalParams();

//        buildAnimalParams2(90, startSpeed, 1000, 2);

    }

    public void buildAnimalParams2(int angle, int instance, int duration, int actionMode) {

        SpringAnimation animation;

        double hudu = (2 * Math.PI / 360) * angle;

        double instanceX = instance * Math.cos(hudu);
        double instanceY = instance * Math.sin(hudu);

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animator21 = ObjectAnimator.ofFloat(mAnimalObj, "translationX", 0f, (float) instanceX);
//        animator21.setRepeatMode(ValueAnimator.REVERSE);
//        animator21.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator animator22 = ObjectAnimator.ofFloat(mAnimalObj, "translationY", 0f, (float) instanceY);
//        animator22.setRepeatMode(ValueAnimator.REVERSE);
//        animator22.setRepeatCount(ValueAnimator.INFINITE);

        // 这里也需要加一个缓动差值器
        animator21.setDuration(duration);
        animator22.setDuration(duration);

        switch (actionMode){
            case 1: // 弹簧效果
                  animatorSet.setInterpolator(new BounceInterpolator());
                break;
            case 2: // 先快后慢
                animatorSet.setInterpolator(new DecelerateInterpolator());
                break;
            case 3:

                break;

        }

        animatorSet.playTogether(animator21,animator22);

        animatorSet.start();

    }

    public void buildAnimalParams(){

        ValueAnimator animator = ValueAnimator.ofObject(new GhostEvaluator(), new PointF(200, 200), new PointF(500, 500));
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setDuration(10000);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF)animation.getAnimatedValue();
                mAnimalObj.setX(pointF.x);
                mAnimalObj.setY(pointF.y);
            }
        });
        animator.start();


        if (true){
            return;
        }

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mAnimalObj, "alpha", 0f, 1f);
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.setInterpolator(new YouLixx());
        animator1.setDuration(8000);

        ObjectAnimator animator21 = ObjectAnimator.ofFloat(mAnimalObj, "translationY", 0f, 500f);
        animator21.setRepeatMode(ValueAnimator.REVERSE);
        animator21.setRepeatCount(ValueAnimator.INFINITE);
        animator21.setInterpolator(new CubicBezierInterpolator(0,0,100,100));
        // 这里也需要加一个缓动差值器
        animator21.setDuration(10000);

        animator21.start();


//        ObjectAnimator animator22 = ObjectAnimator.ofFloat(mAnimalObj, "translationY", 0f, 100f);
//        animator22.setRepeatMode(ValueAnimator.REVERSE);
//        animator22.setRepeatCount(ValueAnimator.INFINITE);
//        ObjectAnimator animator31 = ObjectAnimator.ofFloat(mAnimalObj, "scaleX", 0.8f, 1f);
//        animator31.setRepeatMode(ValueAnimator.REVERSE);
//        animator31.setRepeatCount(ValueAnimator.INFINITE);
//        animator31.setDuration(8000);
//        animator31.setInterpolator(new YouLixx());
//        ObjectAnimator animator32 = ObjectAnimator.ofFloat(mAnimalObj, "scaleY", 0.8f, 1f);
//        animator32.setRepeatMode(ValueAnimator.REVERSE);
//        animator32.setRepeatCount(ValueAnimator.INFINITE);
//        animator32.setDuration(8000);
//        animator32.setInterpolator(new YouLixx());

//        animatorSet.playTogether(animator1,animator21,animator31,animator32);
//        animatorSet.playTogether(animator21);
//
//        animatorSet.start();

//        animator1.addUpdateListener(new AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float animatedValue = (float) animation.getAnimatedValue();
//                if (animatedValue < 0 ) {
////                    Rect rect=new Rect(50,50,250,250);
//                    double youLiParams = Math.random();
//                    mAnimalObj.setX((float) (youLiParams*SCREEN_WIDTH));
//                    mAnimalObj.setY((float) (youLiParams*SCREEN_HIGHT));
////                    if (mAnimalObj.getX() < 50) {
////                        mAnimalObj.setX(50);
////                    } else if (mAnimalObj.getX() > 250) {
////                        mAnimalObj.setX(250);
////                    }
////                    if (mAnimalObj.getY() < 50) {
////                        mAnimalObj.setY(50);
////                    } else if (mAnimalObj.getY() > 250) {
////                        mAnimalObj.setY(250);
////                    }
////                    lastYouLiReset = true;
//                    TLOG.d("差值器-透明666   ",youLiParams+"   "+ (float) (youLiParams*width)+"  "+animatedValue+"   "+(float) (youLiParams*height));
//                }
////                else {
////                    if (animatedValue< - 0.5) {
////                        lastYouLiReset = false;
////                    }
////                }
//                TLOG.d("差值器-透明888   ",""+mAnimalObj.getX()+"    "+mAnimalObj.getY());
//            }
//        });

//        animator21.addUpdateListener(new AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float animatedValue = (float) animation.getAnimatedValue();
//                TLOG.d("差值器-平移",animatedValue+"");
//            }
//        });



//        animator31.addUpdateListener(new AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float animatedValue = (float) animation.getAnimatedValue();
//                TLOG.d("差值器-缩放",animatedValue+"");
//            }
//        });



        //差值器
//        ValueAnimator animator = new ValueAnimator();
//
//        animator.setDuration(10000);
//        animator.setObjectValues(new YouLi());
//
//
//
//        final YouLi youLi = new YouLi();
//
//
//        animator.setEvaluator(new TypeEvaluator() {
//            @Override
//            public Object evaluate(float fraction, Object startValue, Object endValue) {
//                // 两边之和大于第三边；
//                // 两边之差小于底三边
//                TLOG.d("差值器",fraction+"");
//
//                youLi.alpha = Math.min(1, fraction);
//
//                sCount++;
//
//                return youLi;
//            }
//        });
//
//        animator.addUpdateListener(new AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                YouLi youLi = (YouLi) animation.getAnimatedValue();
//                mAnimalObj.setAlpha(youLi.alpha);
//
//                    mAnimalObj.setScaleX(1 - youLi.alpha);
//                    mAnimalObj.setScaleY(1 - youLi.alpha);
//
//            }
//        });
//        animator.start();
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
////        animator.setInterpolator(new EaseCubicInterpolator(0.31f, 0.85f,0.77f, 0.14f));
//        animator.setInterpolator(new YouLixx());
//

    }


//    public static class YouLixx implements Interpolator{
//
//        @Override
//        public float getInterpolation(float input) {
//          double  cycles = 1;
//          return (float) Math.sin(2 * cycles * Math.PI * input);
//        }
//    }

    public static class YouLixx implements Interpolator{

        @Override
        public float getInterpolation(float input) {
            double  cycles = 1;
            return (float) Math.sin(2 * cycles * Math.PI * input);
        }
    }


    //========================= 华丽的分割线 ====================


    static int sCount=0;

    public static class YouLi{

        // 透明度
        float alpha=0;


    }


    // 类似于一个心跳包（透明、缩放）

    private TreeSet<Float> Df = new TreeSet<>();//定义域

    /**
     * 初始化定义域
     */
    private void initDf() {
        for (float i =-360; i <= 450; i++) {
            Df.add(i);//初始化定义域
        }
    }


    /**
     * 对应法则
     *
     * @param x 原像(自变量)
     * @return 像(因变量)
     */
    private float f(Float x) {
        float y= (float) (Math.sin(Math.PI/180*x));
        return y;
    }


    /**
     * 弹簧变速器
     */
    class YouLiInterpolator implements Interpolator {

        @Override
        public float getInterpolation(float t) { //定义我们的函数，t: 0 ~ 1
            if (t < 0.2094) {
                return (float) (-34 * (t - 0.18) * (t - 0.18) + 1.08);
            } else if (t < 0.404) {
                return (float) (5.9 * (t - 0.34) * (t - 0.34) + 0.95);
            } else if (t < 0.6045) {
                return (float) (-3 * (t - 0.53) * (t - 0.53) + 1.02);
            } else if (t < 0.8064) {
                return (float) ((t - 0.72) * (t - 0.72) + 0.99);
            } else {
                return (float) (-0.3 * (t - 0.915) * (t - 0.915) + 1.001);
            }
        }

    }

    /**
     * 缓动三次方曲线插值器.(基于三次方贝塞尔曲线)
     */
    public static class EaseCubicInterpolator implements Interpolator {

        private final static int ACCURACY = 4096;

        private int mLastI = 0;

        private final PointF mControlPoint1 = new PointF();

        private final PointF mControlPoint2 = new PointF();


        /**
         * 设置中间两个控制点
         *
         * 在线工具: http://cubic-bezier.com
         *
         * @param x1
         * @param y1
         * @param x2
         * @param y2
         */
        public EaseCubicInterpolator(float x1, float y1, float x2, float y2) {

            mControlPoint1.x = x1;

            mControlPoint1.y = y1;

            mControlPoint2.x = x2;

            mControlPoint2.y = y2;

        }


        @Override

        public float getInterpolation(float input) {

            float t = input;
            // 近似求解t的值[0,1]
            for (int i = mLastI; i < ACCURACY; i++) {

                t = 1.0f * i / ACCURACY;

                double x = cubicCurves(t, 0, mControlPoint1.x, mControlPoint2.x, 1);

                if (x >= input) {

                    mLastI = i;

                    break;

                }

            }

            double value = cubicCurves(t, 0, mControlPoint1.y, mControlPoint2.y, 1);

            if (value > 0.999d) {

                value = 1;

                mLastI = 0;

            }

            return (float) value;

        }


        /**
         * 求三次贝塞尔曲线(四个控制点)一个点某个维度的值.<br>
         * <p>
         * 参考资料: <em> http://devmag.org.za/2011/04/05/bzier-curves-a-tutorial/ </em>
         *
         * @param t      取值[0, 1]
         * @param value0
         * @param value1
         * @param value2
         * @param value3
         * @return
         */
        public static double cubicCurves(double t, double value0, double value1,

                double value2, double value3) {

            double value;

            double u = 1 - t;

            double tt = t * t;

            double uu = u * u;

            double uuu = uu * u;

            double ttt = tt * t;


            value = uuu * value0;

            value += 3 * uu * t * value1;

            value += 3 * u * tt * value2;

            value += ttt * value3;

            return value;

        }

    }

    /**
     * 自定义估值器
     * 核心：如何处理游离的"速率"和"轨迹"
     * 附加：缩放、透明度、旋转，这些效果可以进行结合
     */
    public static class GhostEvaluator implements TypeEvaluator<PointF> {

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {

            // 三阶贝塞尔公式： B(t) = P0 * (1-t)^3 + 3 * P1 * t * (1-t)^2 + 3 * P2 * t^2 * (1-t) + P3 * t^3, t ∈ [0,1]

            final float t = fraction;
            float oneMinusT = 1.0f - t;
            PointF point = new PointF();

            // 缓动起始点
            PointF point0 = (PointF)startValue;

            // 第一个锚点
            PointF point1 = new PointF();
            point1.set(500, 0);

            // 第二个锚点
            PointF point2 = new PointF();
            point2.set(0, 500);

            // 缓动结束点
            PointF point3 = (PointF)endValue;

            point.x = oneMinusT * oneMinusT * oneMinusT * (point0.x)
                    + 3 * oneMinusT * oneMinusT * t * (point1.x)
                    + 3 * oneMinusT * t * t * (point2.x)
                    + t * t * t * (point3.x);

            point.y = oneMinusT * oneMinusT * oneMinusT * (point0.y)
                    + 3 * oneMinusT * oneMinusT * t * (point1.y)
                    + 3 * oneMinusT * t * t * (point2.y)
                    + t * t * t * (point3.y);
            return point;
        }
    }

    public class CubicBezierInterpolator implements Interpolator {
        private int mLastI = 0;
        private static final float STEP_SIZE = 1.0f / 4096;
        private final PointF point1 = new PointF();
        private final PointF point2 = new PointF();

        public CubicBezierInterpolator(float x1, float y1, float x2, float y2) {
            point1.x = x1;
            point1.y = y1;
            point2.x = x2;
            point2.y = y2;
        }

        @Override
        public float getInterpolation(float input) {
            float t = input;
            //如果重新开始要重置缓存的i。
            if (input == 0) {
                mLastI = 0;
            }
            // 近似求解t
            double tempX;
            for (int i = mLastI; i < 4096; i++) {
                t = i * STEP_SIZE;
                tempX = cubicEquation(t, point1.x, point2.x);
                if (tempX >= input) {
                    mLastI = i;
                    break;
                }
            }
            double value = cubicEquation(t, point1.y, point2.y);

            //如果结束要重置缓存的i。
            if (input == 1) {
                mLastI = 0;
            }
            return (float) value;
        }

        public double cubicEquation(double t, double p1, double p2) {
            double u = 1 - t;
            double tt = t * t;
            double uu = u * u;
            double ttt = tt * t;
            return 3 * uu * t * p1 + 3 * u * tt * p2 + ttt;
        }
    }



}
