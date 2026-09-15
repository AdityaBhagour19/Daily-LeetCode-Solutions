class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            for (int l = i, r = i; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                if (r - l + 1 >= k)
                    dp[r + 1] = Math.max(dp[r + 1], dp[l] + 1);
            }

            for (int l = i - 1, r = i; l >= 0 && r < n && s.charAt(l) == s.charAt(r); l--, r++) {
                if (r - l + 1 >= k)
                    dp[r + 1] = Math.max(dp[r + 1], dp[l] + 1);
            }
        }

        return dp[n];
    }
}