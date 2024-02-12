package treeStructure.Tree;

public interface BST<E> extends tree<E>{

    public abstract boolean isEmpty();
    public abstract int size();
    public abstract boolean add(E value);
    
    public abstract boolean remove(E value);
    
    public abstract void inOrder();
    public abstract void preOrder();
    public abstract void postOrder();

}
