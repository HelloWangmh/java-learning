package wang.mh.algorithm;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class TreeDemo {


    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     */
    public boolean isBalanced(TreeNode root) {
        return root == null || Math.abs(maxDepth(root.left) - maxDepth(root.right)) < 2
                && isBalanced(root.left) && isBalanced(root.right);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     *给定一个二叉树，返回它的中序 遍历。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderRecursion(list, root);
        return list;
    }

    public void inorderRecursion(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        inorderRecursion(list, root.left);
        list.add(root.val);
        inorderRecursion(list, root.right);
    }



}




class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
