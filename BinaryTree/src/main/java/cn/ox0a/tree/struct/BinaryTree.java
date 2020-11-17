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
    /**
     * 后序遍历
     * @param visitor
     * @return
     */
    public boolean postorder(List<BinaryTreeNode> visitor){
        return postorder(visitor, null);
    }




    /**
     * 先序遍历递归实现
     * @param visitor
     * @param node
     */
    private void preorder2(List<BinaryTreeNode> visitor, BinaryTreeNode node){
        visitor.add(node);
        BinaryTreeNode child;
        if((child = node.getLeftChild()) != null) {
            preorder2(visitor, child);
        }
        if((child = node.getRightChild()) != null){
            preorder2(visitor, child);
        }
    }
    public void preorder2(List<BinaryTreeNode> visitor){
        visitor.clear();
        preorder2(visitor, root);
    }
    /**
     * 中序遍历递归实现
     * @param visitor
     * @param node
     */
    private void inorder2(List<BinaryTreeNode> visitor, BinaryTreeNode node){
        BinaryTreeNode child;
        if((child = node.getLeftChild()) != null){
            inorder2(visitor, child);
        }
        visitor.add(node);
        if((child = node.getRightChild()) != null){
            inorder2(visitor, child);
        }
    }
    public void inorder2(List<BinaryTreeNode> visitor){
        visitor.clear();
        inorder2(visitor, root);
    }
    /**
     * 后序遍历递归实现
     * @param visitor
     * @param node
     */
    private void postorder2(List<BinaryTreeNode> visitor, BinaryTreeNode node){
        if(node == null) {
            return;
        }
        postorder2(visitor, node.getLeftChild());
        postorder2(visitor, node.getRightChild());
        visitor.add(node);
    }
    public void postorder2(List<BinaryTreeNode> visitor){
        visitor.clear();
        postorder2(visitor, root);
    }


    // 网上看到非递归实现的算法，终于解决了visitor.contains的问题
    public void preorder3(List<BinaryTreeNode> visitor){
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode node = root;
        visitor.clear();
        while (node != null || !stack.empty()){
            while (node != null){
                visitor.add(node);
                stack.push(node);
                node = node.getLeftChild();
            }
            if(!stack.empty()){
                BinaryTreeNode temp = stack.pop();
                node = temp.getRightChild();
            }
        }
    }
    public void inorder3(List<BinaryTreeNode> visitor){
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode node = root;
        visitor.clear();
        while (node != null || !stack.empty()){
            // 找到最左侧孩子
            while (node != null){
                stack.push(node);
                node = node.getLeftChild();
            }
            if(!stack.empty()){
                BinaryTreeNode top = stack.pop();
                visitor.add(top);
                node = top.getRightChild();
            }
        }
    }

    /**
     * 后续遍历
     * 这个代码用上一个访问节点作为判断条件，解释了为何访问，虽然实现简单，
     * 但想到这层却也不是那么容易
     * @param visitor
     */
    public void postorder3(List<BinaryTreeNode> visitor){
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        BinaryTreeNode node = root;
        BinaryTreeNode lastNode = null;
        visitor.clear();
        while (node != null || !stack.empty()){
            while (node != null){
                stack.push(node);
                node = node.getLeftChild();
            }
            if(!stack.empty()){
                BinaryTreeNode top = stack.pop();
                // 任何节点的右孩子节点后都是紧接着该节点
                // 如果lastNode == rightChild，说明该节点尚未访问，且需要出栈
                // 最先的算法局限在何时入栈，忽略了出栈
                if(top.getRightChild() == null || top.getRightChild() == lastNode){
                    visitor.add(top);
                    lastNode = top;
                }else {
                    stack.push(top);
                    node = top.getRightChild();
                }
            }
        }
    }




    // 层次遍历，这个我在做图形客户端时常用于图形拓扑查找，类似广度遍历
    public void levelOrder(List<BinaryTreeNode> visitor){
        visitor.clear();
        LinkedList<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            BinaryTreeNode node = queue.poll();
            visitor.add(node);
            BinaryTreeNode child;
            if(null != (child = node.getLeftChild())){
                queue.add(child);
            }
            if(null != (child = node.getRightChild())){
                queue.add(child);
            }
        }
    }
}
