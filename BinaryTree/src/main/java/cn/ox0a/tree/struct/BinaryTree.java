package cn.ox0a.tree.struct;

import java.util.*;

/**
 * @Description 树操作
 * @Author 唐亮
 * @Date 2020-11-12 19:40
 * @Version 1.0
 */
public class BinaryTree<E extends Comparable> {
    private BinaryTreeNode<E> root;

    public BinaryTree(){

    }

    public void initTree(BinaryTreeNode<E> root){
        this.root = root;
    }

    /**
     * 初始化一个访问栈
     * @param visitor
     * @return
     */
    private Stack<BinaryTreeNode> orderStack(List<BinaryTreeNode> visitor){
        if(visitor == null || this.root == null) {
            return null;
        }
        visitor.clear();
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        return stack;
    }

    private void pushRightNoCheck(Stack<BinaryTreeNode> stack, BinaryTreeNode node){
        BinaryTreeNode child;
        if((child = node.getRightChild()) != null) {
            stack.push(child);
        }
    }

    /**
     * 出栈操作
     * @param stack 栈
     * @param visitor 访问链
     * @param target 目标访问元素
     * @return 栈顶元素
     */
    private BinaryTreeNode visit(Stack<BinaryTreeNode> stack, List<BinaryTreeNode> visitor, BinaryTreeNode target){
        BinaryTreeNode node = stack.pop();
        visitor.add(node);
        // 找到具体节点，不再继续访问
        if(target != null && target.value != null && target.value.equals(node.value)){
            return null;
        }
        return node;
    }

    // 序的概念是相对父节点的，其左右次序不变，总是先左后右
    /**
     * 先序遍历（父节点优先）
     * @param visitor 访问链
     * @param target
     * @return
     */
    public boolean preorder(List<BinaryTreeNode> visitor, BinaryTreeNode target){
        Stack<BinaryTreeNode> stack;
        if((stack = orderStack(visitor)) == null){
            return false;
        }
        BinaryTreeNode node, child;
        while (!stack.empty()){
            if((node = visit(stack, visitor, target)) == null){
                return true;
            }
            // 先入栈右孩子
            pushRightNoCheck(stack, node);
            if((child = node.getLeftChild()) != null) {
                stack.push(child);
            }
        }
        return true;
    }
    /**
     * 先序遍历
     * @param visitor
     * @return
     */
    public boolean preorder(List<BinaryTreeNode> visitor){
        return preorder(visitor, null);
    }

    /**
     * 中序遍历
     * @param visitor 访问链
     * @param target
     * @return
     */
    public boolean inorder(List<BinaryTreeNode> visitor, BinaryTreeNode target){
        Stack<BinaryTreeNode> stack;
        if((stack = orderStack(visitor)) == null){
            return false;
        }
        BinaryTreeNode node, child;
        while (!stack.empty()){
            node = stack.peek();
            // 这里数据量大时会相当耗时o(n平方) 感觉可以使用有序的哈希存储结构
            if((child = node.getLeftChild()) != null && !visitor.contains(child)) {
                stack.push(child);
            } else {
                // 找到最左侧子孙节点，出栈
                if((node = visit(stack, visitor, target)) == null){
                    return true;
                }
                // 如果有右孩子，入栈继续找右孩子最左侧子孙节点
                pushRightNoCheck(stack, node);
            }
        }
        return true;
    }

    /**
     * 中序遍历
     * @param visitor
     * @return
     */
    public boolean inorder(List<BinaryTreeNode> visitor){
        return inorder(visitor, null);
    }

    /**
     * 后序遍历
     * @param visitor 访问链
     * @param target
     * @return
     */
    public boolean postorder(List<BinaryTreeNode> visitor, BinaryTreeNode target){
        Stack<BinaryTreeNode> stack;
        if((stack = orderStack(visitor)) == null){
            return false;
        }
        BinaryTreeNode node, child;
        while (!stack.empty()){
            // 有孩子的情况应靠后考虑出栈该节点
            boolean pushed = false;
            node = stack.peek();
            // 保证右孩子先入栈
            if((child = node.getRightChild()) != null && !visitor.contains(child)){
                pushed = true;
                stack.push(child);
            }
            if((child = node.getLeftChild()) != null && !visitor.contains(child)){
                stack.push(child);
                pushed = true;
            }
            if(!pushed){
                if(visit(stack, visitor, target) == null){
                    return true;
                }
            }
        }
        return true;
    }

    public boolean postorder(List<BinaryTreeNode> visitor){
        return postorder(visitor, null);
    }
}
