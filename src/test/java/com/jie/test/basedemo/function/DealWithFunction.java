package com.jie.test.basedemo.function;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DealWithFunction {
    public static String[] arrayUnique(String[] ss) {
        /**
         * 数组去重
         */
        Set<String> set=new HashSet<String>(Arrays.asList(ss));
        return set.toArray(new String[set.size()]);
    }

    public static void main(String[] args) {
        String[] a={"a","b","a","c"};
        String[] b=arrayUnique(a);
        System.out.println(b);
    }
}
