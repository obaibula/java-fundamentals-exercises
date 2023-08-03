package com.bobocode.cs;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static java.util.Objects.checkIndex;
import static java.util.Objects.requireNonNull;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> implements List<T> {
    private int size;
    private Object[] internalArray;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
        if(initCapacity <= 0){
            throw new IllegalArgumentException();
        }
        this.internalArray = new Object[initCapacity];
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
        this(5);
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
        var list = new ArrayList<T>();
        Arrays.stream(elements)
                .forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        add(size, element);
    }


    private void resizeArray() {
        internalArray = Arrays.copyOf(internalArray, size * 2);
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        checkIndex(index, size + 1);
        requireNonNull(element);
        if (size >= internalArray.length) {
            resizeArray();
        }

        System.arraycopy(internalArray, index,
                internalArray, index + 1, internalArray.length - index - 1);
        internalArray[index] = element;
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T)internalArray[index];
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return get(0);
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return get(size - 1);
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
        checkIndex(index, size);
        requireNonNull(element);
        internalArray[index] = element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index, size);
        var element = (T)internalArray[index];
        System.arraycopy(internalArray, index + 1,
                internalArray, index, internalArray.length - index - 1);
        size--;
        return element;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
        return Arrays.asList(internalArray)
                .contains(element);
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
     * @return amount of saved elements
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
        internalArray = new Object[5];
        size = 0;
    }

    @Override
    public void reverse() {

    }
}
