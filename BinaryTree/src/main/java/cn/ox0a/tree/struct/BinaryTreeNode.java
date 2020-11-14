package cn.ox0a.tree.struct;

import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 树节点
 * @Author 唐亮
 * @Date 2020-11-12 16:07
 * @Version 1.0
 */
public class BinaryTreeNode<V extends Comparable> extends BaseTreeNode<V> {

    private BinaryTreeNode<V> left;
    private BinaryTreeNode<V> right;

    public TreeNode getChild(int index) {
        if(index == 0){
            return getLeftChild();
        }
        else if(index == 1){
            return getRightChild();
        }
        else{
            throw new IndexOutOfBoundsException("二叉树仅支持左右子节点查询");
        }
    }

    /**
     * 获取左孩子
     * @return
     */
    public BinaryTreeNode<V> getLeftChild() {
        return left;
    }

    /**
     * 获取右孩子
     * @return
     */
    public BinaryTreeNode<V> getRightChild() {
        return right;
    }

    public void setChild(int index, TreeNode child) {
        if (!(child instanceof BinaryTreeNode)) {
            throw new IllegalArgumentException("子节点不符合二叉树要求");
        }
        if(index == 0){
            setLeft((BinaryTreeNode<V>) child);
        }
        else if(index == 1){
            setRight((BinaryTreeNode<V>) child);
        }
        else{
            throw new IndexOutOfBoundsException("二叉树仅支持左右子节点设置");
        }
    }

    /**
     * 设置左子节点
     * @param left
     */
    public void setLeft(BinaryTreeNode<V> left) {
        this.left = left;
        left.parent = this;
    }

    /**
     * 设置右子节点
     * @param right
     */
    public void setRight(BinaryTreeNode<V> right) {
        this.right = right;
        right.parent = this;
    }

    public List<TreeNode> allChildren(){
        List<TreeNode> nodes = new ArrayList<TreeNode>(2);
        nodes.add(left);
        nodes.add(right);
        return nodes;
    }

    public int childrenCounts() {
        return 2;
    }


    public BinaryTreeNode(){ }

    public BinaryTreeNode(V value){
        super(value);
    }

    public BinaryTreeNode(BinaryTreeNode left, BinaryTreeNode right, BinaryTreeNode parent, V value){
        super(parent, value);
        this.left = left;
        this.right = right;
    }
}
