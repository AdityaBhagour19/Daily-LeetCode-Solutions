class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        int max = 0;
        for (int[] row : grid) for (int v : row) if (v > max) max = v;
        int size = Integer.highestOneBit(max) << 1;
        if (size == 0) size = 1;

        boolean[][] dp = new boolean[n][size];
        boolean[] tmp = new boolean[size];
        dp[0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int v = grid[i][j];
                boolean[] up = dp[j];
                boolean[] left = j > 0 ? dp[j - 1] : null;
                for (int y = 0; y < size; y++) {
                    int x = y ^ v;
                    tmp[y] = (i > 0 && up[x]) || (left != null && left[x]);
                }
                System.arraycopy(tmp, 0, dp[j], 0, size);
            }
        }

        boolean[] last = dp[n - 1];
        for (int y = 0; y < size; y++) if (last[y]) return y;
        return -1;
    }
}