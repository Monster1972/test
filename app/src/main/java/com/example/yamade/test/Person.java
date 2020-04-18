package com.example.yamade.test;

public class Person {

    int mMoney;

    public int makeMoney(int grade){

        Dog dog = new Dog();
        dog.setmNickName("测试");

        int mApprec=0;

        if (grade<5){
            mApprec=1000;
        }else if (grade<10){
            mApprec=2000;
        }else {
            mApprec=5000;
        }

        mMoney=mMoney+mApprec;

        return mMoney;
    }

}
