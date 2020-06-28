package tree;

/**
 * Created by ziheng on 2019/7/2.
 */
public class RedBlackTree {

    private static final int COLOR_RED = 0;
    private static final int COLOR_BLACK = 1;

    class TreeNode {
        TreeNode left, right, parent;
        int key;
        String value;
        int color;

        public TreeNode(int key, String value) {
            this.key = key;
            this.value = value;
            this.color = COLOR_RED;
        }
    }

    private TreeNode root;

    public void show() {
        show(root);
    }

    // insert with recursion
    public void insertWithRecursion(int key, String value) {
        root = insert(root, key, value);
        root.color = COLOR_BLACK;
    }

    private TreeNode insert(TreeNode node, int key, String value) {
        if (node == null) {
            node = new TreeNode(key, value);
        }

        if (key < node.key) {
            node.left = insert(node.left, key, value);
            node.left.parent = node;
        } else if (key > node.key) {
            node.right = insert(node.right, key, value);
            node.right.parent = node;
        }

        if (!isRed(node.left) && isRed(node.right)) { // right right -> balance & left right -> left left
            node = leftRotate(node);
            int tmpColor = node.color;
            node.color = node.left.color;
            node.left.color = tmpColor;
        }

        if (isRed(node.left) && isRed(node.left.left)) { // left left -> balance & right left -> right right
            node = rightRotate(node);
            int tmpColor = node.color;
            node.color = node.right.color;
            node.right.color = tmpColor;
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColor(node);
        }

        return node;
    }


    // insertion w/o recursion
    public TreeNode insert(int key, String value) {
        TreeNode node = new TreeNode(key, value);

        TreeNode parentNode = null;
        TreeNode curIndexNode = root;

        while (curIndexNode != null) {
            parentNode = curIndexNode;

            if (key < curIndexNode.key) {
                curIndexNode = curIndexNode.left;
            } else {
                curIndexNode = curIndexNode.right;
            }
        }

        node.parent = parentNode;

        if (parentNode == null) {
            // root node
            root = node;
        } else {
            if (key < parentNode.key) {
                parentNode.left = node;
            } else {
                parentNode.right = node;
            }
        }

        // fix up the tree
        insertFixUp(node);

        return root;
    }

    private void insertFixUp(TreeNode node) {
        TreeNode parentNode, grandNode;
        while ((parentNode = node.parent) != null && node.parent.color == COLOR_RED) {
            grandNode = parentNode.parent;

            // parent is grand parent node's left child
            if (parentNode == grandNode.left) {
                TreeNode uncleNode = grandNode.right;

                // uncle is red
                if (isRed(uncleNode)) {
                    parentNode.color = COLOR_BLACK;
                    uncleNode.color = COLOR_BLACK;
                    grandNode.color = COLOR_RED;

                    node = grandNode;
                } else { // uncle is black
                    if (node == parentNode.right) { // left right
                        node = parentNode;
                        parentNode = leftRotate(parentNode);
                    }

                    // left left
                    parentNode.color = COLOR_BLACK;
                    grandNode.color = COLOR_RED;
                    rightRotate(grandNode);
                }
            } else { // parent is grand parent node's right child
                TreeNode uncleNode = grandNode.left;

                // uncle is red
                if (isRed(uncleNode)) {
                    parentNode.color = COLOR_BLACK;
                    uncleNode.color = COLOR_BLACK;
                    grandNode.color = COLOR_RED;

                    node = grandNode;
                } else { // uncle is black
                    if (node == parentNode.left) { // right left
                        node = parentNode;
                        parentNode = rightRotate(parentNode);
                    }

                    // right right
                    parentNode.color = COLOR_BLACK;
                    grandNode.color = COLOR_RED;
                    leftRotate(grandNode);
                }
            }

        }

        root.color = COLOR_BLACK;
    }


    public void deleteWithoutRecursion(int key) {
        TreeNode node;
        if ((node = search(root, key)) != null) {
            delete(node);
        }
    }


    private void delete(TreeNode node) {
        // 1.first perform bst delete
        TreeNode substitute, child, parent;

        // find substitute
        if (node.left != null && node.right != null) { // node has two children
            substitute = node.right; // substitute.value 是后继节点的值，真正要删的其实是substitute节点和node.value
            while (substitute.left != null) {
                substitute = substitute.left;
            }
        } else {
            substitute = node;
        }

        // find substitute child
        if (substitute.left != null) {
            child = substitute.left;
        } else {
            child = substitute.right;
        }

        // new parent
        if (child != null) {
            child.parent = substitute.parent;
            parent = child.parent;
        } else {
            parent = substitute.parent;
        }

        // fix substitute parent's child
        if (substitute.parent == null) {
            root = child;
        } else if (substitute == substitute.parent.left) {
            substitute.parent.left = child;
        } else {
            substitute.parent.right = child;
        }

        // change node's value
        if (substitute != node) {
            node.key = substitute.key;
            node.value = substitute.value;
        }

        if (substitute.color == COLOR_BLACK) {
            deleteFixUp(child, parent);
        }
    }

    private void deleteFix(TreeNode node) {
        TreeNode sibling;

        // node is black
        if (node != root) {
            if (node == node.parent.left) {
                sibling = node.parent.right;

                // sibling is red
                if (isRed(sibling)) {
                    leftRotate(node.parent);
                    return;
                }

                // sibling is black
                if (!isRed(sibling.left) && !isRed(sibling.right)) {
                    node.parent.color = COLOR_BLACK;
                    sibling.color = COLOR_RED;
                } else {
                    if (isRed(sibling.left)) { // rl
                        sibling.left.color = node.parent.color;
                        node.parent.color = COLOR_BLACK;
                        rightRotate(sibling);
                        leftRotate(node.parent);
                    } else { // rr
                        sibling.color = node.parent.color;
                        sibling.right.color = COLOR_BLACK;
                        leftRotate(node.parent);
                    }

                }
            } else {
                sibling = node.parent.left;

                // sibling is red
                if (isRed(sibling)) {
                    rightRotate(node.parent);
                    return;
                }

                // sibling is black
                if (!isRed(sibling.left) && !isRed(sibling.right)) {
                    node.parent.color = COLOR_BLACK;
                    sibling.color = COLOR_RED;
                } else {
                    if (isRed(sibling.right)) { // lr
                        sibling.right.color = node.parent.color;
                        node.parent.color = COLOR_BLACK;
                        leftRotate(sibling);
                        rightRotate(node.parent);
                    } else { // ll
                        sibling.color = node.parent.color;
                        sibling.left.color = COLOR_BLACK;
                        rightRotate(node.parent);
                    }

                }
            }
        }
    }


    // delete1 w/o recursion
    private void delete1(TreeNode node) {
        TreeNode child, parent;

        if (node.left != null && node.right != null) {
            TreeNode minRightNode = node.right;

            while (minRightNode.left != null) {
                minRightNode = minRightNode.left;
            }

            if (node.parent != null) {
                if (node == node.parent.left) {
                    node.parent.left = minRightNode;
                } else {
                    node.parent.right = minRightNode;
                }
            } else {
                // node is root
                root = minRightNode;
            }

            child = minRightNode.right;
            parent = minRightNode.parent;

            if (parent == node) {
                parent = minRightNode;
            } else {
                if (child != null) {
                    child.parent = parent;
                }
                parent.left = child;
                minRightNode.right = node.right;
                node.right.parent = minRightNode;
            }

            minRightNode.parent = node.parent;
            minRightNode.left = node.left;
            node.left.parent = minRightNode;

            if (minRightNode.color == COLOR_BLACK) {
                deleteFixUp(child, parent);
            }

            node = null;
            return;
        }

        // only have one child
        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        // update child's parent
        parent = node.parent;
        if (child != null) {
            child.parent = parent;
        }

        // update parent's child
        if (parent != null) {
            if (node == node.parent.left) {
                node.parent.left = child;
            } else {
                node.parent.right = child;
            }
        } else {
            root = child;
        }

        if (node.color == COLOR_BLACK) {
            deleteFixUp(child, parent);
        }
    }

    private void deleteFixUp(TreeNode node, TreeNode parent) {
        TreeNode sibling;

        while (!isRed(node) && node != root) {
            if (node == parent.left) { // left
                sibling = parent.right;

                if (isRed(sibling)) { // sibling is red
                    swapColor(sibling, parent);
                    leftRotate(parent);
                    sibling = parent.right; // find current sibling of node
                }

                // sibling is black
                if (!isRed(sibling.left) && !isRed(sibling.right)) { // two child nodes of sibling is black
                    sibling.color = COLOR_RED;
                    // travel up since
                    node = parent;
                    parent = node.parent;
                } else {
                    if (!isRed(sibling.right)) {
                        swapColor(sibling.left, sibling);
                        rightRotate(sibling);
                        sibling = parent.right;
                    }

                    sibling.color = parent.color;
                    parent.color = COLOR_BLACK;
                    if (sibling.right != null) {
                        sibling.right.color = COLOR_BLACK;
                    }
                    leftRotate(parent);
                    node = root;
                    break;
                }
            } else {
                sibling = parent.left;

                if (isRed(sibling)) { // sibling is red
                    swapColor(sibling, parent);
                    rightRotate(parent);
                    sibling = parent.left;
                }

                // sibling is black
                if (!isRed(sibling.left) && !isRed(sibling.right)) { // two child nodes of sibling is black
                    sibling.color = COLOR_RED;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (!isRed(sibling.left)) {
                        swapColor(sibling.right, sibling);
                        rightRotate(sibling);
                        sibling = parent.left;
                    }

                    sibling.color = parent.color;
                    parent.color = COLOR_BLACK;
                    if (sibling.left != null) {
                        sibling.left.color = COLOR_BLACK;
                    }
                    rightRotate(parent);
                    node = root;
                    break;
                }
            }
        }

        if (node != null) {
            node.color = COLOR_BLACK;
        }
    }

    private TreeNode search(TreeNode node, int target) {
        if (node == null || node.key == target) return node;

        if (target < node.key) {
            return search(node.left, target);
        } else {
            return search(node.right, target);
        }
    }

    private boolean isRed(TreeNode node) {
        if (node == null) return false;
        return node.color == COLOR_RED;
    }

    private TreeNode leftRotate(TreeNode node) {
        TreeNode rightNode = node.right;
        node.right = rightNode.left;

        if (rightNode.left != null) {
            rightNode.left.parent = node;
        }

        if (node.parent == null) {
            this.root = rightNode;
        } else {
            if (node == node.parent.left) {
                node.parent.left = rightNode;
            } else {
                node.parent.right = rightNode;
            }
        }

        rightNode.left = node;

        rightNode.parent = node.parent;
        node.parent = rightNode;

        return rightNode;
    }

    private TreeNode rightRotate(TreeNode node) {
        TreeNode leftNode = node.left;
        node.left = leftNode.right;

        if (leftNode.right != null) {
            leftNode.right.parent = node;
        }

        if (node.parent == null) {
            this.root = leftNode;
        } else {
            if (node == node.parent.left) {
                node.parent.left = leftNode;
            } else {
                node.parent.right = leftNode;
            }
        }

        leftNode.right = node;

        leftNode.parent = node.parent;
        node.parent = leftNode;

        return leftNode;
    }

    private TreeNode flipColor(TreeNode node) {
        node.color = COLOR_RED;
        node.left.color = COLOR_BLACK;
        node.right.color = COLOR_BLACK;

        return node;
    }

    private void swapColor(TreeNode n1, TreeNode n2) {
        if (n1 != null && n2 != null) {
            int tmp = n1.color;
            n1.color = n2.color;
            n2.color = tmp;
        }
    }

    private void swap(TreeNode n1, TreeNode n2) {
        if (n1 != null && n2 != null) {
            TreeNode tmpNode;

            // swap parent
            if (n1 == n2.parent) {
                n2.parent = n1.parent;
                if (n1.parent != null) {
                    if (n1 == n1.parent.left) {
                        n1.parent.left = n2;
                    } else {
                        n1.parent.right = n2;
                    }
                }

                if (n2.left != null) {
                    n2.left.parent = n1;
                }

                if (n2.right != null) {
                    n2.right.parent = n1;
                }

                if (n2 == n1.left) {
                    n1.left = n2.left;
                    n2.left = n1;
                } else {
                    n1.right = n2.right;
                    n2.right = n1;
                }

                // swap child's parent
                n1.parent = n2;
            } else if (n2 == n1.parent) {
                n1.parent = n2.parent;
                if (n2.parent != null) {
                    if (n2 == n2.parent.left) {
                        n2.parent.left = n1;
                    } else {
                        n2.parent.right = n1;
                    }
                }

                if (n1.left != null) {
                    n1.left.parent = n2;
                }

                if (n1.right != null) {
                    n1.right.parent = n2;
                }

                if (n1 == n2.left) {
                    n2.left = n1.left;
                    n1.left = n2;
                } else {
                    n2.right = n1.right;
                    n1.right = n1;
                }

                n2.parent = n1;
            } else {
                tmpNode = n1.parent;
                n1.parent = n2.parent;
                if (tmpNode != null) {
                    if (n1 == tmpNode.left) {
                        tmpNode.left = n2;
                    } else {
                        tmpNode.right = n2;
                    }
                }

                if (n2.parent != null) {
                    if (n2 == n2.parent.left) {
                        n2.parent.left = n1;
                    } else {
                        n2.parent.right = n1;
                    }
                }
                n2.parent = tmpNode;

                // swap left
                tmpNode = n1.left;
                n1.left = n2.left;
                if (n2.left != null) { // let n2 left child's parent point to n1
                    n2.left.parent = n1;
                }

                n2.left = tmpNode;
                if (tmpNode != null) {
                    tmpNode.parent = n2; // let n1 left child's parent point to n2
                }

                // swap right
                tmpNode = n1.right;
                n1.right = n2.right;
                if (n2.right != null) {
                    n2.right.parent = n1;
                }

                n2.right = tmpNode;
                if (tmpNode != null) {
                    tmpNode.right = n2;
                }
            }

        }
    }

    private void show(TreeNode node) {
        if (node == null) return;
        if (node.left != null || node.right != null) {
            if (node.left != null) {
                int key1 = node.left.key;
                int color = node.left.color;

                System.out.println("left: " + "color: " + color + "-->" + key1 + "  --父节点-->  " + node.color + "  "
                        + node.value);
                show(node.left);
            }

            if (node.right != null) {
                int key2 = node.right.key;
                int color = node.right.color;

                System.out.println("right: " + "color: " + color + "-->" + key2 + "  --父节点-->  " + node.color + "  "
                        + node.value);
                show(node.right);
            }
        }
    }


    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        RedBlackTree redBlackTree1 = new RedBlackTree();

        int[] nodeValue = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        for (int i = 0; i < nodeValue.length; i++) {
            redBlackTree.insert(nodeValue[i], String.valueOf(nodeValue[i]));
//            redBlackTree1.insertWithRecursion(nodeValue[i], String.valueOf(nodeValue[i]));
        }

        redBlackTree.show();
        System.out.println("=============================");
//        redBlackTree1.show();

        for (int i = 0; i < nodeValue.length; i++) {
            redBlackTree.deleteWithoutRecursion(nodeValue[i]);
            redBlackTree.show();
            System.out.println("=============================");
        }

    }
}
