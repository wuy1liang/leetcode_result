package com.leecode._2021;

/**
 * @author wuyiliang
 * @date 2021/2/22 11:34
 */
public class February {

    /**
     * https://leetcode-cn.com/problems/toeplitz-matrix/
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }

        return true;
    }
}