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
}
