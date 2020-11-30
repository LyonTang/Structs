package cn.ox0a.tree.struct;

import org.junit.Assert;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    final static String PRE = "ABCDEFGHI";
    final static String IN = "CBDEAGFIH";
    final static String POST = "CEDBGIHFA";
    final static String LEVEL = "ABFCDGHEI";
    final static int AC = 8;
    final static BinaryTree tree = new BinaryTree();
    static List<BinaryTreeNode> visitor = new ArrayList<BinaryTreeNode>();
    static {
        BinaryTreeNode<String> node = new BinaryTreeNode<String>("A");
        BinaryTreeNode<String>[] nodes = new BinaryTreeNode[AC];
        for (int i = 0; i < AC; i++){
            nodes[i] = new BinaryTreeNode<String>();
            nodes[i].setValue(String.valueOf((char) ("A".charAt(0) + i + 1)));
        }
        tree.initTree(node);
        node.setLeft(nodes[0]);
        node.setRight(nodes[4]);
        nodes[0].setLeft(nodes[1]);
        nodes[0].setRight(nodes[2]);
        nodes[2].setRight(nodes[3]);
        nodes[4].setLeft(nodes[5]);
        nodes[4].setRight(nodes[6]);
        nodes[6].setLeft(nodes[7]);
        /*for (int i = 0; i < AC - 1; i++) {
            nodes[i].setRight(nodes[i+1]);
        }*/
    }
    private String visit(List<BinaryTreeNode> visitor){
        StringBuilder builder = new StringBuilder();
        for (BinaryTreeNode node: visitor ) {
            builder.append(node.value);
        }
        return builder.toString();
    }

    @org.junit.Test
    public void preorder() {
        tree.preorder(visitor);
        Assert.assertEquals(visit(visitor), PRE);
    }

    @org.junit.Test
    public void preorder1() {
        BinaryTreeNode target = new BinaryTreeNode<String>(String.valueOf(PRE.charAt(PRE.length() - 3)));
        tree.preorder(visitor, target);
        Assert.assertEquals(visit(visitor), PRE.substring(0, PRE.length() - 2));
    }

    @org.junit.Test
    public void inorder() {
        tree.inorder(visitor);
        Assert.assertEquals(visit(visitor), IN);
    }

    @org.junit.Test
    public void inorder1() {
        BinaryTreeNode target = new BinaryTreeNode<String>(String.valueOf(IN.charAt(IN.length() - 3)));
        tree.inorder(visitor, target);
        Assert.assertEquals(visit(visitor), IN.substring(0, IN.length() - 2));
    }

    @org.junit.Test
    public void postorder() {
        tree.postorder(visitor);
        Assert.assertEquals(visit(visitor), POST);
    }

    @org.junit.Test
    public void postorder1() {
        BinaryTreeNode target = new BinaryTreeNode<String>(String.valueOf(POST.charAt(POST.length() - 3)));
        tree.postorder(visitor, target);
        Assert.assertEquals(visit(visitor), POST.substring(0, POST.length() - 2));
    }

    @org.junit.Test
    public void preorder2() {
        tree.preorder2(visitor);
        Assert.assertEquals(visit(visitor), PRE);
    }

    @org.junit.Test
    public void inorder2() {
        tree.inorder2(visitor);
        Assert.assertEquals(visit(visitor), IN);
    }

    @org.junit.Test
    public void postorder2() {
        tree.postorder2(visitor);
        Assert.assertEquals(visit(visitor), POST);
    }

    @org.junit.Test
    public void preorder3() {
        tree.preorder3(visitor);
        Assert.assertEquals(visit(visitor), PRE);
    }

    @org.junit.Test
    public void inorder3() {
        tree.inorder3(visitor);
        Assert.assertEquals(visit(visitor), IN);
    }

    @org.junit.Test
    public void postorder3() {
        tree.postorder3(visitor);
        Assert.assertEquals(visit(visitor), POST);
    }

    @org.junit.Test
    public void levelOrder() {
        tree.levelOrder(visitor);
        Assert.assertEquals(visit(visitor), LEVEL);
    }
}