package com.example.old.yamade;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.old.yamade.gl.MyGLSurfaceView;

public class Main3Activity extends AppCompatActivity {

    MyGLSurfaceView glSurfaceView;

    /**
     *  基于物体上的原点空间、
     *  基于世界上的原点空间（各个物体相对摆放）、
     *  基于视觉上的观察空间、
     *  基于标准坐标系的视觉投影、
     *  裁剪坐标到屏幕坐标（传说中的视口变换，将glViewport函数内的范围值转为-1～1,多的不要）
     *
     *  ==> 局部--(模型矩阵,位移缩放旋转等)-->世界--(观察矩阵，位移和旋转的组合)-->观察--(投影矩阵，进行视窗变换，使之坐标标准化)-->裁剪
     *  V(clip)=M(projection)⋅M(view)⋅M(model)⋅V(local)
     *
     *
     *
     *
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ObjectAnimator.ofInt();
        getSharedPreferences(null,MODE_PRIVATE);

        TextView view=findViewById(R.id.tv_3);

        TextPaint paint = view.getPaint();
        float xx = Layout.getDesiredWidth("文件1221323", paint);

        Path path = new Path();


        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(paint.getTextSize());
        float yy = Layout.getDesiredWidth("文件1221323", textPaint);

        Log.d("Textsize ",paint.getTextSize()+" "+xx+" "+textPaint.getTextSize()+" "+yy);

//        glSurfaceView = new MyGLSurfaceView(this);
//        setContentView(glSurfaceView);
//
//        Drawable drawable = new Drawable() {
//
//            @Override
//            public void draw(@NonNull Canvas canvas) {
//
//            }
//
//            @Override
//            public void setAlpha(int alpha) {
//
//            }
//
//            @Override
//            public void setColorFilter(@Nullable ColorFilter colorFilter) {
//
//            }
//
//            @Override
//            public int getOpacity() {
//                return PixelFormat.TRANSLUCENT;
//            }
//        };
//
//
//        drawable.setCallback(new Callback() {
//            @Override
//            public void invalidateDrawable(@NonNull Drawable who) {
//
//            }
//
//            @Override
//            public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
//
//            }
//
//            @Override
//            public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
//
//            }
//        });

    }
}
