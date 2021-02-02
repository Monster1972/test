package com.example.new_.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * 代理姿势3：动态代理，避免创建冗余的代理类
 *
 * @Author: dottang
 * @Date: 2021/1/31
 */
public class LoginProxy3 {

    public Object createLoginProxy(Object proxyObj){

        // 第二步：第二个参数
        Class<?>[] interfaces = proxyObj.getClass().getInterfaces();

        // 第三步：第三个参数
        DynamicLoginHandler handler = new DynamicLoginHandler(proxyObj);

        // 第一步：一个接口 + 三个参数
        Object proxyInstance = Proxy.newProxyInstance(proxyObj.getClass().getClassLoader(), interfaces, handler);

        return proxyInstance;
    }

    public static class DynamicLoginHandler<T> implements InvocationHandler {

        T target;

        public DynamicLoginHandler(T target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            // proxy - 底层返回的动态代理实例
            // method - 底层返回的动态代理实例的回调方法
            // args - 底层返回的动态代理实例的回调方法参数

            // 实际调用底层代理类 "invoke" 方法
            Object result = method.invoke(target, args);

            return result;
        }
    }
}
