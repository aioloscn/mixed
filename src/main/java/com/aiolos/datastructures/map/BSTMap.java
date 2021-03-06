package com.aiolos.datastructures.map;

/**
 * Created by aiolos on 2018-11-23.
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {

        public K key;
        public V value;
        public Node left, right;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

    }
    private Node root;

    private int size;
    public BSTMap() {
        root = null;
        size = 0;
    }

    /**
     * 向二分搜索树中添加新的键值对
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        add(root, key, value);
    }

    /**
     * 向以node为根的二分搜索树中插入元素(key, value)，递归算法
     * 返回插入新节点后二分搜索树的根
     * @param node
     * @param key
     * @param value
     */
    private Node add(Node node, K key, V value) {

        if (node == null) {
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;
        return node;
    }

    /**
     * 从二分搜索树中删除键为key的节点，返回value
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除掉以node为根的二分搜索树中键为key的节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node, K key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 待删除的节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            // 待删除的节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // 待删除的节点左右子树都不为空
            // 找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);   // 找到node右子树的最小节点
            // 如果successor有右子树，已经在removeMin()中赋值给之前successor的位置，也就是node.left
            // 然后将待删除的右子树赋值给successor的右子树，从而用successor替代待删除节点
            successor.right = removeMin(node.right);
            successor.left = node.left; // 将待删除节点的左子树赋值给successor的左子树，successor完全取代了待删除节点
            node.left = node.right = null;  // 删除节点
            return successor;   // successor没有父亲节点，返回给上一层关联
        }
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回yinode为根节点的二分搜索树中，key所在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key) {

        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        else
            return node;
    }
}
