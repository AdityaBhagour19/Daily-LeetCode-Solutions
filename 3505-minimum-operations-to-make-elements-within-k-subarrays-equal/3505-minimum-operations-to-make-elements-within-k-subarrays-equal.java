import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        final int OFF = 1_000_000;
        final int B = 17;
        final long MASK = (1L << B) - 1;

        long[] bits = new long[(2 * OFF >> 6) + 1];
        for (int v : nums) bits[(v + OFF) >> 6] |= 1L << (v + OFF);
        int[] before = new int[bits.length + 1];
        for (int w = 0; w < bits.length; w++) before[w + 1] = before[w] + Long.bitCount(bits[w]);
        int m = before[bits.length];
        int[] vals = new int[m];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            int u = nums[i] + OFF;
            int r = before[u >> 6] + Long.bitCount(bits[u >> 6] & ((1L << u) - 1));
            vals[r] = u;
            rank[i] = r + 1;
        }

        long[] tree = new long[m + 1];
        int top = Integer.highestOneBit(m);
        int mid = (x + 1) / 2;
        long[] cost = new long[n - x + 1];
        long total = 0;

        for (int i = 0; i < n; i++) {
            long plus = ((long) (nums[i] + OFF) << B) | 1;
            for (int p = rank[i]; p <= m; p += p & -p) tree[p] += plus;
            total += nums[i] + OFF;
            if (i >= x) {
                long minus = ((long) (nums[i - x] + OFF) << B) | 1;
                for (int p = rank[i - x]; p <= m; p += p & -p) tree[p] -= minus;
                total -= nums[i - x] + OFF;
            }
            if (i >= x - 1) {
                long acc = 0;
                int pos = 0;
                for (int step = top; step > 0; step >>= 1) {
                    int nxt = pos + step;
                    if (nxt <= m) {
                        long t = acc + tree[nxt];
                        if ((t & MASK) < mid) { acc = t; pos = nxt; }
                    }
                }
                long c = acc & MASK, s = acc >> B;
                cost[i - x + 1] = total - 2 * s + (long) vals[pos] * (2 * c - x);
            }
        }

        long[] prev = new long[n + 1];
        long[] cur = new long[n + 1];
        for (int t = 1; t <= k; t++) {
            int lo = t * x;
            cur[lo - 1] = Long.MAX_VALUE;
            for (int i = lo; i <= n; i++)
                cur[i] = Math.min(cur[i - 1], prev[i - x] + cost[i - x]);
            long[] tmp = prev; prev = cur; cur = tmp;
        }
        return prev[n];
    }
}