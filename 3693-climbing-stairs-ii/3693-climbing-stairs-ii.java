class Solution {
    public int climbStairs(int n, int[] costs) {
        final int INF = 1_500_000_000;
        int p3 = INF, p2 = INF, p1 = 0;
        for (int j = 1; j <= n; j++) {
            int best = p1 + 1;
            int c2 = p2 + 4;
            if (c2 < best) best = c2;
            int c3 = p3 + 9;
            if (c3 < best) best = c3;
            int cur = best + costs[j - 1];
            p3 = p2;
            p2 = p1;
            p1 = cur;
        }
        return p1;
    }
}