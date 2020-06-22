package com.leecode.result2020;

/**
 * @author wuyiliang
 */
public class LeetCode06 {

    /**
     *  https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
     */
    public int[] spiralOrder(int[][] matrix) {
        int index = 0;
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int[] res = new int[matrix.length * matrix[0].length];
        int left = 0;
        int bot = 0;
        int right = matrix[0].length - 1;
        int top = matrix.length - 1;
        
        while (index < matrix.length * matrix[0].length) {
            for (int i = left; i <= right; i++) {
                res[index] = matrix[bot][i];
                index++;
            }
            bot++;
            if (index == matrix.length * matrix[0].length) {
                break;
            }

            for (int i = bot; i <= top; i++) {
                res[index] = matrix[i][right];
                index++;
            }
            right--;
            if (index == matrix.length * matrix[0].length) {
                break;
            }

            for (int i = right; i >= left; i--) {
                res[index] = matrix[top][i];
                index++;
            }
            top--;
            if (index == matrix.length * matrix[0].length) {
                break;
            }

            for (int i = top; i >= bot; i--) {
                res[index] = matrix[i][left];
                index++;
            }
            left++;
            if (index == matrix.length * matrix[0].length) {
                break;
            }
        }

        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/running-sum-of-1d-array/
     */
    public int[] runningSum(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i-1] + nums[i];
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/shuffle-the-array/
     */
    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[2*n];
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                res[i] = nums[i/2];
            } else {
                res[i] = nums[n + i/2];
            }
        }
        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
     */
    public int subtractProductAndSum(int n) {
        int sum1 = n % 10;
        int sum2 = n % 10;
        while (n/10 > 0) {
            n = n/10;
            sum1 *= n %10;
            sum2 += n % 10;
        }
        return sum1 - sum2;
    }

    /**
     *  https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/comments/
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int length = grid.length;
        int[] rows = new int[length];
        int[] cols = new int[length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                rows[i] = Math.max(rows[i], grid[i][j]);
                cols[j] = Math.max(cols[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                res += Math.min(cols[j], rows[i]) - grid[i][j];
            }
        }
        return res;
    }
}
