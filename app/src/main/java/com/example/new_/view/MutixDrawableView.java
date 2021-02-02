package com.example.new_.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MutixDrawableView extends View {

    public MutixDrawableView(Context context) {
        super(context);
    }

    public MutixDrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    MutixDrawable mutixDrawable = new MutixDrawable();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setRightDrawables(Drawable[] drawables){
        mutixDrawable.setDrawables(drawables);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mutixDrawable.draw(canvas);
    }








   // ======================== 自定义Drawable =========================


    /**
     * com.tencent.mobileqq.vas.gldrawable.DynamicDrawable
     * 130行以下的逻辑你可以直接用，是所有需要代理的方法
     * 172行以下的
     * todo:
     * setCallback到底是什么？？？
     */
    public static class MutixDrawable extends Drawable {

        //依据不同的布局模式，在draw时，初始化每个drawable的显示位置
        int layoutMode;

        Drawable[] drawables;

        public void setDrawables(Drawable[] drawables) {
            this.drawables = drawables;
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            for (Drawable drawable : drawables) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                //拿到每个Drawable的宽高，然后设置绘制bounds
//            if (layoutMode == "横向"){
//                //todo：依据父类的bounds的起始点
//                drawable.setBounds();
//            }else if (layoutMode == "纵向"){
//                drawable.setBounds();
//            }else {
//                //其他各类绘制方式
//            }

                //执行绘制
                drawable.draw(canvas);
            }
        }

        /**
         * 提供给宿主控件宽度，供其measure时计算
         */
        @Override
        public int getIntrinsicWidth() {
            return super.getIntrinsicWidth();
        }

        /**
         * 提供给宿主控件高度，供其measure时计算
         */
        @Override
        public int getIntrinsicHeight() {
            return super.getIntrinsicHeight();
        }

        @Override
        public void setAlpha(int alpha) {

        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return 0;
        }

    }

}
