package com.leecode.result2020;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wuyiliang
 */
public class Leetcode0328 {

    /**
     *  https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
     * @param nums 数组
     * @return 数组中任意一个重复的数字
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            int old = set.size();
            set.add(num);
            int now = set.size();
            if (old == now) {
                return num;
            }
        }
        return 1;
    }

    /**
     *  https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
     * @param matrix 二维数组
     * @param target 目标
     * @return 数组中是否含有目标整数
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int a = matrix.length - 1;
        int b = 0;

        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        while (a >= 0 && b < matrix[0].length) {
            if (target == matrix[a][b]) {
                return true;
            }

            if (target > matrix[a][b]) {
                b++;
            } else if (target < matrix[a][b]) {
                a--;
            }
        }

        return false;
    }

    /**
     *  https://leetcode-cn.com/problems/combination-sum-ii/
     * @param candidates 数组
     * @param target 目标数
     * @return 数组中所有可以使数字和为目标数的组合
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();

        return lists;
    }
}
