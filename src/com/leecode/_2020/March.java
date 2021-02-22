package com.leecode._2020;

import com.leecode.common.TreeNode;

import java.util.*;

/**
 * @author wuyiliang
 */
public class March {

    /**
     *  https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
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
}
