package cn.ox0a.tree.struct;

/**
 * @Description 2-3树节点，包括2-节点，3-节点，4-节点
 * @Author 唐亮
 * @Date 2020-11-27 11:03
 * @Version 1.0
 */
public class TwoThreeTreeNode<V extends Comparable> implements TreeNode {

    private V min;
    private V mid;
    private V max;
    private TwoThreeTreeNode<V> childMinDown;
    private TwoThreeTreeNode<V> childMinToMid;
    private TwoThreeTreeNode<V> childMidToMax;
    private TwoThreeTreeNode<V> childMaxUp;
    private TwoThreeTreeNode<V> parent;

    private static final int VALUE_SIZE = 3;
    private static final int CHILD_SIZE = 4;

    public TwoThreeTreeNode(V v){
        max = mid = null;
        childMinDown = childMinToMid = childMidToMax = childMaxUp = null;
        min = v;
    }

    public void setMin(V v){
        min = v;
    }
    public void setMid(V v){
        mid = v;
    }
    public void setMax(V v){
        max = v;
    }
    /**
     * 2节点 最高位
     * @return Value[0]
     */
    public V getMin(){
        return min;
    }
    /**
     * 3节点 最高位
     * @return Value[1]
     */
    public V getMid(){
        return mid;
    }
    /**
     * 4节点 最高位 仅作为虚拟节点时使用
     * @return Value[2]
     */
    public V getMax(){
        return max;
    }

    public void setChildMinDown(TwoThreeTreeNode<V> n){
        if(n != null){
            n.setParent(this);
        }
        childMinDown = n;
    }
    public TwoThreeTreeNode<V> getChildMinDown(){
        return childMinDown;
    }

    public void setChildMinToMid(TwoThreeTreeNode<V> n){
        if(n != null){
            n.setParent(this);
        }
        childMinToMid = n;
    }
    public TwoThreeTreeNode<V> getChildMinToMid(){
        return childMinToMid;
    }

    public void setChildMidToMax(TwoThreeTreeNode<V> n){
        if(n != null){
            n.setParent(this);
        }
        childMidToMax = n;
    }
    public TwoThreeTreeNode<V> getChildMidToMax(){
        return childMidToMax;
    }

    public void setChildMaxUp(TwoThreeTreeNode<V> n){
        if(n != null){
            n.setParent(this);
        }
        childMaxUp = n;
    }
    public TwoThreeTreeNode<V> getChildMaxUp(){
        return childMaxUp;
    }


    /**
     * 数值插入
     * @param v 待插入值值
     * @param pos 位置
     */
    public void insertValue(V v, Position pos){
        if(pos == Position.MID) {
            mid = v;
        }else if(pos == Position.MIN){
            mid = min;
            min = v;
        }else{
            // forbidden
        }
    }

    /**
     * 3-节点 转 4-节点（值）
     * @param v 待插入值
     * @param position 插入位置
     */
    public void three2FourNodeInsertValue(V v, Position position){
        if(position == Position.MIN){
            max = mid;
            mid = min;
            min = v;
        }else if(position == Position.MID){
            max = mid;
            mid = v;
        }else {
            max = v;
        }
    }

    public void toNull(){
        mid = min = max = null;
        childMinDown = childMinToMid = childMidToMax = childMaxUp = null;
    }

    /**
     * 转为2-节点。
     * 仅保留低位数值和孩子
     */
    public void toTwoNode(){
        // 清空高位数据和高位子节点
        mid = max = null;
        childMidToMax = childMaxUp = null;
    }

    /**
     * 2-节点 转为 3-节点(孩子节点重置继承）
     * @param position 插入位置
     * @param lower 较低子节点
     * @param higher 较高子节点
     */
    public void two2ThreeNode(Position position, TwoThreeTreeNode<V> lower, TwoThreeTreeNode<V> higher){
        if(Position.MIN == position){
            childMidToMax = childMinToMid;
            setChildMinDown(lower);
            setChildMinToMid(higher);
        }else if(Position.MID == position){
            setChildMinToMid(lower);
            setChildMidToMax(higher);
        }else {
            // forbidden
        }
    }

    /**
     * 3-节点 转为 4-节点(孩子节点重置继承）
     * @param position 插入位置
     * @param lower 较低子节点
     * @param higher 较高子节点
     */
    public void three2FourNode(Position position, TwoThreeTreeNode<V> lower, TwoThreeTreeNode<V> higher){
        if(Position.MIN == position){
            childMaxUp = childMidToMax;
            childMidToMax = childMinToMid;
            childMinToMid = childMinDown;
            setChildMinDown(lower);
            setChildMinToMid(higher);
        }else if(Position.MID == position){
            childMaxUp = childMinToMid;
            setChildMinToMid(lower);
            setChildMidToMax(higher);
        }else {
            setChildMidToMax(lower);
            setChildMaxUp(higher);
        }
    }

    public V getValue(int index) {
        return null;
    }

    public void setParent(TreeNode p) {
        this.parent = (TwoThreeTreeNode<V>) p;
    }

    public TreeNode<V> getParent() {
        return this.parent;
    }


    /**
     * 值存储个数（不考虑4-节点）
     * @return Values.length
     */
    public int valueCount(){
        int s = 0;
        s += getMin() == null ? 0 : 1;
        s += getMid() == null ? 0 : 1;
        return s;
    }

    /**
     * 孩子个数（不考虑4-节点）
     * @return Children.length
     */
    public int childrenCount(){
        int s = 0;
        s += getChildMinDown() == null ? 0 : 1;
        s += getChildMinToMid() == null ? 0 : 1;
        s += getChildMidToMax() == null ? 0 : 1;
        return s;
    }
}
