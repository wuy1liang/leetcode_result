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

    /**
     *  https://leetcode-cn.com/problems/minimum-cost-for-tickets/
     */
    public int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];
        int[] res = new int[lastDay + 1];
        int curDay = 1;
        for (int day : days) {
            while (curDay < day) {
                res[curDay] = res[curDay - 1];
                curDay++;
            }
            int min = res[curDay - 1] + costs[0];
            if (curDay >= 7) {
                min = Math.min(min, res[curDay - 7] + costs[1]);
            } else {
                min = Math.min(min, costs[1]);
            }
            if (curDay >= 30) {
                min = Math.min(min, res[curDay - 30] + costs[2]);
            } else {
                min = Math.min(min, costs[2]);
            }
            res[curDay] = min;
            curDay++;
        }
        return res[res.length - 1];
    }

    /**
     *  https://leetcode-cn.com/problems/longest-palindromic-substring/
     */
    public String longestPalindrome(String s) {
        if ("".equals(s)) {
            return "";
        }
        String s1 = new StringBuilder(s).reverse().toString();
        int length = s.length();
        int size = 0;
        int end = 0;
        int[][] dp = new int[length+1][length+1];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (s.charAt(i) == s1.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                }
                if (dp[i+1][j+1] > size && length -1 - j + dp[i+1][j+1] -1 == i) {
                    size = dp[i+1][j+1];
                    end = i;
                }
            }
        }
        return s.substring(end - size + 1, end + 1);
    }

    /**
     *  https://leetcode-cn.com/problems/subtree-of-another-tree/
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSametree(s, t);
    }

    private boolean isSametree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s != null && t != null) {
            return s.val == t.val && isSametree(s.left, t.left) && isSametree(s.right, t.right);
        } else {
            return false;
        }
    }

    /**
     *  https://leetcode-cn.com/problems/count-negative-numbers-in-a-sorted-matrix/
     */
    public int countNegatives(int[][] grid) {
        int res = 0;
        int last = grid[0].length - 1;
        for (int[] aGrid : grid) {
            int j;
            for (j = last; j >= 0; j--) {
                if (aGrid[j] >= 0) {
                    if (j + 1 < aGrid.length) {
                        res += aGrid.length - j - 1;
                        last = j + 1;
                    }
                    break;
                }
            }
            if (j == -1) {
                res += aGrid.length;
            }
        }
        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
     */
    public int peakIndexInMountainArray(int[] A) {
        int index = 0;
        int left = 0;
        int right = A.length - 1;

        while (right > left) {
            int mid = left + (right - left) / 2;
            if (A[mid + 1] < A[mid] && A[mid - 1] < A[mid]) {
                index = mid;
                break;
            } else if (A[mid + 1] > A[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return index;
    }

    /**
     *  https://leetcode-cn.com/problems/maximal-square/
     */
    public int maximalSquare(char[][] matrix) {
        int res = 0;
        int n = matrix.length;
        if (n == 0) {
            return 0;
        }
        int m = matrix[0].length;
        int[][] dp = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i-1][j-1] == '1') {
                    int min = Math.min(dp[i-1][j-1], dp[i-1][j]);
                    min = Math.min(min, dp[i][j-1]);
                    dp[i][j] = min + 1;

                    if (dp[i][j] > res) {
                        res = dp[i][j];
                    }
                }
            }
        }

        return res * res;
    }

    /**
     *  https://leetcode-cn.com/problems/missing-number/
     */
    public int missingNumber(int[] nums) {
        int res = 0;

        for (int i = 1; i <= nums.length; i++) {
            res = res + i - nums[i-1];
        }

        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/sorted-matrix-search-lcci/
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        int x = m - 1;
        int y = 0;

        while (x >= 0 && x < m && y >=0 && y < n) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                x--;
            } else {
                y++;
            }
        }

        return false;
    }

    /**
     * https://leetcode-cn.com/problems/check-if-n-and-its-double-exist/
     */
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (set.contains(i*2) || (i%2 == 0 && set.contains(i/2))) {
                return true;
            } else {
                set.add(i);
            }
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/distribute-candies/
     */
    public int distributeCandies(int[] candies) {
        Arrays.sort(candies);
        int res = 1;
        for (int i = 1; i < candies.length && res < candies.length/2; i++) {
            if (candies[i] > candies[i-1]) {
                res++;
            }
        }
        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/sqrtx/
     */
    public int mySqrt(int x) {
        int res = 0;
        int left = 0;
        int right = x;

        while (left <= right) {
            int mid = left + (right - left)/2;
            if ((long)mid*mid <= x) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/
     */
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int res = 0;
        int[][] dp = new int[m+1][n+1];

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (matrix[i-1][j-1] == 0) {
                    dp[i][j] = 0;
                } else {
                    int min = Math.min(dp[i-1][j-1], dp[i][j-1]);
                    dp[i][j] = Math.min(min, dp[i-1][j]) + 1;
                    res += dp[i][j];
                }
            }
        }

        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/minimum-path-sum/
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = 0;
                if (i - 1 < 0 && j -1 >= 0) {
                    min = dp[i][j-1];
                } else if (i - 1 >= 0 && j -1 < 0) {
                    min = dp[i-1][j];
                } else if (i - 1 >= 0) {
                    min = Math.min(dp[i][j-1], dp[i-1][j]);
                }
                dp[i][j] = grid[i][j] + min;
            }
        }

        return dp[m-1][n-1];
    }

    /**
     *  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     */
    public int maxProfit(int[] prices, int fee) {
        int cash = 0;
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }

    /**
     *  https://leetcode-cn.com/problems/powx-n/
     */
    public double myPow(double x, int n) {
        boolean flag = false;
        long ln = n;
        if (n < 0) {
            ln = -ln;
            flag = true;
        }
        double res = 1;
        while (ln > 0) {
            if (ln%2 == 1) {
                res *= x;
            }
            x *= x;
            ln = ln >>> 1;
        }
        return flag? 1/res: res;
    }

    /**
     *  https://leetcode-cn.com/problems/container-with-most-water/
     */
    public int maxArea(int[] height) {
        int res = 0;
        int i = 0;
        int j = height.length - 1;

        while (i < j) {
            if (height[i] > height[j]) {
                res = Math.max(res, (j - i) * height[j]);
                j--;
            } else {
                res = Math.max(res, (j - i) * height[i]);
                i++;
            }
        }

        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/rotting-oranges/
     */
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();
        int res = 0;
        int num = 0;
        int n = grid.length;
        int m = grid[0].length;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    num++;
                }
            }
        }

        while (!queue.isEmpty() && num > 0) {
            res++;
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                int[] e = queue.poll();
                for (int[] aDirection : direction) {
                    int x = e[0] + aDirection[0];
                    int y = e[1] + aDirection[1];
                    if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.offer(new int[]{x, y});
                        num--;
                    }
                }
            }
        }

        return num > 0? -1: res;
    }

    /**
     *  https://leetcode-cn.com/problems/single-number/
     */
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode cur = start;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.next.val == cur.next.val) {
                ListNode temp = cur.next;
                while (temp.next != null && temp.val == temp.next.val) {
                    temp = temp.next;
                }
                cur.next = temp.next;
            } else {
                cur = cur.next;
            }
        }

        return start.next;
    }

    /**
     *  https://leetcode-cn.com/problems/spiral-matrix-ii/
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bot = n - 1;
        int num = 1;
        while (num <= n*n) {
            for (int i = left; i <= right; i++) {
                res[top][i] = num;
                num++;
            }
            top++;

            for (int i = top; i <= bot; i++) {
                res[i][right] = num;
                num++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                res[bot][i] = num;
                num++;
            }
            bot--;

            for (int i = bot; i >= top; i--) {
                res[i][left] = num;
                num++;
            }
            left++;
        }

        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/three-steps-problem-lcci/
     */
    public int waysToStep(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n ==3) {
            return 4;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for (int i = 3; i < n; i++) {
            dp[i] = ((dp[i-1] + dp[i-2])%1000000007 + dp[i-3])%1000000007;
        }
        return dp[n-1];
    }

    /**
     *  https://leetcode-cn.com/problems/min-stack/
     */
    class MinStack {

        private Stack<Integer> stack;
        private Stack<Integer> min;

        /** initialize your data structure here. */
        public MinStack() {
            this.stack = new Stack<>();
            this.min = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (min.empty() || x < min.peek()) {
                min.push(x);
            } else {
                min.push(min.peek());
            }
        }

        public void pop() {
            stack.pop();
            min.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    /**
     *  https://leetcode-cn.com/problems/ugly-number/
     */
    public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        }
        while (num > 1) {
            if (num % 2 == 0) {
                num /= 2;
                continue;
            }
            if (num % 3 == 0) {
                num /= 3;
                continue;
            }
            if (num % 5 == 0) {
                num /= 5;
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     *  https://leetcode-cn.com/problems/number-of-students-doing-homework-at-a-given-time/
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int res = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (queryTime >= startTime[i] && queryTime <= endTime[i]) {
                res++;
            }
        }
        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/qiu-12n-lcof/
     */
    public int sumNums(int n) {
        return (n + 1) * n /2;
    }

    /**
     *  https://leetcode-cn.com/problems/guess-numbers/
     */
    public int game(int[] guess, int[] answer) {
        int res = 0;
        for (int i = 0; i < 3; i++) {
            if (guess[i] == answer[i]) {
                res++;
            }
        }
        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/delete-middle-node-lcci/
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     *  https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero/
     */
    public int numberOfSteps (int num) {
        int res = 0;
        if (num == 0) {
            return 0;
        }
        while (num > 1) {
            if ((num & 1) == 1) {
                res += 2;
            } else {
                res++;
            }
            num >>= 1;
        }

        return res + 1;
    }

    /**
     *  https://leetcode-cn.com/problems/jewels-and-stones/
     */
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        int res = 0;
        for (char c : S.toCharArray()) {
            if (set.contains(c)) {
                res++;
            }
        }
        return res;
    }

    /**
     *  https://leetcode-cn.com/problems/defanging-an-ip-address/
     */
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    /**
     *  https://leetcode-cn.com/problems/maximum-product-subarray/
     */
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int min = 1;
        int max = 1;
        for (int num : nums) {
            if (num < 0) {
                int temp = min;
                min = max;
                max = temp;
            }

            max = Math.max(max * num, num);
            min = Math.min(min * num, num);
            res = Math.max(max, res);
        }
        return res;
    }
}
