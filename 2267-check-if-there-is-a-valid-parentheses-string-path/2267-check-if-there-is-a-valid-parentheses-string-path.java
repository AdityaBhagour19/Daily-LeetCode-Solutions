class Solution {
    Boolean[][][] dp;
    int m, n;

    public boolean hasValidPath(char[][] g) {
        m = g.length; n = g[0].length;
        if (g[0][0] == ')' || g[m-1][n-1] == '(' || (m+n-1)%2 == 1)
            return false;
        dp = new Boolean[m][n][m+n];
        return dfs(g, 0, 0, 0);
    }

    boolean dfs(char[][] g, int i, int j, int b) {
        b += g[i][j] == '(' ? 1 : -1;
        if (b < 0 || b > m+n-1-i-j) return false;
        if (i == m-1 && j == n-1) return b == 0;
        if (dp[i][j][b] != null) return dp[i][j][b];

        boolean ans = i+1 < m && dfs(g,i+1,j,b);
        if (!ans && j+1 < n) ans = dfs(g,i,j+1,b);
        return dp[i][j][b] = ans;
    }
}