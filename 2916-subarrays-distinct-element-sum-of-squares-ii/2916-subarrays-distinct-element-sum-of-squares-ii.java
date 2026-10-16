class Solution {
    static final long MOD = 1_000_000_007;

    long[] sum;
    long[] sq;
    int[] lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new int[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            update(1, 0, n - 1, last[x] + 1, i);
            ans += sq[1] % MOD;
            last[x] = i;
        }

        return (int) (ans % MOD);
    }

    void update(int o, int l, int r, int a, int b) {
        if (a <= l && r <= b) {
            int len = r - l + 1;
            sq[o] += 2 * sum[o] + len;
            sum[o] += len;
            lazy[o]++;
            return;
        }
        int mid = (l + r) >> 1;
        if (a <= mid) update(o << 1, l, mid, a, b);
        if (b > mid) update(o << 1 | 1, mid + 1, r, a, b);

        long s = sum[o << 1] + sum[o << 1 | 1];
        long t = lazy[o];
        int len = r - l + 1;
        sum[o] = s + t * len;
        sq[o] = sq[o << 1] + sq[o << 1 | 1] + 2 * t * s + t * t * len;
    }
}