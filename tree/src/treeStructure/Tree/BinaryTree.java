package treeStructure.Tree;

import treeStructure.Node.BinaryNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements tree<E>{
    
    private BinaryNode<E> root;
    private int size;
    private int height;
    private E[] array;
    
    @SuppressWarnings("unchecked")
    public BinaryTree() {
        this.root = null;
        this.size = 0;
        this.height = 0;
        this.array = (E[]) new Object[30];
    }

    public boolean addRoot(BinaryNode<E> node) {

        if(root != null) {
            System.out.println("There is root node in this Binary Tree");
            System.out.println("Root node value : " + root.value);
            return false;
        }

        node.index = 1;
        root = node;
        size++;

        array[root.index] = root.value;

        return true;
    }

    public boolean add(BinaryNode<E> parent, BinaryNode<E> node, String position) {

        // 1. position을 잘못 입력하는 경우 node를 추가할수 없다.
        if(!position.equals("left") && !position.equals("right")) {
            System.out.println("The position should be <left> or <right>");
            return false;
        }

        // 2. root 가 없을 경우 추가할 수 있는 트리가 아직 구성되지 않았다.
        if(root == null) {

            System.out.println("There is no root in this Binary Tree");
            System.out.println("Add Root node first");
            return false;
        }

        // 3. 부모 노드가 트리에 없을 경우 node를 추가할 수 없다.
        if(!Contain(parent)) {

            System.out.println("There is no parent node (value: " + parent.value + " ) in this Binary Tree." );
            System.out.println("Can't add node you choose");
            return false;
        }

        // 4. 선택한 부모 노드가 이미 자식이 두 개일경우 다른 자식노드를 추가할 수 없다.
        if(parent.degree() == 2) {
            
            System.out.println("The parent node has 2 children(FULL). So can't add this node");
            System.out.println("Choose another parent!");
            return false;
        }

        // 5. 왼쪽 자식 노드에 추가하고 싶으나, 이미 자식 노드가 있을 경우 노드를 추가할 수 없다.
        if(parent.left != null && position.equals("left")) {
            
            System.out.println("The parent node has left child (value : " + parent.left.value + " )");
            if(parent.right == null) {
                System.out.println("You can put this node on the right child or choose another parent!");
            } else {
                System.out.println("Choose another parent!");
            }
            return false;
        }

        // 5. 오른쪽 자식 노드에 추가하고 싶으나, 이미 자식 노드가 있을 경우 노드를 추가할 수 없다.
        if(parent.right != null && position.equals("right")) {
            
            System.out.println("The parent node has right child (value : " + parent.right.value + " )");
            if(parent.left == null) {
                System.out.println("You can put this node on the left child or choose another parent!");
            } else {
                System.out.println("Choose another parent!");
            }
            return false;
        }

        node.parent = parent;
        node.depth = parent.depth + 1;

        size++;
        if(node.depth > height) {
            height = node.depth;
        }
        if(position.equals("left")) {
            parent.left = node;
            node.index = parent.index * 2;
        } else {
            parent.right = node;
            node.index = parent.index * 2 + 1;
        }

        if(node.index >= array.length) {
            resize(node.index * 2);
        }
        array[node.index] = node.value;

        System.out.println("Successfully add! Parent node value : " + parent.value + ", " + position + " child node value : " + node.value);
        return true;
    }

    public boolean remove(E value) {

        BinaryNode<E> node = search(value);
        if(node == null) {
            System.out.println("There is no node having value : " + value + " in Tree");
            return false;
        }

        BinaryNode<E> parent = node.parent;

        if(node.degree() == 0) {
            
            if(parent == null) { // the node is root
            } else if(parent.left == node) {
                parent.left = null;
            } else if(parent.right == node) {
                parent.right = null;
            }
            array[node.index] = null;
            node = null;
        } else if(node.degree() == 1) {

            int currentIndex = node.index;
            int currDepth = node.depth;
            BinaryNode<E> child = null;
            String childPosition = "";

            if(node.left != null) {
                child = node.left;
                childPosition = "left";
            } else if(node.right != null) {
                child = node.right;
                childPosition = "right";
            }

            if(parent == null) { // node is root node
                root = child;
                child.parent = null;
                if(childPosition.equals("left")) {
                    node.left = null;
                } else if(childPosition.equals("right")) {
                    node.right = null;
                }
                
            } else {
                if(parent.left == node) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
                child.parent = parent;
                node.parent = null;
                if(childPosition.equals("left")) node.left = null;
                else if(childPosition.equals("right")) node.right = null;
            }
            node = null;
            ResetProperty(child, currentIndex, currDepth);
        } 

        size--;
        ResetHeight();

        System.out.println("The node value : " + value + " is removed");
        return true;
    }

    private void ResetProperty(BinaryNode<E> current, int toSetIndex, int toSetDepth) {
        if(current == null) return;

        array[current.index] = null;
        array[toSetIndex] = current.value;
        current.depth = toSetDepth;

        ResetProperty(current.left, toSetIndex * 2, current.depth + 1);
        ResetProperty(current.right, toSetIndex * 2 + 1, current.depth + 1);
    }

    private void ResetHeight() {

        if(isEmpty()) {
            this.height = 0;
            return;
        }

        int height = 0;
        Queue<BinaryNode<E>> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            BinaryNode<E> node = queue.poll();
            if(node.depth > height) height = node.depth;

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }

        this.height = height;
    }

    private BinaryNode<E> search(E value) {
        if(isEmpty()) return null;

        Queue<BinaryNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            BinaryNode<E> current = queue.poll();

            if(current.value == value) {
                queue.clear();
                return current;
            }

            if(current.degree() == 0) continue;
            if(current.left != null) queue.offer(current.left);
            if(current.right != null) queue.offer(current.right);
        }

        return null;
    }

    public boolean Contain(BinaryNode<E> node) {

        if(root == null) {
            return false;
        }

        Queue<BinaryNode<E>> queue = new LinkedList<>();

        queue.offer(root);

        while(!queue.isEmpty()) {
            BinaryNode<E> current = queue.poll();
            if(current.value == node.value) {
                queue.clear();
                return true;
            }
            if(current.degree() == 0) continue;
            if(current.left != null) queue.add(current.left);
            if(current.right != null) queue.add(current.right);
        }

        return false;
    }

    /*
     * Full Binary Tree (전 이진트리)
     * 모든 노드가 0개 또는 2개의 자식 노드를 갖는 트리
     */
    public boolean isFullBinaryTree() {

        if(isEmpty()) return false;

        int degree;
        Queue<BinaryNode<E>> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()) {
            BinaryNode<E> current = queue.poll();
            degree = current.degree();
            if(degree != 0 && degree != 2) {
                System.out.println("This tree is not Full Binary Tree");
                System.out.println("Node " + current.value + " has one child node");
                queue.clear();
                return false;
            }

            if(current.left != null) {
                queue.offer(current.left);
            }
            if(current.right != null) {
                queue.offer(current.right);
            }
        }

        System.out.println("This tree is Full Binary Tree!");
        return true;
    }

    /*
     * Complete Binary Tree
     * 마지막 레벨을 제외하고 모든 레벨이 완전히 채워져 있는 트리
     * 마지막 레벨은 꽉 차 있지 않아도 되지만 노드가 왼쪽에서 부터 차례대로 채워져야 함
     * - array가 순서대로 차있다면 Complete Binary Tree라고 할 수 있음.
     */
    public boolean isCompleteBinaryTree() {

        if(isEmpty()) {
            System.out.println("There is no node in Tree");
            System.out.println("So this tree is not Complete Binary Tree");
            return false;
        }

        for(int i = 1; i <= size; i++) {
            E value = array[i];
            if(value == null) {
                System.out.println("This tree is not Complete Binary Tree");
                return false;
            }
        }

        System.out.println("This tree is Complete Binary Tree!");

        return true;
    }

    /*
     * Perfect Binary Tree
     * 마지막 레벨의 노드 이외의 모든 노드의 degree가 2인 트리
     */
    public boolean isPerfectBinaryTree() {

        if(isEmpty()) return false;

        int degree;
        int depth;

        Queue<BinaryNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {

            BinaryNode<E> current = queue.poll();

            degree = current.degree();
            depth = current.depth;

            if(depth == height) continue;

            if(depth != height && degree != 2) {

                System.out.println("This tree is not Perfect Tree!");
                System.out.println("Node value : " + current.value + " has degree : " + degree);

                queue.clear();
                return false;
            }

            // 위 과정을 통과한 노드는 무조건 degree가 2이므로 자식 노드들을 queue에 삽입
            queue.offer(current.left);
            queue.offer(current.right);
        }

        System.out.println("This tree is Perfect Binary Tree!");
        return true;
    }

    public boolean isPerfectBinaryTree2() {
        if(isEmpty()) return false;

        int level = this.height;
        int numNode = this.size;

        if(numNode != Math.pow(2, level+1) -1) {
            System.out.println("This tree is not Perfect Binary Tree");
            return false;
        }

        for(int i = 1; i <= numNode; i++) {
            E value = array[i];
            if(value == null) {
                System.out.println("This tree is not Perfect Binary Tree");
                return false;
            }
        }
        System.out.println("This tree is Perfect Binary Tree!");
        return true;
    }

    /*
     * Balanced Binary Tree
     * 모든 노드의 왼쪽 서브트리와 오른쪽 서브트리의 높이(height)
     * 차이의 절대값이 1 이하인 트리.
     */
    public boolean isBalancedBinaryTree() {

        if(isEmpty()) {
            System.out.println("This tree is Empty!");
            return false;
        }

        if(subtreeHeight(root) == -1) {
            System.out.println("This Tree is NOT Balanced Tree");
            return false;
        } else {
            System.out.println("This Tree is Balanced Tree");
            return true;
        }
    }

    /*
     * @function    subtreeHeight
     * @param       current
     * 
     * @return      Height of subtree where the current node is root node
     *              -1 if this subtree is not balanced
     * 
     * - 재귀함수
     * - 1) 왼쪽 노드를 루트 노드로 하는 서브트리를 탐색하면서 height를 구한다.
     *      만약 서브트리가 balanced tree가 아니면 -1을 return 한다.
     * - 2) 오른쪽 노드를 루트 노드로 하는 서브트리를 탐색하면서 height를 구한다.
     *      만약 서브트리가 balanced tree가 아니면 -1을 return 한다.
     * - 3) 양 쪽 서브트리가 balanced tree라면 둘 중 큰 height + 1을 자신의 height로 return 한다.
     */
    private int subtreeHeight(BinaryNode<E> current) {

        if(current == null) {
            return 0;
        }

        int leftSubtreeHeight = subtreeHeight(current.left);
        if(leftSubtreeHeight == -1) {
            System.out.println("Subtree where the root node value is : " + current.left.value + " is not balanced");
            return -1;
        }

        int rightSubtreeHeight = subtreeHeight(current.right);
        if(rightSubtreeHeight == -1) {
            System.out.println("Subtree where the root node value is : " + current.right.value + " is not balanced");
            return -1;
        }

        if(Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1) {
            return -1;
        }

        return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
    }

    public void inorder(BinaryNode<E> current) {
        if(current == null) return;

        inorder(current.left);
        System.out.print(current.value + " ");
        inorder(current.right);
    }

    public void preorder(BinaryNode<E> current) {
        if(current == null) return ;

        System.out.print(current.value + " ");
        preorder(current.left);
        preorder(current.right);
    }

    public void postorder(BinaryNode<E> current) {
        if(current == null) return ;

        postorder(current.left);
        postorder(current.right);
        System.out.print(current.value + " ");
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {

        E[] newArray = (E[]) new Object[newCapacity];

        for(int i = 1; i <= size; i++) {
            newArray[i] = array[i];
        }

        this.array = null;
        this.array = newArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(isEmpty()) {
            sb.append("Tree is Empty!");
            return sb.toString();
        }

        int depth = 0;
        Queue<BinaryNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        sb.append("depth " + depth + ": ");

        while(!queue.isEmpty()) {
            BinaryNode<E> current = queue.poll();
            BinaryNode<E> parent = current.parent;
            E value = current.value;
            if(depth == current.depth) {
                if(root == current) {
                    sb.append(value + "(root)" +" ");
                } else {
                    sb.append(value + "(" + current.parent.value);
                    if(parent.left != null && parent.left == current) {
                        sb.append("-left) ");
                    } else if(parent.right != null && parent.right == current) {
                        sb.append("-right) ");
                    }
                }
            } else {
                depth++;
                sb.append("\ndepth " + depth + ": " + value + "(" + current.parent.value);
                if(parent.left != null && parent.left == current) {
                    sb.append("-left) ");
                } else if(parent.right != null && parent.right == current) {
                    sb.append("-right) ");
                }
            }

            if(current.left != null) {
                queue.offer(current.left);
            }
            if(current.right != null) {
                queue.offer(current.right);
            }
        }


        return sb.toString();
    }

    public String arrayString() {
        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < array.length; idx++) {
            if(array[idx] != null) {
                sb.append(idx + ": " + array[idx] + "\n");
            }
        }
        return sb.toString();
    }

    public BinaryNode<E> getRoot() {
        return root;
    }

    public int getHeight() {
        return height;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}