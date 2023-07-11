package com.bobocode.cs;

public class DemoApp {
    public static void main(String[] args) {
        var list = LinkedList.of(0, 4, 9, -4, 541);
        list.set(0, -1);
        var res = list.contains(-1);

        System.out.println(res);
    }
}
