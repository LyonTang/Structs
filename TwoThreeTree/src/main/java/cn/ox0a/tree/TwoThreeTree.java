package cn.ox0a.tree;

import cn.ox0a.tree.struct.Position;
import cn.ox0a.tree.struct.TwoThreeTreeNode;
import org.jetbrains.annotations.NotNull;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-11-27 15:28
 * @Version 1.0
 */
public class TwoThreeTree<V extends Comparable<? super V>> {
    private TwoThreeTreeNode<V> root;

    public void setRoot(TwoThreeTreeNode<V> root){
        this.root = root;
    }

    /**
     * 插入
     * @param v value
     */
    public void insert(V v){
        insert(v, root);
    }

    /**
     * 数据插入
     * @param v value
     * @param node 当前节点
     */
    private void insert(@NotNull V v, @NotNull TwoThreeTreeNode<V> node){
        Position position = Position.MID;
        V min = node.getMin();
        V mid = node.getMid();
        TwoThreeTreeNode<V> min2MidChild = node.getChildMinToMid();
        if(v.compareTo(min) == 0 || (mid != null && v.compareTo(mid) == 0)) {
            return;
        }
        if(v.compareTo(min) < 0){
            position = Position.MIN;
        }else if(mid != null && v.compareTo(mid) > 0){
            position = Position.MAX;
        }

        if(mid == null && min2MidChild == null){
            // 为2-节点且没有右孩子
            node.insertValue(v, position);
        }else {
            // 为3-节点，需要转成4-节点进行分裂或向下继续查找插入位置
            TwoThreeTreeNode<V> lower = node.getChildMinDown();
            TwoThreeTreeNode<V> min2Max = node.getChildMinToMid();
            TwoThreeTreeNode<V> higher = node.getChildMidToMax();
            if(position == Position.MIN){
                if(lower == null) {
                    node.three2FourNodeInsertValue(v, position);
                    insertBalance(node);
                }else {
                    insert(v, lower);
                }
            }else if(position == Position.MID){
                if(min2Max == null){
                    node.three2FourNodeInsertValue(v, position);
                    insertBalance(node);
                }else {
                    insert(v, min2Max);
                }
            }else {
                if(higher == null){
                    node.three2FourNodeInsertValue(v, position);
                    insertBalance(node);
                }else {
                    insert(v, higher);
                }
            }
        }
    }

    /**
     * 自平衡
     * @param node 4-节点
     */
    private void insertBalance(@NotNull TwoThreeTreeNode<V> node){
        TwoThreeTreeNode<V> parent = (TwoThreeTreeNode<V>) node.getParent();
        TwoThreeTreeNode<V> newParentNode = divideFourNode(node);
        TwoThreeTreeNode<V> lower = newParentNode.getChildMinDown();
        TwoThreeTreeNode<V> higher = newParentNode.getChildMinToMid();
        if(parent == null) {
            root = newParentNode;
        }else if(parent.valueCount() == 1){
            if(parent.getMin().compareTo(newParentNode.getMin()) > 0){
                // 父节点大
                parent.insertValue(newParentNode.getMin(), Position.MIN);
                parent.two2ThreeNode(Position.MIN, lower, higher);
            }else{
                // 父节点小
                parent.insertValue(newParentNode.getMin(), Position.MID);
                parent.two2ThreeNode(Position.MID, lower, higher);
            }
            newParentNode.toNull();
        }else if(parent.valueCount() == 2){
            V toParent = newParentNode.getMin();
            if(parent.getMin().compareTo(toParent) > 0){
                // 低值插入
                parent.three2FourNodeInsertValue(toParent, Position.MIN);
                parent.three2FourNode(Position.MIN, lower, higher);
            }else if(parent.getMid().compareTo(newParentNode.getMin()) < 0){
                // 高值插入
                parent.three2FourNodeInsertValue(toParent, Position.MAX);
                parent.three2FourNode(Position.MAX, lower, higher);
            }else{
                // 中值插入
                parent.three2FourNodeInsertValue(toParent, Position.MID);
                parent.three2FourNode(Position.MID, lower, higher);
            }
            insertBalance(parent);
            newParentNode.toNull();
        }

    }

    /**
     * 4-节点分裂
     * @param node 4-节点
     * @return 新的父级节点
     */
    private TwoThreeTreeNode<V> divideFourNode(TwoThreeTreeNode<V> node){
        // 4-节点 分裂为3个 2-节点
        TwoThreeTreeNode<V> pNode = new TwoThreeTreeNode<V>(node.getMid());
        TwoThreeTreeNode<V> higherNode = new TwoThreeTreeNode<V>(node.getMax());
        TwoThreeTreeNode<V> lowerNode = node;
        // 父节点设置两个子孩子
        pNode.setChildMinDown(lowerNode);
        pNode.setChildMinToMid(higherNode);
        // node的孩子 分配
        lowerNode.setChildMinDown(node.getChildMinDown());
        lowerNode.setChildMinToMid(node.getChildMinToMid());
        higherNode.setChildMinDown(node.getChildMidToMax());
        higherNode.setChildMinToMid(node.getChildMaxUp());
        // 清空node高位子节点
        node.toTwoNode();
        return pNode;
    }

    public TwoThreeTreeNode<V> find(@NotNull V v){
        return find(v, root);
    }

    private TwoThreeTreeNode<V> find(@NotNull V v, TwoThreeTreeNode<V> node){
        if(node == null){
            return null;
        }
        if(node.contains(v)){
            return node;
        }
        V min = node.getMin();
        V mid = node.getMid();
        if(min.compareTo(v) > 0){
            return find(v, node.getChildMinDown());
        }else if(mid != null && mid.compareTo(v) < 0){
            return find(v, node.getChildMidToMax());
        }else{
            return find(v, node.getChildMinToMid());
        }
    }

    public void delete(V v){

    }
}
