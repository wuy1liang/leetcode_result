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
        //TODO
        return null;
    }
}
