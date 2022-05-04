import java.io.FileWriter;
import java.io.IOException;

public class AVLTree<T extends Comparable<T>> {
    private int treeSize;
    private Node root;


    public AVLTree() {
        treeSize = 0;
    }

    public int height(Node node) {
        if (node == null) return 0;
        return node.getHeight();
    }

    public int treeHeight() {
        return root.getHeight();
    }

    public int size() {
        return treeSize;
    }

    public boolean isEmpty() {
        return treeSize == 0;
    }

    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node<T> node, T value) {
        if(node == null) return false;

        if (value.compareTo(node.getValue()) < 0)
            return contains(node.left, value);
        else if (value.compareTo(node.getValue()) > 0)
            return contains(node.right, value);
        return true;

    }

    private void update(Node node) {
        int heightOfLeftNode = (node.left == null) ? -1 : node.left.getHeight();
        int heightOfRightNode = (node.right == null) ? -1 : node.right.getHeight();

        node.setHeight(1 + Math.max(heightOfLeftNode, heightOfRightNode));
        node.setBalanceFactor(heightOfLeftNode - heightOfRightNode);
    }

    private Node rightRotation(Node x) {
        Node y = x.left;
        Node z = y.right;
        x.left = z;
        y.right = x;

        update(x);
        update(y);

        return y;
    }

    private Node leftRotation(Node x) {
        Node y = x.right;
        Node z = y.left;
        x.right = z;
        y.left = x;

        update(x);
        update(y);

        return y;
    }

    private Node leftLeftCase(Node node) {
        return rightRotation(node);
    }

    private Node rightRightCase(Node node) {
        return leftRotation(node);
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node rebalance(Node node) {
        if (node.getBalanceFactor() == 2) {
            if (node.left.getBalanceFactor() >= 0)
                return leftLeftCase(node);
            else
                return leftRightCase(node);
        }
        else if (node.getBalanceFactor() == -2) {
            if(node.right.getBalanceFactor() <= 0)
                return rightRightCase(node);
            else
                return rightLeftCase(node);

        }
        return node;
    }

    private Node minNode(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public boolean insert(T value) {
        if(!contains(value)) {
            root = insert(root, value);
            treeSize++;
            return true;
        }
        return false;
    }

    private Node insert(Node<T> node, T value) {
        // if no root create it
        if(node == null) return new Node<T>(value);

        if(value.compareTo(node.getValue()) < 0)
            node.left = insert(node.left, value);
        else
            node.right = insert(node.right, value);

        update(node);
        return rebalance(node);
    }

    public boolean remove(T value) {
        if(contains(value)) {
            root = remove(root, value);
            treeSize--;
            return true;
        }
        return false;
    }

    private Node remove(Node<T> node, T value) {
        if (node == null) return null;

        if (value.compareTo(node.getValue()) < 0)
            node.left = remove(node.left, value);
        else if (value.compareTo(node.getValue()) > 0)
            node.right = remove(node.right, value);
        else {
            if (node.left == null || node.right == null) {

                Node replacingNode = null;
                if (node.left != null)
                    replacingNode = node.left;
                else if (node.right != null)
                    replacingNode = node.right;

                if(replacingNode != null)
                    node = replacingNode;
                else{
                    node = null;
                }
            }
            else {
                Node<T> replacingNode = minNode(node.right);
                node.setValue(replacingNode.getValue());
                node.right = remove(node.right, replacingNode.getValue());
            }
        }
        if (node == null)
            return null;

        update(node);
        return rebalance(node);

    }

    public void writeWordsInOrderTraversal(FileWriter fileWriter) throws IOException {
        writeWordsInOrderTraversal(fileWriter, root);
    }

    private void writeWordsInOrderTraversal(FileWriter fileWriter, Node node) throws IOException {
        if (node == null)
            return;
        writeWordsInOrderTraversal(fileWriter, node.left);
        fileWriter.write(node.getValue() + "\n");
        writeWordsInOrderTraversal(fileWriter, node.right);
    }
}
