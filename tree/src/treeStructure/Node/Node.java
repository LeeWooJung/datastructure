package treeStructure.Node;

import java.util.LinkedList;
import java.util.List;


public class Node<E> {
    
    public E value;
    public Node<E> parent;
    public int depth;
    public List<Node<E>> children;

    public Node(E value) {
        this.value = value;
        this.parent = null;
        this.depth = 0;
        this.children = new LinkedList<>();
    }

    public E get() {
        return value;
    }

    public List<Node<E>> getChildren() {
        return children;
    }

    public int degree() {
        return children.size();
    }
}
