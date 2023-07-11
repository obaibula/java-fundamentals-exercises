package com.bobocode.cs;


import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.util.Objects.checkIndex;
import static java.util.Objects.requireNonNull;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;


    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        var list = new LinkedList<T>();

        Stream.of(elements)
                .forEach(list::add);

        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        add(size, element);
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        requireNonNull(element);
        checkIndex(index, size + 1);

        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            addAsHead(newNode);
        } else if (index == size) {
            addAsTail(newNode);
        } else {
            addInBetween(index, newNode);
        }
        size++;
    }

    private void addAsHead(Node<T> newNode) {
        Node<T> currentNode = head;
        if (head == null) {
            head = tail = newNode;
        } else {
            head = newNode;
            head.next = currentNode;
        }
    }

    private void addAsTail(Node<T> newNode) {
        Node<T> currentNode = tail;
        tail = newNode;
        currentNode.next = tail;
    }

    private void addInBetween(int index, Node<T> newNode) {
        var previousNode = getNodeByIndex(index - 1);
        var currentNode = previousNode.next;
        previousNode.next = newNode;
        newNode.next = currentNode;
    }

    private Node<T> getNodeByIndex(int index) {
        checkIndex(index, size);
        var currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        requireNonNull(element);
        var node = getNodeByIndex(index);
        node.element = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        return getNodeByIndex(index).element;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (head == null) throw new NoSuchElementException();
        else return head.element;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (tail == null) throw new NoSuchElementException();
        else return tail.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        var nodeToRemove = removeAndGetRemovedNode(index);
        return nodeToRemove.element;
    }

    private Node<T> removeAndGetRemovedNode(int index) {
        checkIndex(index, size);

        if (index == 0) {
            var oldHead = head;
            head = head.next;
            size--;
            return oldHead;
        }

        var previousNode = getNodeByIndex(index - 1);
        var removedNode = previousNode.next;
        previousNode.next = removedNode.next;

        if (index == size - 1) {
            tail = previousNode;
        }

        size--;
        return removedNode;
    }

    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (!isEmpty()) {
            var currentNode = head;
            while (currentNode != null) {
                if (currentNode.element.equals(element))
                    return true;
                currentNode = currentNode.next;
            }
        }

        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    public void reverse() {
        if (size == 1 || size == 0)
            return;

        tail = head;
        Node<T> previous = null;
        Node<T> current = head;


       while (current != null){
           Node<T> next = current.next;
           current.next = previous;
           previous = current;
           current = next;
       }

       head = previous;
    }

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }
}
