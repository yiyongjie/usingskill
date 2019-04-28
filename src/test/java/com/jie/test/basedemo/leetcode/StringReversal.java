package com.jie.test.basedemo.leetcode;

public class StringReversal {
    /**
     * 字符串反转
     * @param code
     * @return
     */
    public static String reversal(String code){
        StringBuffer sb=new StringBuffer();
        char c;
        for(int i=code.length()-1;i>=0;i--){
            c=code.charAt(i);
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String code=reversal("hello world");
        System.out.println(code);
    }
}
