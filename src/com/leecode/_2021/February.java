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

    /**
     * https://leetcode-cn.com/problems/transpose-matrix/
     */
    public int[][] transpose(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[j][i];
            }
        }

        return result;
    }
}