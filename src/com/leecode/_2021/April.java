package com.leecode._2021;

import com.leecode.common.TreeNode;

import java.util.*;

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

    /**
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
     */
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right)/2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[left] == nums[mid] && nums[right] == nums[mid]) {
                left++;
                right--;
                continue;
            }
            if (nums[mid] >= nums[left]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/lru-cache/
     */
    class LRUCache {
        int size;
        int capacity;
        Node head;
        Node tail;
        Map<Integer, Node> map;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            this.head = new Node();
            this.tail = new Node();
            this.head.next = this.tail;
            this.tail.pre = this.head;
            this.map = new HashMap<>();
        }

        public int get(int key) {
            Node node = this.map.get(key);
            if (node == null) {
                return -1;
            }
            put(key, node.val);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = this.map.get(key);
            if (node != null) {
                remove(node);
            }
            if (this.size == this.capacity) {
                Node lastNode = this.tail.pre;
                remove(lastNode);
            }
            node = new Node();
            node.key = key;
            node.val = value;
            addFirst(node);
        }

        private void addFirst(Node node) {
            Node next = this.head.next;
            this.head.next = node;
            node.next = next;
            next.pre = node;
            node.pre = this.head;
            this.size++;
            this.map.put(node.key, node);
        }

        private void remove(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
            this.size--;
            this.map.remove(node.key);
        }

        private class Node {
            int key;
            int val;
            Node pre;
            Node next;
        }
    }

    /**
     * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[right] < nums[mid]) {
                left = mid + 1;
            } else if (nums[right] > nums[mid]){
                right = mid;
            } else {
                right = right - 1;
            }
        }
        return nums[left];
    }

    /**
     * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
     */
    class Trie {
        CharNode head;
        Set<String> set;

        private CharNode find(char c, List<CharNode> list) {
            for (CharNode node : list) {
                if (node.c == c) {
                    return node;
                }
            }
            return null;
        }

        /** Initialize your data structure here. */
        public Trie() {
            this.head = new CharNode();
            this.head.next = new ArrayList<>();
            set = new HashSet<>();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            set.add(word);
            CharNode cur = this.head;
            for (char c : word.toCharArray()) {
                CharNode node = find(c, cur.next);
                if (node == null) {
                    CharNode charNode = new CharNode();
                    charNode.c = c;
                    charNode.next = new ArrayList<>();
                    cur.next.add(charNode);
                    cur = charNode;
                } else {
                    cur = node;
                }
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return set.contains(word);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            CharNode cur = this.head;
            for (char c : prefix.toCharArray()) {
                CharNode charNode = find(c, cur.next);
                if (charNode == null) {
                    return false;
                } else {
                    cur = charNode;
                }
            }
            return true;
        }

        class CharNode {
            char c;
            List<CharNode> next;
        }
    }

    /**
     * https://leetcode-cn.com/problems/hamming-distance/
     */
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int sum = 0;
        while (z != 0) {
            sum += z & 1;
            z >>>= 1;
        }
        return sum;
    }

    /**
     * https://leetcode-cn.com/problems/permutations/
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        int[] flag = new int[nums.length];
        permuteBackTrack(nums, flag, tmp, result);
        return result;
    }
    private void permuteBackTrack(int[] nums, int[] flag, List<Integer> tmp, List<List<Integer>> result) {
        if (tmp.size() == nums.length) {
            result.add(new ArrayList<>(tmp));
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i] == 1) {
                continue;
            }
            tmp.add(nums[i]);
            flag[i] = 1;
            permuteBackTrack(nums, flag,tmp, result);
            tmp.remove(tmp.size() - 1);
            flag[i] = 0;
        }
    }

    /**
     * https://leetcode-cn.com/problems/generate-parentheses/
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesisBackTrack(0,  n * 2, 0, "", result);
        return result;
    }
    private void generateParenthesisBackTrack(int cur, int max, int score, String tmp, List<String> result) {
        if (cur == max) {
            if (score == 0) {
                result.add(tmp);
            }
            return;
        }
        if (cur + 1 <= max) {
            generateParenthesisBackTrack(cur + 1, max, score + 1, tmp + "(", result);
        }
        if (score - 1 >= 0) {
            generateParenthesisBackTrack(cur + 1, max, score - 1, tmp + ")", result);
        }
    }

    /**
     * https://leetcode-cn.com/problems/house-robber-ii/
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            Arrays.sort(nums);
            return nums[len - 1];
        }

        int[] dp = new int[len];
        int result = 0;
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        result = dp[len - 2];
        dp[len - 1] = nums[len - 1];
        dp[len - 2] = Math.max(nums[len - 1], nums[len - 2]);
        for (int i = len - 3; i > 0; i--) {
            dp[i] = Math.max(dp[i + 2] + nums[i], dp[i + 1]);
        }
        return Math.max(result, dp[1]);
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalDfs(result, root);
        return result;
    }
    void inorderTraversalDfs(List<Integer> result, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inorderTraversalDfs(result, node.left);
        }
        result.add(node.val);
        if (node.right != null) {
            inorderTraversalDfs(result, node.right);
        }
    }

    /**
     * https://leetcode-cn.com/problems/rotate-image/
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int index = 0;
        while (index <= n / 2) {
            for (int i = index; i < n - 1 - index; i++) {
                int tmp = matrix[index][i];
                matrix[index][i] = matrix[n - 1 - i][index];
                matrix[n - 1 - i][index] = matrix[n - 1 -index][n - 1 - i];
                matrix[n - 1 -index][n - 1 - i] = matrix[i][n - 1 - index];
                matrix[i][n - 1 - index] = tmp;
            }
            index++;
        }
    }

    /**
     * https://leetcode-cn.com/problems/remove-element/
     */
    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == val) {
                continue;
            }
            nums[left] = nums[right];
            left++;
        }
        return left;
    }

    /**
     * https://leetcode-cn.com/problems/decode-ways/
     */
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        int value = s.charAt(0) - '0';
        if (value == 0) {
            return 0;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= len; i++) {
            int cur = s.charAt(i - 1) - '0';
            int pre = s.charAt(i - 2) - '0';
            if (cur == 0) {
                if (pre == 1 || pre == 2) {
                    dp[i] = dp[i - 2];
                } else {
                    return 0;
                }
            } else {
                if (pre == 1 || (cur <= 6 && pre == 2)) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[len];
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int left = 1;
        int[] result = new int[len];
        result[len - 1] = nums[len - 1];
        for (int i = len - 2; i > 0; i--) {
            result[i] = result[i + 1] * nums[i];
        }
        for (int i = 0; i < len - 1; i++) {
            result[i] = result[i + 1] * left;
            left *= nums[i];
        }
        result[len - 1] = left;
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[0]);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                int[] leftInorder = Arrays.copyOfRange(inorder, 0, i);
                int[] leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + i);
                node.left = buildTree(leftPreorder, leftInorder);
                int[] rightInorder = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                int[] rightPreorder = Arrays.copyOfRange(preorder, 1 + i, preorder.length);
                node.right = buildTree(rightPreorder, rightInorder);
            }
        }
        return node;
    }

    /**
     * https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
     */
    class NumMatrix {
        int[][] dp;
        public NumMatrix(int[][] matrix) {
            int n = matrix.length;
            int m = matrix[0].length;
            dp = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] + dp[row1][col1] - dp[row2 + 1][col1] - dp[row1][col2 + 1];
        }
    }

    /**
     * https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/solution/
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int result = Integer.MIN_VALUE;
        for (int top = 1; top <= n; top++) {
            for (int bot = top; bot <= n; bot++) {
                TreeSet<Integer> set = new TreeSet<>();
                for (int right = 0; right <= m; right++) {
                    int rightSum = dp[bot][right] - dp[top - 1][right];
                    Integer leftSum = set.ceiling(rightSum - k);
                    if (leftSum != null) {
                      result = Math.max(rightSum - leftSum, result);
                    }
                    set.add(rightSum);
                }
            }
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/combination-sum/
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        Arrays.sort(candidates);
        backTrackCombinationSum(candidates, target, result, tmp, 0, 0);
        return result;
    }
    private void backTrackCombinationSum(int[] candidates, int target, List<List<Integer>> result, List<Integer> tmp, int count, int start) {
        if (count == target) {
            result.add(new ArrayList<>(tmp));
            return;
        }
        if (count > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            tmp.add(candidates[i]);
            backTrackCombinationSum(candidates, target, result, tmp, count + candidates[i], i);
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * https://leetcode-cn.com/problems/top-k-frequent-elements/
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (Integer num : map.keySet()) {
            priorityQueue.add(num);
            if (priorityQueue.size() > k) {
                priorityQueue.remove();
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = priorityQueue.remove();
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfsConvertBST(root);
        return root;
    }
    private int convertBSTSum = 0;
    private void dfsConvertBST(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.right != null) {
            dfsConvertBST(root.right);
        }
        convertBSTSum += root.val;
        root.val = convertBSTSum;
        if (root.left != null) {
            dfsConvertBST(root.left);
        }
    }

    /**
     * https://leetcode-cn.com/problems/combination-sum-iv/
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /**
     * https://leetcode-cn.com/problems/increasing-order-search-tree/
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode result = null;
        if (root.left != null) {
            result = increasingBST(root.left);
        }
        TreeNode node = new TreeNode(root.val);
        if (result != null) {
            TreeNode last = result;
            while (last.right != null) {
                last = last.right;
            }
            last.right = node;
        } else {
            result = node;
        }
        if (root.right != null) {
            node.right = increasingBST(root.right);
        }
        return result;
    }
}
