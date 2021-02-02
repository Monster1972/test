package com.example.old.anim;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.old.yamade.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        rotateGirl();
//        testAnim();

        ViewGroup view = findViewById(android.R.id.content);
        FrameLayout frameLayout = new FrameLayout(this);
        view.addView(frameLayout);
        FrameLayout frameLayout1 = new FrameLayout(this);
        view.addView(frameLayout1);
    }


    ImageView girlPic;

    private void rotateGirl() {

        girlPic = findViewById(R.id.beautiful_girl_pic);

        final float centerX = girlPic.getWidth() / 2.0f;
        final float centerY = girlPic.getHeight() / 2.0f;

        //括号内参数分别为（上下文，开始角度，结束角度，x轴中心点，y轴中心点，深度，是否扭曲）
        final Rotate3DAnimation rotation = new Rotate3DAnimation(this, 0, 360, centerX, centerY, 100f, false);
        rotation.setDuration(3000);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new LinearInterpolator());
        girlPic.startAnimation(rotation);
    }


    private void testAnim() {

//        TranslateAnimation translateAnimation=new TranslateAnimation(0,1,0,1);
        ObjectAnimator.ofInt(girlPic,"backgroundColor",1).start();

        BitmapDrawable drawable = new BitmapDrawable();
//        drawable.setFilterBitmap();
    }

    public static void startActivity(Context mContext){
        mContext.startActivity(new Intent(mContext,Main2Activity.class));
    }



}
