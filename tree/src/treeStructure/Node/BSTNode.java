package treeStructure.Node;

public class BSTNode<E> {
    
    public E value;
    public int depth;
    public int index;

    public BSTNode<E> left;
    public BSTNode<E> right;
    public BSTNode<E> parent;

    public BSTNode(E value) {
        this(value, null);
    }

    public BSTNode(E value, BSTNode<E> parent) {

        this.value = value;
        this.depth = 0;
        this.index = 0;

        this.left = null;
        this.right = null;
        this.parent = parent;
    }

    public int degree() {
        if(this.left == null && this.right == null) {
            return 0;
        } else if(this.left != null && this.right == null) {
            return 1;
        } else if(this.left == null && this.right != null) {
            return 1;
        } else {
            return 2;
        }
    }
}
