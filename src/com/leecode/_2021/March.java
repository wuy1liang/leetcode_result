package com.leecode._2021;

import com.leecode.common.ListNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author wuyiliang
 * @date 2021/3/8 16:24
 */
public class March {
    /**
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/
     */
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/palindrome-partitioning-ii/
     */
    public int minCut(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len];
        Arrays.fill(dp, len);
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            if (isPalindrome(s.substring(0, i + 1))) {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (isPalindrome(s.substring(j + 1, i + 1))) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }

    public boolean isPalindrome(String str) {
        if (str == null || str.length() <= 1) {
            return true;
        }

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list-ii/
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode start = new ListNode(0);
        return start;
    }

    /**
     * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> deque = new ArrayDeque<>(tokens.length);
        for (String token : tokens) {
            switch (token) {
                case "+":
                    deque.addLast(deque.removeLast() + deque.removeLast());
                    break;
                case "-":
                    deque.addLast(-(deque.removeLast() - deque.removeLast()));
                    break;
                case "*":
                    deque.addLast(deque.removeLast() * deque.removeLast());
                    break;
                case "/":
                    Integer b = deque.removeLast();
                    Integer a = deque.removeLast();
                    deque.addLast(a / b);
                    break;
                default:
                    deque.addLast(Integer.valueOf(token));
                    break;
            }
        }
        return deque.removeLast();
    }
}
