package com.leecode.result2020;

import java.util.*;

/**
 * @author wuyiliang
 */
public class Leetcode03 {

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
     *  https://leetcode-cn.com/problems/maximum-subarray/
     * @param nums 整数数组
     * @return 连续子数组的最大和
     */
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int result = nums[0];

        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            result = Math.max(sum, result);
        }

        return result;
    }

    /**
     *  https://leetcode-cn.com/problems/as-far-from-land-as-possible/
     *  你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。
     *  其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？
     *  请返回该海洋区域到离它最近的陆地区域的距离。
     */
    public int maxDistance(int[][] grid) {
        Queue<int[]> land = new ArrayDeque<>();
        boolean hasOcen = false;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    land.offer(new int[] {i,j});
                } else {
                    hasOcen = true;
                }
            }
        }

        if (land.isEmpty() || !hasOcen) {
            return -1;
        }

        int[] poll = null;
        while (!land.isEmpty()) {
            poll = land.poll();

            int x1 = poll[0] + 1;
            int y1 = poll[1];
            if (x1 >= 0 && x1 < grid.length && y1 >= 0 && y1 < grid.length && grid[x1][y1] == 0) {
                grid[x1][y1] = grid[poll[0]][poll[1]] + 1;
                land.offer(new int[]{x1, y1});
            }

            int x2 = poll[0];
            int y2 = poll[1] + 1;
            if (x2 >= 0 && x2 < grid.length && y2 >= 0 && y2 < grid.length && grid[x2][y2] == 0) {
                grid[x2][y2] = grid[poll[0]][poll[1]] + 1;
                land.offer(new int[]{x2, y2});
            }

            int x3 = poll[0] - 1;
            int y3 = poll[1];
            if (x3 >= 0 && x3 < grid.length && y3 >= 0 && y3 < grid.length && grid[x3][y3] == 0) {
                grid[x3][y3] = grid[poll[0]][poll[1]] + 1;
                land.offer(new int[]{x3, y3});
            }

            int x4 = poll[0];
            int y4 = poll[1] - 1;
            if (x4 >= 0 && x4 < grid.length && y4 >= 0 && y4 < grid.length && grid[x4][y4] == 0) {
                grid[x4][y4] = grid[poll[0]][poll[1]] + 1;
                land.offer(new int[]{x4, y4});
            }
        }

        return grid[poll[0]][poll[1]] - 1;
    }

    /**
     *  https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
     *  给定一个二叉树，原地将它展开为链表。
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = null;

            TreeNode lastRight = root.right;
            while (lastRight.right != null) {
                lastRight = lastRight.right;
            }
            lastRight.right = temp;
        }

        if (root.right != null) {
            flatten(root.right);
        }
    }

    // TODO 未完
    /**
     *  https://leetcode-cn.com/problems/three-steps-problem-lcci/
     *  三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。
     *  实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     */
    public int waysToStep(int n) {
        return 1;
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
