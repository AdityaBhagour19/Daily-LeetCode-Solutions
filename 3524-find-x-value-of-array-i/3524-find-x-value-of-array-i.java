class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k]; // dp[r] = # of subarrays ending at previous index with product % k == r

        for (int num : nums) {
            long[] newDp = new long[k];
            int m = num % k;
            newDp[m] += 1; // subarray consisting of just this element
            for (int r = 0; r < k; r++) {
                if (dp[r] != 0) {
                    int nr = (int) ((long) r * m % k);
                    newDp[nr] += dp[r];
                }
            }
            dp = newDp;
            for (int x = 0; x < k; x++) result[x] += dp[x];
        }
        return result;
    }
}