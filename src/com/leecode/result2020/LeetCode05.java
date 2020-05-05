package com.leecode.result2020;

import java.util.*;

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
     *  https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        int max = Arrays.stream(candies).max().getAsInt();
        for (int candy : candies) {
            result.add(candy + extraCandies >= max);
        }
        return result;
    }

    /**
     *  https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    /**
     *  https://leetcode-cn.com/problems/count-number-of-teams/
     */
    public int numTeams(int[] rating) {
        int res = 0;

        for (int i = 1; i < rating.length - 1; i++) {
            int left1 = 0;
            int left2 = 0;
            int right1 = 0;
            int right2 = 0;

            for (int j = 0; j < i; j++) {
                if (rating[j] > rating[i]) {
                    left1++;
                } else if (rating[j] < rating[i]) {
                    left2++;
                }
            }

            for (int j = i + 1; j < rating.length; j++) {
                if (rating[j] > rating[i]) {
                    right1++;
                } else if (rating[j] < rating[i]) {
                    right2++;
                }
            }

            res = res + left1*right2 + left2*right1;
        }

        return res;
    }

    /**
     * https://leetcode-cn.com/problems/validate-binary-search-tree/
     */
    double last = -Double.MAX_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST(root.left)) {
            if (last < root.val) {
                last = root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }

    /**
     *  https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        if (s.length() == 0) {
            return res;
        }
        Map<Character, Integer> map = new HashMap<>(s.length());
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                start = Math.max(map.get(chars[i])+1, start);
            }
            map.put(chars[i], i);
            end = i;
            res = Math.max(res, end - start + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode05 leetCode05 = new LeetCode05();
        leetCode05.lengthOfLongestSubstring("au");
    }

    /**
     *  https://leetcode-cn.com/problems/stone-game/
     */
    public boolean stoneGame(int[] piles) {

        //TODO
        return false;
    }
}
