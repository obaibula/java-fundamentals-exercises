package com.bobocode.tdd;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class RecursiveBinarySearchTree<T extends Comparable<? super T>>
        implements BinarySearchTree<T> {
    private Node<T> root;


    public static <T extends Comparable<? super T>>
    RecursiveBinarySearchTree<T> of(T... elements) {
        var bst = new RecursiveBinarySearchTree<T>();
        Stream.of(elements)
                .forEach(bst::insert);
        return bst;
    }

    @Override
    public boolean insert(T element) {
        var newNode = new Node<T>(element);
        if (root == null) {
            this.root = newNode;
            return true;
        } else {
            return insertIntoTree(root, element);
        }
    }

    private boolean insertIntoTree(Node<T> node, T element) {
        var newNode = new Node<T>(element);
        if (node.element.compareTo(element) > 0) {
            return insertIntoLeftSubTree(node, element, newNode);
        } else if (node.element.compareTo(element) < 0) {
            return insertIntoRightSubTree(node, element, newNode);
        } else {
            return false;
        }
    }

    private boolean insertIntoRightSubTree(Node<T> node, T element, Node<T> newNode) {
        if (node.right == null) {
            node.right = newNode;
            return true;
        } else {
            return insertIntoTree(node.right, element);
        }
    }

    private boolean insertIntoLeftSubTree(Node<T> node, T element, Node<T> newNode) {
        if (node.left == null) {
            node.left = newNode;
            return true;
        } else {
            return insertIntoTree(node.left, element);
        }
    }

    @Override
    public boolean contains(T element) {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public int size() {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public int depth() {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if(node != null){
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.element);
            inOrderTraversal(node.right, consumer);
        }
    }

    private static class Node<T> {
        private T element;
        private Node<T> left;
        private Node<T> right;

        public Node(T element) {
            this.element = element;
        }
    }
}
