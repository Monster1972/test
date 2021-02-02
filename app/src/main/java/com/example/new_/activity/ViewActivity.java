package com.example.new_.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.SurfaceControl.Transaction;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.new_.animal.GhostAnimal;
import com.example.new_.util.TLOG;
import com.example.old.yamade.R;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ViewActivity extends AppCompatActivity {

    private ImageView xfermodeImag;
    private ImageView xfermodeImagFuzhu;


    private static final int[][] ITEMS = new int[][]{
            {2001, 2, 3, 1},
            {2002, 2, 3, 2},
            {2003, 2, 3, 3}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        xfermodeImag = findViewById(R.id.xfer_mode1);
        xfermodeImagFuzhu = findViewById(R.id.xfer_mode2);

//        xfermodeImag.setImageResource(R.drawable.test_content);

//        xfermodeImag.setScaleType(ScaleType.CENTER_CROP);

        int oldId = ITEMS[0][0];

        TLOG.d("数组读取    1   ：", oldId +"");

        int id= oldId;

        TLOG.d("数组读取    2   ：", oldId +"");

//        Bitmap bitmap1 = makeSrc(200, 100);
        Bitmap bitmap2 = makeDst(200, 200);
//
        xfermodeImag.setImageBitmap(bitmap2);

        xfermodeImagFuzhu.setImageBitmap(bitmap2);
//
//        initDf();
//
//        for (Iterator iter = Df.iterator(); iter.hasNext();){
//            Log.d("正余",""+f((Float) iter.next()));
//        }
//
        GhostAnimal ghostAnimal = new GhostAnimal(this);
        ghostAnimal.setAnimalObj(xfermodeImag);
        ghostAnimal.launch(1000);

//        GhostAnimal ghostAnimal2 = new GhostAnimal(this);
//        ghostAnimal2.setAnimalObj(xfermodeImagFuzhu);
//        ghostAnimal2.launch(-1000);


      Map map=  new HashMap<Integer,Integer>(){
          {
              put(1,2);
          }
      };
        TLOG.d("数组读取    xx   ：", map.get(0) +"");
//
    }



    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF66AAFF);
        c.drawRect(0, 0, w, h, p);
        return bm;
    }


    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w, h), p);
        return bm;
    }

    public void canvasPic(){




    }

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



    Test.DecodeCallBack listener = null;

    public void testCallBack(GhostAnimal result){
        if (null == listener) {
            listener = new Test.DecodeCallBack() {
                @Override
                public void decodeResult(String result) {
                    TLOG.d("回调测试：", result);
                }
            };
        }
    }

    public static class Test{
        /**
         * 解码回调
         */
        public  interface DecodeCallBack {
            void decodeResult(String result);
        }

    }
}
