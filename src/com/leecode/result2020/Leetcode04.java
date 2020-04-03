package com.leecode.result2020;

/**
 * @author wuyiliang
 */
public class Leetcode04 {

    /**
     *  https://leetcode-cn.com/problems/game-of-life/
     */
    public void gameOfLife(int[][] board) {
        int[][] copy = new int[board.length][board[0].length];

        int[] n1 = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] n2 = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, board[0].length);
        }

        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {

                int sum = 0;
                for (int k = 0; k < 8; k++) {
                    int row = i + n1[k];
                    int col = j + n2[k];
                    if (row >=0 && row < copy.length && col >=0 && col < copy[0].length && copy[row][col] == 1) {
                        sum++;
                    }
                }

                if (board[i][j] == 1) {
                    if (sum < 2 || sum > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (sum == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    /**
     *  https://leetcode-cn.com/problems/string-to-integer-atoi/
     *
     */
    public int myAtoi(String str) {
        char[] chars = str.trim().toCharArray();

        if (chars.length == 0) {
            return 0;
        }
        boolean flag = false;
        int start = 0;

        if (chars[0] == '+') {
            start++;
        } else if (chars[0] == '-'){
            start++;
            flag = true;
        } else if (!Character.isDigit(chars[0])) {
            return 0;
        }

        int result = 0;
        while (start < chars.length && Character.isDigit(chars[start])) {
            int num = chars[start] - '0';

            if (result > (Integer.MAX_VALUE - num) / 10) {
                return flag? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            result = result * 10 + num;
            start++;
        }

        if (flag) {
            result = -result;
        }

        return result;
    }
}
