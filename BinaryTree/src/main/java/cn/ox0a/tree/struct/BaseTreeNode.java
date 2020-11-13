package cn.ox0a.tree.struct;

/**
 * @Description TODO
 * @Author 唐亮
 * @Date 2020-11-12 16:14
 * @Version 1.0
 */
public abstract class BaseTreeNode<V> implements TreeNode<V> {
   protected V value;
   protected TreeNode<V> parent;

   public BaseTreeNode(){}

   public BaseTreeNode(V value){
       this(null, value);
   }

   public BaseTreeNode(TreeNode<V> parent){
       this(parent, null);
   }

   public BaseTreeNode(TreeNode<V> parent, V value){
       this.parent = parent;
       this.value = value;
   }

    public V getValue() {
        return value;
    }

    public void setValue(V value){
        this.value = value;
    }

    public TreeNode<V> getParent() {
        return parent;
    }

    public void setParent(TreeNode node) {
        this.parent = node;
    }
}
