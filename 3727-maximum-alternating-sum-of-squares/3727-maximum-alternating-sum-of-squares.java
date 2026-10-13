class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int x : nums) {
            int a = x < 0 ? -x : x;
            if (a > max) max = a;
        }

        int[] cnt = new int[max + 1];
        for (int x : nums) cnt[x < 0 ? -x : x]++;

        int minus = n / 2;
        long res = 0;
        for (int v = 0; v <= max; v++) {
            int c = cnt[v];
            if (c == 0) continue;
            long sq = (long) v * v;
            int sub = Math.min(c, minus);
            res -= sub * sq;
            res += (c - sub) * sq;
            minus -= sub;
        }
        return res;
    }
}