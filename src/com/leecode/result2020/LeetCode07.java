package com.leecode.result2020;

import java.util.*;

/**
 * @author wuyiliang
 */
public class LeetCode07 {

    /**
     *  https://leetcode-cn.com/problems/path-sum/
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        } else {
            return hasPathSum(root.left, sum - root.val)
                    || hasPathSum(root.right, sum - root.val);
        }
    }

    /**
     *  https://leetcode-cn.com/problems/different-ways-to-add-parentheses/
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            result.add(Integer.valueOf(input));
            return result;
        }
        for(int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                for(Integer left : diffWaysToCompute(input.substring(0, i))) {
                    for (Integer right : diffWaysToCompute(input.substring(i + 1))) {
                        if (input.charAt(i) == '+') {
                            result.add(left + right);
                        } else if (input.charAt(i) == '-') {
                            result.add(left - right);
                        } else if (input.charAt(i) == '*') {
                            result.add(left * right);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     *  https://leetcode-cn.com/problems/diving-board-lcci/
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] res = new int[k + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (k - i) * shorter + i * longer;
        }
        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/re-space-lcci/solution/
     */
    public int respace(String[] dictionary, String sentence) {
        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
        int n = sentence.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int idx = 0; idx < i; idx++) {
                if (dict.contains(sentence.substring(idx, i))) {
                    dp[i] = Math.min(dp[i], dp[idx]);
                }
            }
        }
        return dp[n];
    }

    /**
     *  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            dp[i][1] = dp[i-1][0] + prices[i];
            dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1]);
        }

        return Math.max(dp[prices.length-1][1], dp[prices.length-1][2]);
    }

    /**
     * https://leetcode-cn.com/problems/unique-binary-search-trees/
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

    /**
     *  https://leetcode-cn.com/problems/delete-and-earn/
     */
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = Arrays.stream(nums).max().getAsInt();
        int[] count = new int[max + 1];
        for (int num : nums) {
            count[num]++;
        }
        int[] dp = new int[max+1];
        dp[1] = count[1];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + count[i] * i);
        }
        return dp[max];
    }

    /**
     *  https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int i : nums1) {
            map.put(i, map.containsKey(i)? map.get(i) + 1: 1);
        }
        List<Integer> list = new ArrayList<>(nums1.length);
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                list.add(i);
                map.put(i, map.get(i) - 1);
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    /**
     * https://leetcode-cn.com/problems/triangle/
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return triangle.get(0).get(0);
        }
        int m = triangle.get(n - 1).size();
        int[] dp = new int[m];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            int length = triangle.get(i).size();
            dp[length - 1] = dp [length - 2] + triangle.get(i).get(length - 1);
            for (int j = length - 2; j > 0; j--) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j - 1]);
            }
            dp[0] += triangle.get(i).get(0);
        }
        return Arrays.stream(dp).min().getAsInt();
    }

    /**
     *  https://leetcode-cn.com/problems/airplane-seat-assignment-probability/
     */
    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return 1;
        }
        double[] dp = new double[n+1];
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = 1/ (double) i + (i - 2) / (double) i * dp[i - 1];
        }
        return dp[n];
    }

    /**
     *  https://leetcode-cn.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }

        if (target.val == cloned.val) {
            return cloned;
        }

        TreeNode leftCopy = getTargetCopy(original.left, cloned.left, target);
        if (leftCopy != null) {
            return leftCopy;
        } else {
            return getTargetCopy(original.right, cloned.right, target);
        }
    }

    /**
     *  https://leetcode-cn.com/problems/sum-of-nodes-with-even-valued-grandparent/
     */
    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.val % 2 == 0) {
            if (root.left != null) {
                if (root.left.right != null) {
                    res += root.left.right.val;
                }
                if (root.left.left != null) {
                    res += root.left.left.val;
                }
            }
            if (root.right != null) {
                if (root.right.right != null) {
                    res += root.right.right.val;
                }
                if (root.right.left != null) {
                    res += root.right.left.val;
                }
            }
        }
        return res + sumEvenGrandparent(root.left) + sumEvenGrandparent(root.right);
    }

    /**
     *  https://leetcode-cn.com/problems/deepest-leaves-sum/
     */
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        Queue<TreeNode> temp = new ArrayDeque<>();
        int res = root.val;
        if (root.left != null) {
            queue.offer(root.left);
        }
        if (root.right != null) {
            queue.offer(root.right);
        }
        while (!queue.isEmpty()) {
            res = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.remove();
                res += poll.val;
                if (poll.left != null) {
                    temp.offer(poll.left);
                }
                if (poll.right != null) {
                    temp.offer(poll.right);
                }
            }
            queue.addAll(temp);
            temp.clear();
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/search-insert-position/
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     *  https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right + 1};
    }
}
