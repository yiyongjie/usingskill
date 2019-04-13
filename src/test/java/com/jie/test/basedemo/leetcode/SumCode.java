package com.jie.test.basedemo.leetcode;

import java.util.Arrays;

public class SumCode {
    /**
     *那什么两数之和
     * @param arr
     * @param num
     */
    public static int[] sumArray(int[] arr ,int num){
        int[] option=new int[2];
        for(int i=0;i<arr.length;i++){
            int sum=0;
            int one=arr[i];
            for(int j=i+1;j<arr.length;j++){
                int two=arr[j];
                sum=one+two;
                if(sum==num){
                    option[0]=i;
                    option[1]=j;
                }
            }
        }
        return option;
    }

    public static void main(String[] args) {
        int[] arr={2, 7, 11, 15,20,3,8};
        int result[]=sumArray(arr,11);
        System.out.println(result.toString());
    }
}
