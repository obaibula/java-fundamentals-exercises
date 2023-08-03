package com.bobocode.cs;

public class Demo {
    public static void main(String[] args) {
        var list = ArrayList.of(4, 5, 3, 4, 54, 6, 3, 67, 56, 5, 6, 67);

        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.remove(2);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(0, 3);
        list.add(0, 1);
        list.add(1, 2);
        list.set(0, 999);
        list.set(list.size() - 1, 111);
        list.set(7, 333);

        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.get(7));
        System.out.println(list.contains(-1));
        System.out.println(list.contains(1));
        list.clear();
        System.out.println(list.isEmpty());
    }
}
