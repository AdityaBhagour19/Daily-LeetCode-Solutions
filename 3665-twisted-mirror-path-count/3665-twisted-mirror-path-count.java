class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;
        int[] sendDown = new int[n];
        int last = 0;

        for (int i = 0; i < m; i++) {
            int sendRight = 0;
            int[] row = grid[i];
            for (int j = 0; j < n; j++) {
                int r = (i == 0 && j == 0) ? 1 : sendRight;
                int d = sendDown[j];
                if (row[j] == 0) {
                    int t = r + d;
                    if (t >= MOD) t -= MOD;
                    sendRight = t;
                    sendDown[j] = t;
                    last = t;
                } else {
                    sendRight = d;
                    sendDown[j] = r;
                }
            }
        }
        return last;
    }
}