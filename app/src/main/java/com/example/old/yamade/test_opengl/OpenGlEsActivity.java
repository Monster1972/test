package com.example.old.yamade.test_opengl;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGlEsActivity extends AppCompatActivity {

    /**
     * GlSurfaceView    //渲染容器
     * GlSurfaceView.Renderer   //渲染引擎
     *
     * 如何创建渲染容器？
     *
     * 如何初始化窗口？
     *
     * 如何创建图形？
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //添加Gl视图
        setContentView(new PicGlSurfaceView(this));
    }

    /**
     * 1、创建渲染容器
     */
    public class PicGlSurfaceView extends GLSurfaceView{

        public PicGlSurfaceView(Context context) {
            super(context);
            //1.1、设置Gl环境
            setEGLContextClientVersion(2);
            //1.2、设置渲染引擎
            setRenderer(new PicRender());
        }
    }

    /**
     * 2、创建渲染引擎
     */
    public class PicRender implements GLSurfaceView.Renderer{

        Triangle mTriangle;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //2.2、设置底色
            GLES20.glClearColor(0.0f, 0.0f, 0.8f, 1.0f);
            //【创建图形】
            mTriangle = new Triangle();
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //2.1、设置窗体大小
            GLES20.glViewport(0,0,width,height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            //2.3、清除屏幕缓冲颜色，露出底色
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
            //【绘制图形】
            mTriangle.draw();
        }
    }

    public static void startActivity(Context mContext){
        mContext.startActivity(new Intent(mContext,OpenGlEsActivity.class));
    }

    /**
     * 3、创建渲染图形
     */
    public class Triangle{

        //3.1.1、创建坐标，并处理坐标「坐标范围：-1～1，且以左下角为原点」。
        float[] mGraphPoint = new float[]{
                0f, 0.5f, 0f,
                -0.5f, 0f, 0f,
                0.5f, 0f, 0f,
        };

        //3.1.2、创建顶点着色器，处理顶点的位置和色值
        private final String vertexShaderCode =
                "attribute vec4 vPosition;" +
                        "void main() {" +
                        "  gl_Position = vPosition;" +
                        "}";

        //3.1.3、创建片段着色器，处理图形片段的形状和色值
        private final String fragmentShaderCode =
                "precision mediump float;" +
                        "uniform vec4 vColor;" +
                        "void main() {" +
                        "  gl_FragColor = vColor;" +
                        "}";



        int mProgram;   //ES程序
        private FloatBuffer vertexBuffer;

        public Triangle() {

            //【疑点1：顶点排序】

            //3.2.1、初始化缓冲字节长度（1个float占4个字节）
            ByteBuffer bb = ByteBuffer.allocateDirect(mGraphPoint.length * 4);
            //3.2.2、定义顶点排序方式
            bb.order(ByteOrder.nativeOrder());

            //【疑点2：设置读取的起始坐标】

            //3.3、创建浮点缓冲区，添加坐标数据，读取起始坐标
            vertexBuffer = bb.asFloatBuffer();
            vertexBuffer.put(mGraphPoint);
            vertexBuffer.position(0);

            //【疑点3：添加着色器 = 加载着色器 + 创建ES程序 + 程序绑定着色器 + 链接ES程序】

            //3.4、添加着色器
            int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                    vertexShaderCode);
            int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                    fragmentShaderCode);

            // 创建空的OpenGL ES程序
            mProgram = GLES20.glCreateProgram();

            // 添加顶点着色器到程序中
            GLES20.glAttachShader(mProgram, vertexShader);

            // 添加片段着色器到程序中
            GLES20.glAttachShader(mProgram, fragmentShader);

            // 创建OpenGL ES程序可执行文件
            GLES20.glLinkProgram(mProgram);
        }


        static final int COORDS_PER_VERTEX = 3;

        private int mColorHandle;
        private int mPositionHandle;

        private final int vertexCount = mGraphPoint.length / COORDS_PER_VERTEX;
        private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

        float color[] = { 255, 0, 0, 1.0f };

        /**
         *
         * 3.5、绘制三角形
         *
         * 一个字，draw
         * 读取P属性，进行位置顶点初始化
         * 读取C属性，进行颜色初始化
         *
         *
         *
         *
         */
        public void draw(){

            // 将程序添加到OpenGL ES环境
            GLES20.glUseProgram(mProgram);

            // 获取顶点着色器的位置的句柄
            mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

            // 启用三角形顶点位置的句柄
            GLES20.glEnableVertexAttribArray(mPositionHandle);

            // 准备三角形坐标数据
            GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                    GLES20.GL_FLOAT, false,
                    vertexStride, vertexBuffer);

            // 获取片段着色器的颜色的句柄
            mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

            // 设置绘制三角形的颜色
            GLES20.glUniform4fv(mColorHandle, 1, color, 0);

            // 绘制三角形
            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

            // 禁用顶点数组
            GLES20.glDisableVertexAttribArray(mPositionHandle);

        }

    }

    /**
     * 加载着色器(着色器代码执行代价高，避免频繁调用)
     * @param type
     * @param shaderCode
     */
    public static int loadShader(int type,String shaderCode){
        //1、创建着色器
        int shader = GLES20.glCreateShader(type);
        //2、设置着色资源并编译
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

}
