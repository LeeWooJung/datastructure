package treeStructure.Tree;

import treeStructure.Node.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SimpleTree<E> implements tree<E>{
    
    private Node<E> root;
    private int size;

    public SimpleTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean addRoot(Node<E> node) {
        root = node;
        size++;
        return true;
    }

    public boolean add(Node<E> parent, Node<E> current) {
        if(root == null) {
            System.out.println("There is no root in Tree.");
            System.out.println("Add Root first.");
            return false;
        }

        if(!Contain(parent)) {
            System.out.println("There is no Node " + parent.value + " in Tree.");
            System.out.println("Can't add node...");
            return false;
        } 

        List<Node<E>> children = parent.getChildren();


        current.parent = parent;
        current.depth = parent.depth + 1;

        children.add(current);
        size++;

        System.out.println("Successfuly add! value : " + current.value);

        return true;
    }

    public boolean Contain(Node<E> node) {

        if(root == null) {
            return false;
        }

        E value = node.value;

        Queue<Node<E>> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()) {
            Node<E> current = queue.poll();
            if(value == current.value) {
                queue.clear();
                return true;
            }
            for(Node<E> chlidren : current.getChildren()) {
                queue.offer(chlidren);
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(isEmpty()) {
            sb.append("Tree is Empty!");
            return sb.toString();
        }

        int depth = 0;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        sb.append("depth " + depth + ": ");

        while(!queue.isEmpty()) {
            Node<E> current = queue.poll();
            E value = current.value;
            if(depth == current.depth) {
                if(root == current) {
                    sb.append(value + "(root)" +" ");
                } else {
                    sb.append(value + "(" + current.parent.value + ")" +" ");
                }
            } else {
                depth++;
                sb.append("\ndepth " + depth + ": " + value + "(" + current.parent.value + ")" +" ");
            }

            for(Node<E> chlidren : current.getChildren()) {
                queue.offer(chlidren);
            }
        }

        sb.append("\n");
        return sb.toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
