package com.example.old.yamade.test;

import org.junit.Before;

import static junit.framework.TestCase.assertEquals;

public class PersonTest {

    Person mPerson;

    @Before
    //运行所有测试用例之前跑一次
    public void initPerson() {
        //创建测试对象
        mPerson = new Person();
    }

    @org.junit.Test
    public void testMakeMoney() {

        //调用测试方法
        int money = mPerson.makeMoney(100);

        //断言判断
        assertEquals(5000, money);

    }


}
