package cn.ox0a.tree;

import cn.ox0a.tree.struct.TwoThreeTreeNode;

import static org.junit.Assert.*;

public class TwoThreeTreeTest {

    @org.junit.Test
    public void setRoot() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<Integer>();
        Integer root = new Integer(1);
        tree.setRoot(new TwoThreeTreeNode<Integer>(root));
    }

    @org.junit.Test
    public void insert() {
        TwoThreeTree<Integer> tree = new TwoThreeTree<Integer>();
        Integer root = new Integer(1);
        tree.setRoot(new TwoThreeTreeNode<Integer>(root));
        for (int i = 2; i < 10; i++) {
            tree.insert(new Integer(i));
        }
    }

    @org.junit.Test
    public void find() {
    }
}