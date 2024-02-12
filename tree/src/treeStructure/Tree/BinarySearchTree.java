package treeStructure.Tree;

import java.util.Queue;
import java.util.LinkedList;

import treeStructure.Node.BSTNode;

public class BinarySearchTree<E> implements BST<E>{
    
    private BSTNode<E> root;
    private int size;
    private int height;
    private E[] array;

    @SuppressWarnings("unchecked")
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
        this.height = 0;
        this.array = (E[]) new Object[30];
    }

    public boolean add(E value) {

        if(isEmpty()) {
            BSTNode<E> node = new BSTNode<E>(value);
            node.index = 1;
            root = node;
            array[node.index] = node.value;
            size++;
            return true;
        }

        if(contain(value)) {
            System.out.println("Can't add! Since value(" + value + ") is in the tree.");
            return false;
        }

        BSTNode<E> parent = null;
        BSTNode<E> current = root;

        @SuppressWarnings("unchecked")
        Comparable<? super E> toCompare = (Comparable<? super E>) value;
        int resCompare;

        while(true) {
            
            parent = current;
            resCompare = toCompare.compareTo(current.value);

            if(resCompare < 0) current = current.left;
            else current = current.right;

            if(current == null) break;

        }

        BSTNode<E> node = new BSTNode<E>(value);

        node.parent = parent;

        if(resCompare < 0) {
            parent.left = node;
            node.index = parent.index * 2;
        } else {
            parent.right = node;
            node.index = parent.index * 2 + 1;
        }

        node.depth = parent.depth + 1;
        if(node.depth > this.height) {
            this.height = node.depth;
        }

        if(node.index >= this.array.length) {
            resize(node.index * 2);
        }
        this.array[node.index] = node.value;

        this.size++;
        System.out.println("Successfully added the node value(" + value +")");

        return true;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {

        E[] newArray = (E[]) new Object[newCapacity];

        for(int i = 1; i < this.array.length; i++) {
            newArray[i] = array[i];
        }

        this.array = null;
        this.array = newArray;
    }

    public boolean contain(E value) {

        BSTNode<E> current = root;

        @SuppressWarnings("unchecked")
        Comparable<? super E> toCompare = (Comparable<? super E>) value;
        int resComp;

        while(current != null) {
            resComp = toCompare.compareTo(current.value);
            if(resComp < 0) {
                current = current.left;
            } else if(resComp > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }

    public boolean remove(E value) {

        if(isEmpty()) {
            System.out.println("The tree is Empty!");
            return false;
        }

        if(!contain(value)) {
            System.out.println("There isn't that value (" + value + ") in Tree!");
            return false;
        }

        removeNode(search(value));

        return true;
    }

    private void removeNode(BSTNode<E> node) {

        BSTNode<E> parent = node.parent;
        Boolean isLeftChild = false;

        if(parent != null) {
            if(parent.left == node) isLeftChild = true;
        }

        if(node.degree() == 0) { // 자식 노드가 없을 때
            if(node == root) {
                root = null;
            } else {
                node = null;
                if(isLeftChild) parent.left = null;
                else parent.right = null;
            }
        } else if(node.degree() == 1) { // 자식 노드가 한 개 일때

            BSTNode<E> child;

            if(node.left != null) {
                child = node.left;
            } else {
                child = node.right;
            }

            if(parent == null) { // node is root
                root = child;
            } else {
                if(isLeftChild) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
            child.parent = parent;
            node = null;
        } else { // 자식 노드가 두 개 일때

            BSTNode<E> toReplaceNode = findPredecessor(node);
            node.value = toReplaceNode.value;
            removeNode(toReplaceNode);
            this.size++;
        }

        this.size--;

        resetProperty();

    }

    private void resetProperty() {

        int maxIndex = (int) Math.pow(2, height + 1) - 1;

        BSTNode<E> current;
        BSTNode<E> leftChild, rightChild;

        // initialize array, height
        for(int pos = 1; pos <= Math.min(this.array.length - 1, maxIndex); pos++) {
            this.array[pos] = null;
        }
        this.height = 0;

        if(isEmpty()) return;

        Queue<BSTNode<E>> queue = new LinkedList<>();
        queue.offer(this.root);
        this.root.depth = 0;
        this.root.index = 1;
        this.root.parent = null;
        this.array[root.index] = root.value;

        while(!queue.isEmpty()) {
            current = queue.poll();

            leftChild = current.left;
            rightChild = current.right;

            if(leftChild != null) {
                leftChild.parent = current;
                leftChild.index = current.index * 2;
                leftChild.depth = current.depth + 1;
                this.array[leftChild.index] = leftChild.value;
                if(leftChild.depth > this.height) this.height = leftChild.depth;

                queue.offer(leftChild);
            }
            if(rightChild != null) {
                rightChild.parent = current;
                rightChild.index = current.index * 2 + 1;
                rightChild.depth = current.depth + 1;
                this.array[rightChild.index] = rightChild.value;
                if(rightChild.depth > this.height) this.height = rightChild.depth;

                queue.offer(rightChild);
            }
        }
    }

    private BSTNode<E> findPredecessor(BSTNode<E> node) {

        BSTNode<E> current = node.left;
        BSTNode<E> parent = current.parent;

        while(current != null) {
            parent = current;
            current = current.right;
        }

        return parent;
    }

    private BSTNode<E> search(E value) {
        BSTNode<E> current = this.root;

        @SuppressWarnings("unchecked")
        Comparable<? super E> toCompare = (Comparable<? super E>) value;
        int resComp;

        while(true) {
            resComp = toCompare.compareTo(current.value);

            if(resComp < 0) current = current.left;
            else if(resComp > 0) current = current.right;
            else break;
        }

        return current;
    }

    public void inOrder() {
        inOrder(this.root);
    }

    public void inOrder(BSTNode<E> current) {
        if(current == null) return;

        inOrder(current.left);
        System.out.print(current.value + " ");
        inOrder(current.right);
    }

    public void preOrder() {
        preOrder(this.root);
    }

    public void preOrder(BSTNode<E> current) {
        if(current == null) return;

        System.out.print(current.value + " ");
        preOrder(current.left);
        preOrder(current.right);
    }

    public void postOrder() {
        postOrder(this.root);
    }

    public void postOrder(BSTNode<E> current) {
        if(current == null) return;

        postOrder(current.left);
        postOrder(current.right);
        System.out.print(current.value + " ");
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(isEmpty()) {
            sb.append("Tree is Empty.\n");
            return sb.toString();
        }

        int depth = 0;
        BSTNode<E> parent;
        BSTNode<E> current;

        Queue<BSTNode<E>> queue = new LinkedList<>();

        queue.offer(this.root);
        sb.append("depth " + depth + ": ");

        while(!queue.isEmpty()) {
            current = queue.poll();
            parent = current.parent;

            if(depth == current.depth) {
                if(current == root) {
                    sb.append(current.value + "(root)");    
                } else {
                    sb.append(current.value + "(" + parent.value + ") ");
                } 
            } else {
                sb.append("\ndepth " + ++depth + ": " + current.value + "(" + parent.value + ") ");
            }

            if(current.left != null) queue.offer(current.left);
            if(current.right != null) queue.offer(current.right);
        }

        return sb.toString();
    }

    public String arrayString() {
        
        StringBuilder sb = new StringBuilder();

        int maxIndex = (int) Math.pow(2, height + 1) - 1;
        int arrayLen = this.array.length;

        for(int index = 1; index <= Math.min(maxIndex, arrayLen - 1); index++) {
            if(this.array[index] != null) sb.append(index + ": " + array[index] + "\n");
        }

        return sb.toString();
    }
}
