package com.example.old.yamade.gl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {

    Triangle mTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //创建一个三角形
        mTriangle = new Triangle();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置绘制窗口大小
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        //绘制三角形
        mTriangle.draw();
    }


    /**
     * Vertex Shader - 渲染形状顶点
     * Fragment Shader - 渲染形状外观（颜色或纹理）
     * Program - 一个OpenGLES对象，包含了你想要用来绘制一个或多个形状的shader。
     */
    public static int loadShader(int type, String shaderCode){

        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

}