package com.leecode._2021;

import java.util.HashMap;
import java.util.Map;

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
}
