package com.leecode.result2020;

import java.util.Arrays;

/**
 * @author wuyiliang
 */
public class LeetCode05 {

    /**
     * https://leetcode-cn.com/problems/counting-bits/
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];

        if (num == 0) {
            return result;
        }

        for (int i = 1; i <= num; i++) {
            if (i % 2 == 1) {
                result[i] = result[i-1] + 1;
            } else {
                result[i] = result[i/2];
            }
        }

        return result;
    }

    /**
     *  https://leetcode-cn.com/problems/reducing-dishes/
     */
    public int maxSatisfaction(int[] satisfaction) {
        int result = 0;
        int sum = 0;
        Arrays.sort(satisfaction);
        for (int i = satisfaction.length - 1; i >= 0; i--) {
            if (sum + satisfaction[i] > 0) {
                sum += satisfaction[i];
                result += sum;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     *  https://leetcode-cn.com/problems/matrix-block-sum/
     */
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] prefix = new int[m+1][n+1];
        int[][] result = new int[m][n];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i-1][j-1] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int x1 = i - K - 1 >= 0? i - K - 1: 0;
                int y1 = j - K - 1 >= 0? j - K - 1: 0;
                int x2 = i + K <= m? i + K: m;
                int y2 = j + K <= n? j + K: n;
                result[i-1][j-1] = prefix[x2][y2] + prefix[x1][y1] - prefix[x2][y1] - prefix[x1][y2];
            }
        }

        return result;
    }

    /**
     *  https://leetcode-cn.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
     */
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] prefix = new int[m+1][n+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                prefix[i][j] = mat[i-1][j-1] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }

        int result = 0;
        int max = Math.min(m, n);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
               for (int k = result + 1; k <= max; k++) {
                   if (i + k -1 <= m && j + k - 1 <= n) {
                       int sum = prefix[i+k-1][j+k-1] + prefix[i-1][j-1] - prefix[i+k-1][j-1] - prefix[i-1][j+k-1];
                       if (sum <= threshold){
                           result++;
                           continue;
                       }
                   }
                   break;
               }
            }
        }
        return result;
    }

    /**
     *  https://leetcode-cn.com/problems/stone-game/
     */
    public boolean stoneGame(int[] piles) {
        //TODO
        return false;
    }
}
