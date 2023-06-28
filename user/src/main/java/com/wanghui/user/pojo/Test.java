package com.wanghui.user.pojo;

public class Test {
    public static void main(String[] args) {
        func(3.3,6.3);
    }
    public static void func(double start,double end){
        for(int i = 0;i < 30;i++){
            System.out.println(String.format("%.2f",start + Math.random() * (end - start)));
        }
    }
}
