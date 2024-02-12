package treeStructure.Node;

public class BinaryNode<E> {
    
    public E value;
    public int depth;
    public int index;

    public BinaryNode<E> left;
    public BinaryNode<E> right;
    public BinaryNode<E> parent;

    public BinaryNode(E value) {
        this(value, null);
    }

    public BinaryNode(E value, BinaryNode<E> parent) {
        this.value = value;
        this.depth = 0;
        this.index = 0;
        this.parent = parent;
        this.left = null;
        this.right = null;
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
