package cn.ox0a.tree;

import cn.ox0a.tree.struct.BinaryTree;
import cn.ox0a.tree.struct.BinaryTreeNode;
import cn.ox0a.tree.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 数据测试
 * @Author 唐亮
 * @Date 2020-11-12 16:06
 * @Version 1.0
 */
public class Runner {
    static int AC = 8;
    static BinaryTree tree = new BinaryTree();

    public static void main(String[] args) {
        orderBinary();
    }

    public static void orderBinary(){
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

        List<BinaryTreeNode> visitor = new ArrayList<BinaryTreeNode>();
        if(tree.preorder(visitor)) {
            for (BinaryTreeNode n : visitor) {
                System.out.print(n.getValue());
            }
        }
        System.out.println();
        if(tree.inorder(visitor)) {
            for (BinaryTreeNode n : visitor) {
                System.out.print(n.getValue());
            }
        }
        System.out.println();

        if(tree.postorder(visitor/*, new BinaryTreeNode("G")*/)) {
            for (BinaryTreeNode n : visitor) {
                System.out.print(n.getValue());
            }
        }
        System.out.println();

        tree.preorder3(visitor);
        for (BinaryTreeNode n : visitor) {
            System.out.print(n.getValue());
        }
        System.out.println();

        tree.inorder3(visitor);
        for (BinaryTreeNode n : visitor) {
            System.out.print(n.getValue());
        }
        System.out.println();

        tree.postorder3(visitor);
        for (BinaryTreeNode n : visitor) {
            System.out.print(n.getValue());
        }
        System.out.println();

        tree.levelOrder(visitor);
        for (BinaryTreeNode n : visitor) {
            System.out.print(n.getValue());
        }
        System.out.println();
    }
}
