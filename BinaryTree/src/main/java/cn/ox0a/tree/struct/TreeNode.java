package cn.ox0a.tree.struct;

import java.util.List;

public interface TreeNode<V> {

    /**
     * 设置子节点
     * @param index index
     * @param child 子节点
     */
    void setChild(int index, TreeNode child);

    /**
     * 单个获取子节点
     * @param index index
     * @return TreeNode
     */
    TreeNode getChild(int index);

    /**
     * 树节点权值
     * @return value
     */
    V getValue();

    /**
     * 设置权值
     */
    void setValue(V value);

    /**
     * 获取全部子节点
     * @return
     */
    List<TreeNode> allChildren();

    /**
     * 子节点个数
     * @return
     */
    int childrenCounts();

    void setParent(TreeNode node);
    TreeNode getParent();
}
