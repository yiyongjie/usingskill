package com.jie.test.basedemo.function;

import java.util.*;

public class DealWithFunction {

    /**
     * 数组去重
     */
    public static String[] arrayUnique(String[] ss) {
        Set<String> set=new HashSet<String>(Arrays.asList(ss));
        return set.toArray(new String[set.size()]);
    }

    /**
     * 防止添加重复
     */
    public static void ListUnique(){
        List<String> list=new ArrayList<>();
        Map map=new HashMap();
        String a= (String)map.get("aa");
        if(a!=null){
            map.put("aa","unique");
        }
        list.add("aa");
    }

    public static void main(String[] args) {
        String[] a={"a","b","a","c"};
        String[] b=arrayUnique(a);
        System.out.println(b);
    }
}
