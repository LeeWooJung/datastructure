package treeStructure;

import treeStructure.Tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) throws Exception {


        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        int A = 4;
        int B = 2;
        int C = 9;
        int D = 1;
        int E = 3;
        int F = 6;
        int G = 11;
        int H = 5;
        int I = 8;
        int J = 10;
        int K = 12;
        int L = 7;

        bst.add(A);
        bst.add(B);
        bst.add(C);
        bst.add(D);
        bst.add(E);
        bst.add(F);
        bst.add(G);
        bst.add(H);
        bst.add(I);
        bst.add(J);
        bst.add(K);
        bst.add(L);


        System.out.println("-----------------------------------");

        System.out.println("InOrder :");
        bst.inOrder(); System.out.println("\n-----------------------------------");

        System.out.println("PreOrder :");
        bst.preOrder(); System.out.println("\n-----------------------------------");

        System.out.println("PostOrder :");
        bst.postOrder(); System.out.println("\n-----------------------------------");
        System.out.println(bst.toString());
        System.out.println("-----------------------------------");
        System.out.println(bst.arrayString());
        System.out.println("-----------------------------------");

        System.out.println("The number of nodes in Tree is : " + bst.size());
        System.out.println("Height of the Tree is : " + bst.getHeight());
        System.out.println("-----------------------------------");

        int value = 25;
        if(bst.contain(value)) {
            System.out.println("Tree has the node of which value is " + value);
        } else {
            System.out.println("Tree doesn't have the node of which value is " + value);
        }
        System.out.println("-----------------------------------");

        if(bst.isEmpty()) {
            System.out.println("Tree is empty!");
        } else {
            System.out.println("Tree is not empty!");
        }
        System.out.println("-----------------------------------");

        System.out.println("Remove Degree 0 node.");
        if(bst.remove(D)) {
            System.out.println("The value " + D + " is successfully removed");
        }
        System.out.println(bst.toString());
        System.out.println("The number of nodes in Tree is : " + bst.size());
        System.out.println("Height of the Tree is : " + bst.getHeight());
        System.out.println("-----------------------------------");
        bst.add(D);
        System.out.println(bst.toString());


        System.out.println("-----------------------------------");
        System.out.println("Remove Degree 1 node.");
        if(bst.remove(I)) {
            System.out.println("The value " + I + " is successfully removed");
        }
        System.out.println(bst.toString());
        System.out.println("The number of nodes in Tree is : " + bst.size());
        System.out.println("Height of the Tree is : " + bst.getHeight());
        System.out.println("-----------------------------------");
        bst.add(I);
        System.out.println(bst.toString());

        System.out.println("-----------------------------------");
        System.out.println("Remove Degree 2 node.");
        if(bst.remove(C)) {
            System.out.println("The value " + C + " is successfully removed");
        }
        System.out.println(bst.toString());
        System.out.println("The number of nodes in Tree is : " + bst.size());
        System.out.println("Height of the Tree is : " + bst.getHeight());

    }
}
