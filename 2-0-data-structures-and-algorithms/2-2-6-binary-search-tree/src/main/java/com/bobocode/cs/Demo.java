package com.bobocode.cs;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        var tree = RecursiveBinarySearchTree.of(3d,1d,2d,4d,5d);

        tree.insert(-10d);
        tree.insert(2.4);
        tree.insert(0.01);
        tree.insert(0.03);
        tree.insert(0.02);
        tree.inOrderTraversal(list::add);

        System.out.println(list);
    }
}
