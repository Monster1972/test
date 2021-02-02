package com.example.new_.design.proxy;

import com.example.new_.design.proxy.api.ILogin;


/**
 * 代理姿势1：适用于自编自导，属于自己的代码
 */

/*
 * 代理类：持有被代理对象，处理和登录业务无关的附加逻辑。使登录业务职责单一
 * @Author: dottang
 * @Date: 2021/1/31
 */
public class LoginProxy1 implements ILogin {

    private final Login mLogin;

    public LoginProxy1() {
        mLogin = new Login();
    }

    @Override
    public int login(String userName) {

        // 开启耗时统计（非登录业务）

        int loginState = mLogin.login(userName);

        if (loginState == 0) {
            // 执行登录成功上报逻辑（非登录业务）
        }

        return loginState;
    }
}
