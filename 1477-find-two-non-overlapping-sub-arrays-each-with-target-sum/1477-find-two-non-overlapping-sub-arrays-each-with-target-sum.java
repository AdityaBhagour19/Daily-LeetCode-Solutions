class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length, INF = Integer.MAX_VALUE / 2;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        int left = 0, sum = 0, ans = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) sum -= arr[left++];

            dp[right + 1] = dp[right];
            if (sum == target) {
                int len = right - left + 1;
                ans = Math.min(ans, dp[left] + len);
                dp[right + 1] = Math.min(dp[right], len);
            }
        }
        return ans >= INF ? -1 : ans;
    }
}