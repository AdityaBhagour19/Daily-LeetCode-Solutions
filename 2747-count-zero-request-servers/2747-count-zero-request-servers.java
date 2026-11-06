class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length, q = queries.length, active = 0, lo = 0, hi = 0, M = (1 << 17) - 1;
        long[] ev = new long[m], qs = new long[q]; int[] cnt = new int[n + 1], res = new int[q];
        for (int i = 0; i < m; i++) ev[i] = ((long) logs[i][1] << 17) | logs[i][0];
        for (int i = 0; i < q; i++) qs[i] = ((long) queries[i] << 17) | i;
        java.util.Arrays.sort(ev); java.util.Arrays.sort(qs);
        for (long cur : qs) {
            while (hi < m && (ev[hi] >>> 17) <= (cur >>> 17)) if (cnt[(int) (ev[hi++] & M)]++ == 0) active++;
            while (lo < hi && (ev[lo] >>> 17) < (cur >>> 17) - x) if (--cnt[(int) (ev[lo++] & M)] == 0) active--;
            res[(int) (cur & M)] = n - active;
        }
        return res;
    }
}