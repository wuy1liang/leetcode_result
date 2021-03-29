package com.leecode._2021;

import com.leecode.common.ListNode;
import com.leecode.common.NestedInteger;

import java.util.*;

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

    /**
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length);
        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.remove();
    }

    /**
     * https://leetcode-cn.com/problems/set-matrix-zeroes/
     */
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        byte[] row = new byte[n];
        byte[] col = new byte[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        for (int i = 0; i < row.length; i++) {
            if (row[i] == 1) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < col.length; i++) {
            if (col[i] == 1) {
                for (int j = 0; j < n; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/number-of-1-bits/
     */
    public int hammingWeight(int n) {
        int result = 0;
        while (n != 0) {
            n &= n - 1;
            result++;
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/flatten-nested-list-iterator/
     */
    public class NestedIterator implements Iterator<Integer> {

        Iterator<Integer> iterator;

        public NestedIterator(List<NestedInteger> nestedList) {
            List<Integer> list = new ArrayList<>();
            for (NestedInteger nestedInteger : nestedList) {
                addInteger(nestedInteger, list);
            }
            this.iterator = list.iterator();
        }

        private void addInteger(NestedInteger nestedInteger, List<Integer> list) {
            if (nestedInteger.isInteger()) {
                list.add(nestedInteger.getInteger());
            } else {
                for (NestedInteger nested : nestedInteger.getList()) {
                    addInteger(nested, list);
                }
            }
        }

        @Override
        public Integer next() {
            return this.iterator.next();
        }

        @Override
        public boolean hasNext() {
            return this.iterator.hasNext();
        }
    }

    /**
     * https://leetcode-cn.com/problems/132-pattern/
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Deque<Integer> d = new ArrayDeque<>();
        int k = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < k) {
                return true;
            }
            while (!d.isEmpty() && d.peekLast() < nums[i]) {
                k = d.pollLast();
            }
            d.addLast(nums[i]);
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode start = new ListNode();
        start.next = head;
        ListNode pre = start;
        ListNode cur = start.next;
        boolean flag = false;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur = cur.next;
                flag = true;
            } else {
                if (flag) {
                    pre.next = cur.next;
                    cur = cur.next;
                    flag = false;
                } else {
                    pre = cur;
                    cur = cur.next;
                }
            }
        }
        if (flag) {
            pre.next = null;
        }
        return start.next;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-bits/
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int t = (n >> i) & 1;
            if (t == 1) {
                result |= (1 << (31 - i));
            }
        }
        return result;
    }
}
