package tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by ziheng on 2019/6/26.
 */

public class BinarySearchTree {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        private int value;
        int N;

        TreeNode() {
        }

        TreeNode(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }


    private TreeNode root;

    public TreeNode search(int target) {
        return search(root, target);
    }

    public void insert(int input) {
        root = insert(root, input);
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    private TreeNode delete(TreeNode node, int key) {
        if (node == null) return node;

        if (key < node.getValue()) {
            node.left = delete(node.left, key);
        } else if (key > node.getValue()) {
            node.right = delete(node.right, key);
        } else {
            // node is leaf or node has only left/right child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            // node has both left child & right child
            TreeNode minRightNode = min(node.right);
            node.setValue(minRightNode.getValue());
            node.right = delete(node.right, minRightNode.getValue());
        }

        node.N = size(node.left) + size(node.right) + 1;

        return node;
    }


    public void preOrder() {
        preOrder(root);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void  postOrder() {
        postOrder(root);
    }

    private int size(TreeNode node) {
        if (node == null) return 0;
        return node.N;
    }

    private TreeNode search(TreeNode node, int target) {
        if (node == null || node.getValue() == target) return node;

        if (target < node.getValue()) {
            return search(node.left, target);
        } else {
            return search(node.right, target);
        }
    }

    private TreeNode insert(TreeNode node, int input) {
        if (node == null) {
            node = new TreeNode(input);
        } else if (input < node.getValue()) {
            node.left = insert(node.left, input);
        } else if (input > node.getValue()) {
            node.right = insert(node.right, input);
        }

        node.N = size(node.left) + size(node.right) + 1;

        return node;
    }

    private void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.getValue());
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.getValue());
            inOrder(node.right);
        }
    }

    private void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.getValue());
        }
    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    // 层序遍历(使用Queue + bfs，这里不能使用stack + dfs)
    private void levelOrderTraversal(TreeNode node) {
        if (node == null) return;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode popNode = queue.poll();
            System.out.println(popNode.getValue());

            if (popNode.left != null) {
                queue.add(popNode.left);
            }

            if (popNode.right != null) {
                queue.add(popNode.right);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        binarySearchTree.insert(3);
        binarySearchTree.insert(2);
        binarySearchTree.insert(1);
        binarySearchTree.insert(4);
        binarySearchTree.insert(6);
        binarySearchTree.insert(5);
        binarySearchTree.insert(7);

        System.out.println(111);

//        binarySearchTree.delete(3);
//        binarySearchTree.inOrder();
        binarySearchTree.levelOrderTraversal(binarySearchTree.root);
    }
}

