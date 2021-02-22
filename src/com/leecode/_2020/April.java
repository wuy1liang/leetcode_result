package com.leecode._2020;

import java.util.*;

/**
 * @author wuyiliang
 */
public class April {

    /**
     *  https://leetcode-cn.com/problems/game-of-life/
     */
    public void gameOfLife(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];

        int[] n1 = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] n2 = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, board[0].length);
        }

        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {

                int sum = 0;
                for (int k = 0; k < 8; k++) {
                    int row = i + n1[k];
                    int col = j + n2[k];
                    if (row >=0 && row < copy.length && col >=0 && col < copy[0].length && copy[row][col] == 1) {
                        sum++;
                    }
                }

                if (board[i][j] == 1) {
                    if (sum < 2 || sum > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (sum == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    /**
     *  https://leetcode-cn.com/problems/string-to-integer-atoi/
     *
     */
    public int myAtoi(String str) {
        char[] chars = str.trim().toCharArray();

        if (chars.length == 0) {
            return 0;
        }
        boolean flag = false;
        int start = 0;

        if (chars[0] == '+') {
            start++;
        } else if (chars[0] == '-'){
            start++;
            flag = true;
        } else if (!Character.isDigit(chars[0])) {
            return 0;
        }

        int result = 0;
        while (start < chars.length && Character.isDigit(chars[start])) {
            int num = chars[start] - '0';

            if (result > (Integer.MAX_VALUE - num) / 10) {
                return flag? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            result = result * 10 + num;
            start++;
        }

        if (flag) {
            result = -result;
        }

        return result;
    }

    /**
     *  https://leetcode-cn.com/problems/trapping-rain-water/
     */
    public int trap(int[] height) {
        int result = 0;
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];

        for (int i = 1;i < height.length - 1;i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i -1]);
        }

        for (int i = height.length -2;i >= 0;i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(maxLeft[i], maxRight[i]);
            if (min > height[i]) {
                result = result + min - height[i];
            }
        }

        return result;
    }

    /**
     * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
     */
    public int reversePairs(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * https://leetcode-cn.com/problems/summary-ranges/
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int num : nums) {
            if (deque.size() == 0) {
                deque.add(num);
                continue;
            }

            if (num == deque.peekLast() + 1) {
                deque.add(num);
            } else {
                if (deque.size() == 1) {
                    list.add(String.valueOf(deque.peek()));
                } else {
                    list.add(String.valueOf(deque.peekFirst()) + "->" + String.valueOf(deque.peekLast()));
                }
                deque.clear();
                deque.add(num);
            }
        }

        if (deque.size() == 1) {
            list.add(String.valueOf(deque.peek()));
        } else if (deque.size() > 1) {
            list.add(String.valueOf(deque.peekFirst()) + "->" + String.valueOf(deque.peekLast()));
        }

        return list;
    }

    /**
     * https://leetcode-cn.com/problems/search-a-2d-matrix/
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1;
        if (matrix.length == 0) {
            return false;
        }
        int j = 0;

        while (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }

        return false;
    }
    
}
