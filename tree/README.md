# TREE <img src = "https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white">

* Explanation page

    - [Simple Tree](https://lunareclipse000.wordpress.com/2024/01/23/tree/)

    - [Binary Tree](https://lunareclipse000.wordpress.com/2024/01/26/binary-tree/)

    - [BinaryTree Implemantation 1](https://lunareclipse000.wordpress.com/2024/01/27/binary-tree-%ea%b5%ac%ed%98%84java/)

    - [BinaryTree Implemantation 2](https://lunareclipse000.wordpress.com/2024/01/27/binary-tree-%ea%b5%ac%ed%98%84java-2/)

    - [Binary Search Tree](https://lunareclipse000.wordpress.com/2024/02/03/binary-search-treejava/)

---------

:full_moon: Implementation of Tree Structure

---------

**📦src**  
 ┗ **📂treeStructure**  : package of tree structure.  
 ┃ ┣ **📂Node** : package of node.  
 ┃ ┃ ┣ **📜BinaryNode.java** : Implementation of Binary Tree's node. This node can have information of parent node, its depth, location(index) of the array that represent tree structure.  
 ┃ ┃ ┣ **📜BSTNode.java** : Implementation of Binary Search Tree's node. This node can have information of parent node, its depth, location(index) of the array that represent tree structure.  
 ┃ ┃ ┗ **📜Node.java** : Implementation of Simple Tree's node. This node may have many children and have information of its depth.  
 ┃ ┣ **📂Tree** : package of tree.  
 ┃ ┃ ┣ **📜BinarySearchTree.java** : Implementation of Binary Search Tree. This class have many methods like "add", "remove", "contain", "resize", "removeNode", "resetProperty", "findPredecessor", "search", "inOrder", "preOrder", "postOrder", "toString", etc.  
 ┃ ┃ ┣ **📜BinaryTree.java** : Implementation of Binary Tree. This class have many methods like "addRoot", "add", "remove(when node has degree 0 or 1)", "isFullBinaryTree", "isCompleteBinaryTree", "isPerfectBinaryTree", "isBalancedBinaryTree", etc.  
 ┃ ┃ ┣ **📜SimpleTree.java** : Implementation of Simple Tree. This class have uncomplicated methods like "add", "isEmpty", "size", etc.  
 ┃ ┃ ┣ **📜BST.java** : This is the interface of Binary Search Tree.  
 ┃ ┃ ┗ **📜tree.java** : This is the interface of tree.  
 ┃ ┗ **📜Main.java** : Use Binary Search Tree class to watch how it works.

 