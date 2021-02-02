package com.example.new_.design.proxy;

import com.example.new_.design.proxy.api.ILogin;
import com.example.new_.util.TLOG;

/*
 * 被代理类：单一职责，仅负责登录业务处理
 * @Author: dottang
 * @Date: 2021/1/31
 */
public class Login implements ILogin {


    @Override
    public int login(String userName) {

        // 获取用户信息

        // 执行登录操作

        // 返回登录状态

        TLOG.d("代理模式: ", userName + " 登录成功...");
        return 0;
    }
}
