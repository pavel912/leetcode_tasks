package org.example;

import java.util.Stack;

public class BSTIterator {
    private final Stack<TreeNode> stack;
    private TreeNode current;

    public BSTIterator(TreeNode root) {
        current = root;
        stack = new Stack<>();
    }

    public int next() {
        while (current != null) {
            stack.add(current);
            current = current.left;
        }

        TreeNode node = stack.pop();
        current = node.right;

        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty() || current != null;
    }
}
