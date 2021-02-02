package com.example.old.yamade.gl;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private FloatBuffer vertexBuffer;

    //定义顶点数
    static final int COORDS_PER_VERTEX = 3;

    //定义图形顶点
    static float triangleCoords[] = {
             0.0f,  0.622008459f, 0.0f,
            -0.5f, -0.311004243f, 0.0f,
             0.5f, -0.311004243f, 0.0f
    };

    //构建颜色数组，红、绿、蓝、透明度
    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    private final int mProgram;

    public Triangle() {

        // 创建图形坐标字节容器
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // 坐标个数 * 坐标字节占比
                triangleCoords.length * 4);

        // 硬件排序字节
        bb.order(ByteOrder.nativeOrder());

        //创建浮点型的顶点字节容器
        vertexBuffer = bb.asFloatBuffer();
        //添加顶点约束
        vertexBuffer.put(triangleCoords);
        //设置读取的起始位置
        vertexBuffer.position(0);

        //创建GL渲染器
        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // 创建GL程序容器
        mProgram = GLES20.glCreateProgram();

        // 添加渲染顶点shader
        GLES20.glAttachShader(mProgram, vertexShader);

        // 添加渲染形状shader
        GLES20.glAttachShader(mProgram, fragmentShader);

        // 执行GL容器程序
        GLES20.glLinkProgram(mProgram);
    }

    //顶点成员句柄
    private int mPositionHandle;
    //颜色句柄
    private int mColorHandle;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    public void draw() {

        // 设置GL容器程序
        GLES20.glUseProgram(mProgram);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // 启动三角形顶点句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // 准备图形数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // 设置绘制颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // 绘制图形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}