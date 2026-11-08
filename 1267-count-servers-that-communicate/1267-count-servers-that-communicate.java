class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m], col = new int[n];
        int total = 0;
        for (int i = 0; i < m; i++) {
            int[] g = grid[i];
            for (int j = 0; j < n; j++) {
                if (g[j] == 1) {
                    row[i]++;
                    col[j]++;
                    total++;
                }
            }
        }
        int alone = 0;
        for (int i = 0; i < m; i++) {
            if (row[i] != 1) continue;
            int[] g = grid[i];
            for (int j = 0; j < n; j++) {
                if (g[j] == 1) {
                    if (col[j] == 1) alone++;
                    break;
                }
            }
        }
        return total - alone;
    }
}