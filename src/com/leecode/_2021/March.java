package com.leecode._2021;

import com.leecode.common.ListNode;

import java.util.Arrays;

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
        start.next = head;
        ListNode pre = start;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        head = pre.next;
        for(int i = left; i < right; i++){
            ListNode nex = head.next;
            head.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
        }
        return start.next;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list/
     */
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }

    /**
     * https://leetcode-cn.com/problems/design-parking-system/
     */
    class ParkingSystem {
        int[] car;
        public ParkingSystem(int big, int medium, int small) {
            this.car = new int[]{big, medium, small};
        }
        public boolean addCar(int carType) {
            if (car[carType - 1] > 0) {
                car[carType - 1]--;
                return true;
            } else {
                return false;
            }
        }
    }
}
