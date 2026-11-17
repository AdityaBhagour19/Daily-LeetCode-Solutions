class Solution {
    private char[] c;
    private int[][] divs;
    private int[][] cost;

    private int costOf(int i, int j) {
        int v = cost[i][j];
        if (v >= 0) return v;
        int len = j - i + 1;
        int best = Integer.MAX_VALUE;
        for (int d : divs[len]) {
            int m = len / d;
            int diff = 0;
            for (int r = 0; r < d; r++) {
                int lo = i + r;
                int hi = i + r + (m - 1) * d;
                for (int t = 0; t < m / 2; t++, lo += d, hi -= d) if (c[lo] != c[hi]) diff++;
            }
            if (diff < best) best = diff;
        }
        cost[i][j] = best;
        return best;
    }

    public int minimumChanges(String s, int k) {
        int n = s.length();
        c = s.toCharArray();

        divs = new int[n + 1][];
        for (int len = 2; len <= n; len++) {
            int cnt = 0;
            for (int d = 1; d <= len / 2; d++) if (len % d == 0) cnt++;
            divs[len] = new int[cnt];
            cnt = 0;
            for (int d = 1; d <= len / 2; d++) if (len % d == 0) divs[len][cnt++] = d;
        }

        cost = new int[n][n];
        for (int[] row : cost) java.util.Arrays.fill(row, -1);

        final int INF = 1 << 28;
        int[] prev = new int[n + 1];
        int[] cur = new int[n + 1];
        java.util.Arrays.fill(prev, INF);
        prev[0] = 0;
        for (int j = 1; j <= k; j++) {
            java.util.Arrays.fill(cur, INF);
            int hi = n - 2 * (k - j);
            int lo = (j == k) ? n : 2 * j;
            for (int i = lo; i <= hi; i++) {
                int best = INF;
                for (int p = 2 * (j - 1); p <= i - 2; p++) {
                    int pv = prev[p];
                    if (pv >= INF) continue;
                    int v = pv + costOf(p, i - 1);
                    if (v < best) best = v;
                }
                cur[i] = best;
            }
            int[] t = prev; prev = cur; cur = t;
        }
        return prev[n];
    }
}