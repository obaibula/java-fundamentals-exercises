package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private int size;
    private Node<T> root;

    @SafeVarargs
    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        var tree = new RecursiveBinarySearchTree<T>();
        Stream.of(elements)
                .forEach(tree::insert);
        return tree;
    }

    /*@Override
    public boolean insert(T element) {
        requireNonNull(element);
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }

        return insert(element, root);

    }

    private boolean insert(T element, Node<T> currentNode) {

        if (element.compareTo(currentNode.element) > 0) {
            if (currentNode.right == null) {
                currentNode.right = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(element, currentNode.right);
            }
        } else if (element.compareTo(currentNode.element) < 0) {
            if (currentNode.left == null) {
                currentNode.left = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(element, currentNode.left);
            }
        }
        return false;
    }*/

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }
        var currentNode = root;
        Deque<Node<T>> stack = new ArrayDeque<>();

        while (currentNode != null) {
            if(element.compareTo(currentNode.element) == 0){
                return false;
            }

            if(element.compareTo(currentNode.element) > 0){
                currentNode = currentNode.right;
                stack.addFirst(currentNode);
            }else {
                currentNode = currentNode.left;
                stack.addFirst(currentNode);
            }
        }

        Node<T> place = stack.removeFirst();
        Node<T> newNode = new Node<>(element);
        if (element.compareTo(place.element) > 0){
            place.right = newNode;
        } else {
            place.left =newNode;
        }

        size++;
        return true;
    }

    @Override
    public boolean contains(T element) {
        requireNonNull(element);

        if (root == null) {
            return false;
        }

        return contains(element, root);
    }

    private boolean contains(T element, Node<T> currentNode) {
        if (element.compareTo(currentNode.element) > 0) {
            return checkRightBrunch(element, currentNode);
        } else if (element.compareTo(currentNode.element) < 0) {
            return checkLeftBranch(element, currentNode);
        }

        return true;
    }

    private boolean checkRightBrunch(T element, Node<T> currentNode) {
        if (currentNode.right != null) {
            return contains(element, currentNode.right);
        } else {
            return false;
        }
    }

    private boolean checkLeftBranch(T element, Node<T> currentNode) {
        if (currentNode.left != null) {
            return contains(element, currentNode.left);
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root != null ? depth(root) - 1 : 0;
    }

    private int depth(Node<T> currentNode) {
        if (currentNode == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(currentNode.left), depth(currentNode.right));
        }
    }


    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        Deque<Node<T>> stack = new ArrayDeque<>();
        var currentNode = root;

        while (currentNode != null || !stack.isEmpty()){
            while (currentNode != null){
                stack.addFirst(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.removeFirst();
            consumer.accept(currentNode.element);
            currentNode = currentNode.right;
        }
    }

    private static class Node<T extends Comparable<T>> {
        Node<T> left;
        Node<T> right;
        T element;

        public Node(T element) {
            this.element = element;
        }
    }
}
