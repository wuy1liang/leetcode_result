package com.leecode.result2020;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuyiliang
 */
public class LeetCode07 {

    /**
     *  https://leetcode-cn.com/problems/path-sum/
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        } else {
            return hasPathSum(root.left, sum - root.val)
                    || hasPathSum(root.right, sum - root.val);
        }
    }

    /**
     *  https://leetcode-cn.com/problems/different-ways-to-add-parentheses/
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            result.add(Integer.valueOf(input));
            return result;
        }
        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                for(Integer left : diffWaysToCompute(input.substring(0, i))) {
                    for (Integer right : diffWaysToCompute(input.substring(i + 1))) {
                        if (input.charAt(i) == '+') {
                            result.add(left + right);
                        } else if (input.charAt(i) == '-') {
                            result.add(left - right);
                        } else if (input.charAt(i) == '*') {
                            result.add(left * right);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     *  https://leetcode-cn.com/problems/diving-board-lcci/
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] res = new int[k + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (k - i) * shorter + i * longer;
        }
        return res;
    }
}
