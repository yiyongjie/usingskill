package com.jie.test.basedemo;

public class BaseDemo {
    public static void main(String[] args) {
        int[] array={9,3,5,1,4,2,0,7,6,8};
//        bubble(array);
        select(array);
    }

    /**
     * 冒泡
     * @param array
     */
    public static void bubble(int[] array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-1;j++){
                if(array[j]>array[j+1]){
                    int temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
        StringBuffer sb=new StringBuffer();
        for(int k=0;k<array.length;k++){
            sb.append(array[k]);
        }
        System.out.println(sb.toString());
    }

    /**
     * 选择
     * @param array
     */
    public static void select(int array[]){
        for(int i=0;i<array.length-1;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[j]<array[i]){
                    int temp=array[j];
                    array[j]=array[i];
                    array[i]=temp;
                }
            }
        }
        StringBuffer sb=new StringBuffer();
        for(int k=0;k<array.length;k++){
            sb.append(array[k]);
        }
        System.out.println(sb.toString());
    }
}
