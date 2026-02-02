package org.example;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    // https://leetcode.com/problems/lru-cache/?envType=study-plan-v2&envId=top-interview-150

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int val, Node prev, Node next) {
            this.key = key;
            this.value = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private final Map<Integer, Node> index;
    private int size;
    private final int capacity;
    private final Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.index = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        this.head = new Node(0, 0, null, null);
        this.tail = head;
    }

    public int get(int key) {
        if (!index.containsKey(key)) return -1;

        Node node = index.get(key);

        moveToEnd(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (index.containsKey(key)) {
            Node node = index.get(key);
            node.value = value;

            moveToEnd(node);
            return;
        }

        if (size < capacity) {
            Node node = new Node(key, value, null, null);

            index.put(key, node);

            tail.next = node;
            node.prev = tail;
            tail = node;
            size++;
            return;
        }

        evict();

        put(key, value);
    }

    private void moveToEnd(Node node) {
        if (node != tail) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
    }

    private void evict() {
        Node del = head.next;
        index.remove(del.key);

        head.next = del.next;
        if (size > 1) del.next.prev = head;
        else tail = head;

        size--;
    }
}
