package com.example.old.yamade.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import com.example.old.yamade.gl2.initBitmap;
import com.example.old.yamade.gl3.TY_Render1;

public class MyGLSurfaceView extends GLSurfaceView {

//    private final MyGLRenderer mRenderer;
    private final TY_Render1 mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
//        setEGLContextClientVersion(2);

        initBitmap.init(this.getResources());
        //        mRenderer = new MyGLRenderer();
        mRenderer = new TY_Render1();

        setRenderer(mRenderer);

    }
}