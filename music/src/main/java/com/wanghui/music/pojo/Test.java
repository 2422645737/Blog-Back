package com.wanghui.music.pojo;

public class Test {
    public static void main(String[] args) {
        double start = 1.11;
        double end = 39.15;
        for(int i = 0;i < 30;i++){
            System.out.println(String.format("%.2f",start + Math.random() * (end - start)));
        }
    }
}
