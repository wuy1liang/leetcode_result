package com.leecode._2021;

/**
 * @author wuyiliang
 * @date 2021/4/2 10:52
 */
public class April {

    /**
     * https://leetcode-cn.com/problems/volume-of-histogram-lcci/
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int result = 0;
        for (int i = 1; i < len - 1; i++) {
            int max = Math.min(rightMax[i], leftMax[i]);
            if (max > height[i]) {
                result += max - height[i];
            }
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        int left = 2;
        for (int i = 2; i < len; i++) {
            if (nums[left - 2] != nums[i]) {
                nums[left] = nums[i];
                left++;
            }
        }
        return left;
    }
}
