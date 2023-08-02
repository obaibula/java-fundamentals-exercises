package com.bobocode.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTreeTest {
    BinarySearchTree<Integer> bst;
    @BeforeEach
    void setUp() {
        bst = RecursiveBinarySearchTree.of(45, 5, 98, 23, 17, 342);
    }

    @Test
    public void testCreateBST(){
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
    }

    @Test
    public void InsertIntoEmptyTree(){
        bst = new RecursiveBinarySearchTree<>();
        boolean isInserted = bst.insert(35);

        assertThat(isInserted).isTrue();
    }

    @Test
    public void insertSameElementTwice(){
        bst.insert(43);
        boolean isInsertedSecondTime = bst.insert(43);
        assertThat(isInsertedSecondTime).isFalse();
    }

    @Test
    public void shouldInsertElements(){


        var isNewInserted = bst.insert(898);
        var isDuplicateInserted = bst.insert(98);
        var isRootInsertedSecondTime = bst.insert(45);

        assertThat(isNewInserted).isTrue();
        assertThat(isRootInsertedSecondTime).isFalse();
        assertThat(isDuplicateInserted).isFalse();
    }

    @Test
    public void testInOrderTraversal(){
        List<Integer> bstElements = new LinkedList<>();
        bst.inOrderTraversal(bstElements::add);
        assertThat(bstElements)
                .containsExactly(5, 17, 23, 45, 98, 342);
    }
}
