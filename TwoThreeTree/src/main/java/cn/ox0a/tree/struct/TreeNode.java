package cn.ox0a.tree.struct;

/**
 * @Description 多路查找树节点
 * @Author 唐亮
 * @Date 2020-11-26 19:31
 * @Version 1.0
 */
public interface TreeNode<V extends Comparable> {
    /**
     * 获取数值
     * @param index index
     * @return Value(index)
     */
    V getValue(int index);

    /**
     * 设置父节点
     * @param p 父节点
     */
    void setParent(TreeNode<V> p);

    /**
     * 获取父节点
     * @return TreeNode 父节点
     */
    TreeNode<V> getParent();


}
