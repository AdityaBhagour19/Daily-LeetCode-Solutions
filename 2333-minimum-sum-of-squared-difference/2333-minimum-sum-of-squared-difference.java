class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int max = 0;
        int[] cnt = new int[100001];
        long total = 0;
        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            cnt[d]++;
            total += d;
            max = Math.max(max, d);
        }
        if (total <= k) return 0;

        for (int d = max; d > 0 && k > 0; d--) {
            if (cnt[d] == 0) continue;
            long c = cnt[d];
            if (c <= k) {
                k -= c;
                cnt[d - 1] += (int) c;
                cnt[d] = 0;
            } else {
                cnt[d - 1] += (int) k;
                cnt[d] -= (int) k;
                k = 0;
            }
        }

        long res = 0;
        for (int d = 1; d <= max; d++) {
            res += (long) cnt[d] * d * d;
        }
        return res;
    }
}