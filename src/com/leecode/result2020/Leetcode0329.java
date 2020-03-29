package com.leecode.result2020;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author wuyiliang
 */
public class Leetcode0329 {

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
}
