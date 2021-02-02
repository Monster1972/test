package com.example.old.yamade.test;

import java.util.ArrayList;

public class A {


    public String xx = "";


   public ArrayList<String> data = new ArrayList<String>() {
        @Override
        public boolean add(String s) {
            return super.add(s);
        }

        @Override
        public void ensureCapacity(int minCapacity) {
            super.ensureCapacity(minCapacity);
        }
    };

}
