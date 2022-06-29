package com.example.drill.etc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Drill {
    public static void main(String[] args) {
        System.out.println("run");
        try{
            System.out.println("1");
            if(true) return;
//            System.out.println(1/0);
            System.out.println("2");
        }catch(Throwable t){
            System.out.println(t);
        }finally {
            System.out.println("final");
        }
        System.out.println("end");
    }
}
