package com.example.new_.design.proxy;

/*
 * 代理姿势2：适用于代理第三方Code
 *
 * @Author: dottang
 * @Date: 2021/1/31
 */
public class LoginProxy2 extends Login{

    public LoginProxy2() {
       // 初始化非业务的依赖类
    }

    @Override
    public int login(String userName) {

        // 开启耗时统计（非登录业务）

        int loginState = super.login(userName);

        if (loginState == 0) {
            // 执行登录成功上报逻辑（非登录业务）
        }

        return loginState;
    }
}
