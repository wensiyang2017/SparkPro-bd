package com.txkt.java;

import java.util.HashMap;

public class test1 {


    public static void main(String[] arg) {
        test();
    }

    private static void test() {


        String[] strs1 = {"1", "2", "3"};
        //String[] strs3 = {1, "2", "3"};
        String[] strs2 = new String[3];
        Object[] objs = new Object[3];
        objs[0] = 1;
        objs[1] = "123";
        objs[2] = new HashMap<String,Object>();
        System.out.println(objs[1]);

    }

}
