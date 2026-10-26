class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[] stack = new int[m * n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;

                long sum = 0;
                int top = 0;
                stack[top++] = i * n + j;
                sum += grid[i][j];
                grid[i][j] = 0;

                while (top > 0) {
                    int cur = stack[--top];
                    int r = cur / n, c = cur - r * n;

                    if (r > 0 && grid[r - 1][c] != 0) {
                        sum += grid[r - 1][c];
                        grid[r - 1][c] = 0;
                        stack[top++] = cur - n;
                    }
                    if (r < m - 1 && grid[r + 1][c] != 0) {
                        sum += grid[r + 1][c];
                        grid[r + 1][c] = 0;
                        stack[top++] = cur + n;
                    }
                    if (c > 0 && grid[r][c - 1] != 0) {
                        sum += grid[r][c - 1];
                        grid[r][c - 1] = 0;
                        stack[top++] = cur - 1;
                    }
                    if (c < n - 1 && grid[r][c + 1] != 0) {
                        sum += grid[r][c + 1];
                        grid[r][c + 1] = 0;
                        stack[top++] = cur + 1;
                    }
                }
                if (sum % k == 0) count++;
            }
        }
        return count;
    }
}