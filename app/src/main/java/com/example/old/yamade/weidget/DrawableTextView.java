package com.example.old.yamade.weidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.old.yamade.R;

public class DrawableTextView extends View {

    public DrawableTextView(Context context) {
        super(context);
        init();
    }



    public DrawableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public int dp2px(float dp) {
        if (dp == 0) {
            return 0;
        } else {
            return (int) (dp * getResources().getDisplayMetrics().density + 0.5f);
        }
    }


    Drawable drawable1;
    Drawable drawable2;

    Paint paint;

    private void init() {
        drawable1 = getResources().getDrawable(R.mipmap.qq_profilecard_pretty_gray);
        drawable2= getResources().getDrawable(R.mipmap.qq_profilecard_pretty_hight);

        paint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#A47E0D"));

        drawable1.setBounds(0, 0, dp2px(20), dp2px(20));

        drawable1.draw(canvas);

        drawable2.setBounds(dp2px(25),0,dp2px(45),dp2px(20));

        drawable2.draw(canvas);

    }
}
