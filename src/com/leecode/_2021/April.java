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
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // TODO
    }
}
