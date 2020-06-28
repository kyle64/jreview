package tree;

/**
 * Created by ziheng on 2019/6/26.
 */
public class AVLTree {
    class TreeNode {
        TreeNode left, right, parent;
        int value;
        int N; // number of children
        int height;

        TreeNode() {
        }

        TreeNode(int value) {
            this.value = value;
        }
    }

    private TreeNode root;


    public void insert(int value) {
        root = insert(root, value);
    }

    private TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            node = new TreeNode(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        }

        node.N = calculateSize(node);
        node.height = calculateHeight(node);

        // left child is too heavy
        if (getBalance(node) > 1) {
            if (value < node.left.value) {
                // left left
                node = rightRotate(node);
            } else if (value > node.left.value) {
                // left right
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        } else if (getBalance(node) < -1) {
            // right child is too heavy

            if (value > node.right.value) {
                // right right
                node = leftRotate(node);
            } else if (value < node.right.value) {
                // right left
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }

        return node;
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.value);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private TreeNode delete(TreeNode node, int value) {
        // delete1 node
        if (node == null) return null;

        if (value < node.value) {
            node.left = delete(node.left, value);
        } else if (value > node.value) {
            node.right = delete(node.right, value);
        } else {
            // node is leaf or node has only left/right child
            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                // node has both left child & right child
                TreeNode minRightNode = min(node.right);
                node.value = minRightNode.value;
                node.right = delete(node.right, minRightNode.value);
            }
        }

        node.N = calculateSize(node);
        node.height = calculateHeight(node);

        // check balance
        // left child is too heavy
        if (getBalance(node) > 1) {
            if (getBalance(node.left) >= 0) {
                // left left
                node = rightRotate(node);
            } else {
                // left right
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        } else if (height(node.left) - height(node.right) < -1) {
            // right child is too heavy

            if (getBalance(node.right) <= 0) {
                // right right
                node = leftRotate(node);
            } else {
                // right left
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }

        return node;
    }


    private int size(TreeNode node) {
        if (node == null) return 0;
        return node.N;
    }

    private int calculateSize(TreeNode node) {
        return size(node.left) + size(node.right) + 1;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        return node.height;
    }

    private int calculateHeight(TreeNode node) {
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private int getBalance(TreeNode node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private TreeNode leftRotate(TreeNode node) {
        TreeNode rightNode = node.right;

        // set rightNode's left sub tree as current node's right sub tree
        node.right = rightNode.left;

        // recalculate node size & height
        node.N = calculateSize(node);
        node.height = calculateHeight(node);

        // set current node as new root(sub tree)'s left sub tree
        rightNode.left = node;
        // recalculate node size & height
        rightNode.N = calculateSize(rightNode);
        rightNode.height = calculateHeight(rightNode);

        return rightNode;
    }

    private TreeNode rightRotate(TreeNode node) {
        TreeNode leftNode = node.left;
        node.left = leftNode.right;

        // recalculate node size & height
        node.N = calculateSize(node);
        node.height = calculateHeight(node);

        leftNode.right = node;
        // recalculate node size & height
        leftNode.N = calculateSize(leftNode);
        leftNode.height = calculateHeight(leftNode);

        return leftNode;
    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) return node;
        return min(node.left);
    }





    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(9);
        avlTree.insert(5);
        avlTree.insert(10);
        avlTree.insert(0);
        avlTree.insert(6);
        avlTree.insert(11);
        avlTree.insert(-1);
        avlTree.insert(1);
        avlTree.insert(2);

        avlTree.preOrder();
        System.out.println("=======================");

        avlTree.delete(10);
        avlTree.preOrder();
        System.out.println("=======================");

    }
}
